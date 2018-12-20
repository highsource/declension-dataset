package org.hisrc.declension.jwktl;

import java.util.function.Predicate;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.PartOfSpeech;
import de.tudarmstadt.ukp.jwktl.api.util.Language;

public class IWiktionaryEntryPredicates {

	public static final Predicate<IWiktionaryEntry> HAS_NO_SPACES = entry -> (!entry.getWord().contains(" "));
	public static final Predicate<IWiktionaryEntry> GERMAN_WORD = entry -> Language.equals(entry.getWordLanguage(), Language.GERMAN);
	public static final Predicate<IWiktionaryEntry> NOUN = entry -> entry.getPartsOfSpeech().contains(PartOfSpeech.NOUN);
	public static final Predicate<IWiktionaryEntry> NOT_TOPONYM = entry -> !entry.getPartsOfSpeech().contains(PartOfSpeech.TOPONYM);
	public static final Predicate<IWiktionaryEntry> NOT_FIRST_NAME = entry -> !entry.getPartsOfSpeech().contains(PartOfSpeech.FIRST_NAME);
	public static final Predicate<IWiktionaryEntry> NOT_LAST_NAME = entry -> !entry.getPartsOfSpeech().contains(PartOfSpeech.LAST_NAME);
	public static final Predicate<IWiktionaryEntry> SINGLE_GENDER = entry -> entry.getGenders() != null && entry.getGenders().size() == 1;
	public static final Predicate<IWiktionaryEntry> NOT_PROPER_NOUN = entry -> !entry.getPartsOfSpeech().contains(PartOfSpeech.PROPER_NOUN);
	public static final Predicate<IWiktionaryEntry> HAS_WORD_FORMS = entry -> (entry.getWordForms() != null && !entry.getWordForms().isEmpty());
}
