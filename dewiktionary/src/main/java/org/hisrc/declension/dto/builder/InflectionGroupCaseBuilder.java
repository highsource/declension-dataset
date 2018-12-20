package org.hisrc.declension.dto.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalCase;

public class InflectionGroupCaseBuilder {

	private final InflectionGroupId inflectionGroupId;
	private final GrammaticalCase grammaticalCase;
	private final List<IWiktionaryWordForm> wordForms = new ArrayList<>();
	private final List<IWiktionaryWordForm> unmodifiableWordForms = Collections.unmodifiableList(this.wordForms);

	public InflectionGroupCaseBuilder(InflectionGroupId inflectionGroupId, GrammaticalCase grammaticalCase) {
		Objects.requireNonNull(inflectionGroupId, "inflectionGroupId must not be null");
		Objects.requireNonNull(grammaticalCase, "grammaticalCase must not be null");
		this.inflectionGroupId = inflectionGroupId;
		this.grammaticalCase = grammaticalCase;
	}

	public InflectionGroupId getInflectionGroupId() {
		return inflectionGroupId;
	}

	public GrammaticalCase getCase() {
		return grammaticalCase;
	}

	public List<IWiktionaryWordForm> getWordForms() {
		return unmodifiableWordForms;
	}

	public void addWordForm(IWiktionaryWordForm wordForm) {
		Objects.requireNonNull(wordForm, "wordForm must not be null.");
		Objects.requireNonNull(wordForm.getNumber(), "wordForm.number must not be null.");
		Objects.requireNonNull(wordForm.getCase(), "wordForm.case must not be null.");
		if (!Objects.equals(this.getInflectionGroupId().getNumber(), wordForm.getNumber())) {
			throw new IllegalArgumentException(
					MessageFormat.format("Invalid word form [{0}] with grammatical number [{1}], expected [{2}].",
							wordForm.getWordForm(), wordForm.getNumber(), this.getInflectionGroupId().getNumber()));
		}
		if (!(this.getInflectionGroupId().getInflectionGroup() == wordForm.getInflectionGroup())) {
			throw new IllegalArgumentException(MessageFormat.format(
					"Invalid word form [{0}] with inflection group [{1}], expected [{2}].", wordForm.getWordForm(),
					wordForm.getInflectionGroup(), this.getInflectionGroupId().getInflectionGroup()));
		}
		if (wordForm.getCase() != this.grammaticalCase) {
			throw new IllegalArgumentException(
					MessageFormat.format("Invalid word form [{0}] with grammatical case [{1}], expected [{2}].",
							wordForm.getWordForm(), wordForm.getCase(), this.grammaticalCase));
		}
		wordForms.add(wordForm);
	}

	@Override
	public String toString() {
		final String wordFormsAsString = getWordForms().stream().map(IWiktionaryWordForm::getWordForm)
				.collect(Collectors.joining("|"));
		return getInflectionGroupId().toString() + "(" + getCase() + ")=" + wordFormsAsString;
	}

	public List<String> build() {
		return getWordForms().stream().map(IWiktionaryWordForm::getWordForm)
				//
				.filter(Objects::nonNull)
				//
				.filter(word -> !"–".equals(word))
				.collect(Collectors.toList());
	}
}