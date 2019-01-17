package org.hisrc.declension.dto.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hisrc.declension.dto.Inflection;
import org.hisrc.declension.dto.InflectionGroup;

import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalNumber;

public class InflectionBuilder {

	private IWiktionaryEntry entry;

	private Map<InflectionGroupId, InflectionGroupBuilder> inflectionGroupBuilders = new HashMap<>();
	private List<InflectionGroupBuilder> singularInflectionGroupBuilders = new ArrayList<>(1);
	private List<InflectionGroupBuilder> pluralInflectionGroupBuilders = new ArrayList<>(1);

	private Set<IWiktionaryWordForm> wordForms = new HashSet<>();

	public InflectionBuilder(IWiktionaryEntry entry) {
		Objects.requireNonNull(entry, "entry must not be null.");
		this.entry = entry;
		if (entry.getWordForms() != null) {
			entry.getWordForms().stream().filter(wordForm -> wordForm.getNumber() != null)
					.filter(wordForm -> wordForm.getCase() != null).forEach(this::addWordForm);
		}
	}

	public Inflection build() {

		List<InflectionGroup> singular = getInflectionGroups(GrammaticalNumber.SINGULAR).stream()
				.map(InflectionGroupBuilder::build).filter(Objects::nonNull).collect(Collectors.toList());
		List<InflectionGroup> plural = getInflectionGroups(GrammaticalNumber.PLURAL).stream()
				.map(InflectionGroupBuilder::build).filter(Objects::nonNull).collect(Collectors.toList());
		
		return new Inflection(entry.getWord(), getDeterminatum(), singular, plural);
	}
	
	public String getDeterminatum()  {
		IWikiString wordEtymology = entry.getWordEtymology();
		String word = entry.getWord();
		
		if (wordEtymology != null && wordEtymology.toString().toLowerCase().contains("determinativkompositum")) {
			Set<String> links = new HashSet<>(wordEtymology.getWikiLinks());
			for (String link : links) {
				if (word.toLowerCase().endsWith(link.toLowerCase())) {
					return link;
				}
			}
		}
		return null;
	}
	
	

	public void addWordForm(IWiktionaryWordForm wordForm) {
		Objects.requireNonNull(wordForm, "wordForm must not be null.");
		Objects.requireNonNull(wordForm.getNumber(), "wordForm.number must not be null.");
		int inflectionGroupIndex = wordForm.getInflectionGroup();
		Objects.requireNonNull(wordForm.getCase(),
				"wordForm.case must not be null. Word form is [" + wordForm.getWordForm() + "].");
		final InflectionGroupId inflectionGroupId = new InflectionGroupId(wordForm.getNumber(),
				inflectionGroupIndex);
		InflectionGroupBuilder inflectionGroupBuilder = inflectionGroupBuilders.computeIfAbsent(inflectionGroupId,
				id -> new InflectionGroupBuilder(id, wordForm.getGender()));
		final List<InflectionGroupBuilder> inflectionGroupsList;
		final GrammaticalNumber grammaticalNumber = inflectionGroupId.getNumber();
		switch (grammaticalNumber) {
		case SINGULAR:
			inflectionGroupsList = this.singularInflectionGroupBuilders;
			break;
		case PLURAL:
			inflectionGroupsList = this.pluralInflectionGroupBuilders;
			break;
		default:
			throw new IllegalStateException(
					MessageFormat.format("Unsupported grammatical number [{0}].", grammaticalNumber));
		}

		int index = inflectionGroupId.getInflectionGroup() - 1;
		while (inflectionGroupsList.size() <= index) {
			inflectionGroupsList.add(null);
		}
		inflectionGroupsList.set(index, inflectionGroupBuilder);

		inflectionGroupBuilder.addWordForm(wordForm);
		wordForms.add(wordForm);
	}

	private List<InflectionGroupBuilder> getInflectionGroups(GrammaticalNumber number) {

		return inflectionGroupBuilders.values().stream()
				.filter(inflectionGroupBuilder -> inflectionGroupBuilder.getInflectionGroupId().getNumber() == number)
				.collect(Collectors.toList());
	}

	public InflectionGroupBuilder getInflectionGroup(GrammaticalNumber number, int index) {
		final InflectionGroupId id = new InflectionGroupId(number, index);
		final InflectionGroupBuilder inflectionGroup = inflectionGroupBuilders.get(id);
		return inflectionGroup;
	}

	public InflectionGroupBuilder getInflectionGroupSize(GrammaticalNumber number, int index) {
		final InflectionGroupId id = new InflectionGroupId(number, index);
		final InflectionGroupBuilder inflectionGroup = inflectionGroupBuilders.get(id);
		return inflectionGroup;
	}

	public IWiktionaryEntry getEntry() {
		return entry;
	}

	public Set<IWiktionaryWordForm> getWordForms() {
		return wordForms;
	}
}