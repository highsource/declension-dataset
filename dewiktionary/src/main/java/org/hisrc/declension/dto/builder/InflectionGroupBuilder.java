package org.hisrc.declension.dto.builder;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hisrc.declension.dto.GrammaticalCaseSuffices;
import org.hisrc.declension.dto.InflectionGroup;
import org.hisrc.declension.util.Trie;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalCase;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;

public class InflectionGroupBuilder {
	private static final List<String> SINGLE_EMPTY_STRING = Collections.unmodifiableList(Arrays.asList("-"));

	private final InflectionGroupId inflectionGroupId;
	private final GrammaticalGender grammaticalGender;
	@JsonProperty("n")
	private final InflectionGroupCaseBuilder nominative;
	@JsonProperty("g")
	private final InflectionGroupCaseBuilder genitive;
	@JsonProperty("d")
	private final InflectionGroupCaseBuilder dative;
	@JsonProperty("a")
	private final InflectionGroupCaseBuilder accusative;

	public InflectionGroupBuilder(InflectionGroupId inflectionGroupId, GrammaticalGender grammaticalGender) {
		Objects.requireNonNull(inflectionGroupId, "inflectionGroupId must not be null");
		this.inflectionGroupId = inflectionGroupId;
		this.grammaticalGender = grammaticalGender;
		this.nominative = new InflectionGroupCaseBuilder(inflectionGroupId, GrammaticalCase.NOMINATIVE);
		this.genitive = new InflectionGroupCaseBuilder(inflectionGroupId, GrammaticalCase.GENITIVE);
		this.dative = new InflectionGroupCaseBuilder(inflectionGroupId, GrammaticalCase.DATIVE);
		this.accusative = new InflectionGroupCaseBuilder(inflectionGroupId, GrammaticalCase.ACCUSATIVE);
	}

	public InflectionGroupId getInflectionGroupId() {
		return inflectionGroupId;
	}

	public GrammaticalGender getGender() {
		return grammaticalGender;
	}

	public InflectionGroupCaseBuilder getNominative() {
		return nominative;
	}

	public InflectionGroupCaseBuilder getGenitive() {
		return genitive;
	}

	public InflectionGroupCaseBuilder getDative() {
		return dative;
	}

	public InflectionGroupCaseBuilder getAccusative() {
		return accusative;
	}

	public void addWordForm(IWiktionaryWordForm wordForm) {
		Objects.requireNonNull(wordForm, "wordForm must not be null.");
		Objects.requireNonNull(wordForm.getNumber(), "wordForm.number must not be null.");
		Objects.requireNonNull(wordForm.getCase(), "wordForm.case must not be null.");
		if (!Objects.equals(this.getInflectionGroupId().getNumber(), wordForm.getNumber())) {
			throw new IllegalArgumentException(
					MessageFormat.format("Invalid word form [{0}] with grammatical number [{1}], expected [{2}].",
							wordForm.getWordForm(), wordForm.getNumber(), getInflectionGroupId().getNumber()));
		}
		if (!(this.getInflectionGroupId().getInflectionGroup() == wordForm.getInflectionGroup())) {
			throw new IllegalArgumentException(MessageFormat.format(
					"Invalid word form [{0}] with inflection group [{1}], expected [{2}].", wordForm.getWordForm(),
					wordForm.getInflectionGroup(), getInflectionGroupId().getInflectionGroup()));
		}
		if (!Objects.equals(this.getGender(), wordForm.getGender())) {
			throw new IllegalArgumentException(
					MessageFormat.format("Invalid word form [{0}] with grammatical gender [{1}], expected [{2}].",
							wordForm.getWordForm(), wordForm.getGender(), getGender()));
		}
		final GrammaticalCase wordFormCase = wordForm.getCase();
		final InflectionGroupCaseBuilder cell = getCellByCase(wordFormCase);
		cell.addWordForm(wordForm);
	}

	private InflectionGroupCaseBuilder getCellByCase(final GrammaticalCase wordFormCase) {
		switch (wordFormCase) {
		case NOMINATIVE:
			return getNominative();
		case GENITIVE:
			return getGenitive();
		case DATIVE:
			return getDative();
		case ACCUSATIVE:
			return getAccusative();
		default:
			throw new UnsupportedOperationException(
					MessageFormat.format("Unknown grammatical case [{0}].", wordFormCase));
		}
	}

	public InflectionGroup build() {

		final List<String> nominative = getNominative() == null ? null : getNominative().build();
		final List<String> genitive = getGenitive() == null ? null : getGenitive().build();
		final List<String> dative = getDative() == null ? null : getDative().build();
		final List<String> accusative = getAccusative() == null ? null : getAccusative().build();

		if (nominative.isEmpty() && genitive.isEmpty() && dative.isEmpty() && accusative.isEmpty()) {
			return null;
		} else {

			final Trie wordForms = new Trie();

			nominative.stream().forEachOrdered(wordForms::add);
			genitive.stream().forEachOrdered(wordForms::add);
			dative.stream().forEachOrdered(wordForms::add);
			accusative.stream().forEachOrdered(wordForms::add);

			final String root = wordForms.getCommonPrefix();

			List<String> n = nominative.stream().map(s -> s.substring(root.length())).map(s -> "".equals(s) ? "-" : s).collect(Collectors.toList());
			List<String> g = genitive.stream().map(s -> s.substring(root.length())).map(s -> "".equals(s) ? "-" : s).collect(Collectors.toList());
			List<String> d = dative.stream().map(s -> s.substring(root.length())).map(s -> "".equals(s) ? "-" : s).collect(Collectors.toList());
			List<String> a = accusative.stream().map(s -> s.substring(root.length())).map(s -> "".equals(s) ? "-" : s).collect(Collectors.toList());

			n = n.equals(SINGLE_EMPTY_STRING) ? null : n;
			g = g.equals(SINGLE_EMPTY_STRING) ? null : g;
			d = d.equals(SINGLE_EMPTY_STRING) ? null : d;
			a = a.equals(SINGLE_EMPTY_STRING) ? null : a;

			final GrammaticalCaseSuffices su;
			if (n == null && g == null && d == null && a == null) {
				su = null;
			} else {
				su = new GrammaticalCaseSuffices(n, g, d, a);
			}
			return new InflectionGroup(getGender(), root, su);
		}
	}
}