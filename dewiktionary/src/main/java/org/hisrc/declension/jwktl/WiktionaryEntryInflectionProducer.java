package org.hisrc.declension.jwktl;

import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.GERMAN_WORD;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.HAS_NO_SPACES;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.HAS_WORD_FORMS;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.NOT_FIRST_NAME;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.NOT_LAST_NAME;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.NOT_PROPER_NOUN;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.NOT_TOPONYM;
import static org.hisrc.declension.jwktl.IWiktionaryEntryPredicates.NOUN;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

import org.hisrc.declension.dto.Inflection;
import org.hisrc.declension.dto.builder.InflectionBuilder;
import org.hisrc.declension.dto.builder.InflectionRule;
import org.hisrc.declension.jwktl.api.filter.PredicateBasedWiktionaryEntryFilter;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.filter.IWiktionaryEntryFilter;
import de.tudarmstadt.ukp.jwktl.api.util.IWiktionaryIterator;

public class WiktionaryEntryInflectionProducer {

	private static final Logger LOGGER = Logger.getLogger(WiktionaryEntryInflectionProducer.class.getName());

	private final IWiktionaryEdition wiktionaryEdition;

	private static final IWiktionaryEntryFilter ENTRY_FILTER = new PredicateBasedWiktionaryEntryFilter(HAS_NO_SPACES,
			GERMAN_WORD, NOUN, NOT_TOPONYM, NOT_FIRST_NAME, NOT_LAST_NAME, NOT_PROPER_NOUN, HAS_WORD_FORMS);

	public WiktionaryEntryInflectionProducer(IWiktionaryEdition wiktionaryEdition) {
		Objects.requireNonNull(wiktionaryEdition, "wiktionaryEdition must not be null");
		this.wiktionaryEdition = wiktionaryEdition;
	}

	public void process(Consumer<Inflection> consumer) {

		final IWiktionaryIterator<IWiktionaryEntry> entriesIterator = this.wiktionaryEdition
				.getAllEntries(ENTRY_FILTER);

		Map<InflectionRule, List<InflectionBuilder>> inflectionByRule = new EnumMap<>(InflectionRule.class);
		StreamSupport.stream(entriesIterator.spliterator(), false).map(this::process).filter(Objects::nonNull)
				.map(inflection -> {
					for (InflectionRule rule : InflectionRule.values()) {
						if (rule.test(inflection)) {
							inflectionByRule.computeIfAbsent(rule, ignored -> new ArrayList<>()).add(inflection);
							break;
						}
					}
					return inflection.build();
				}).map(inflection -> {
					if (inflection.getWord().contains(" ")) {
						LOGGER.warning("Word [" + inflection.getWord() + "] contains spaces.");
					}
					return inflection;
				}).forEach(consumer);

		inflectionByRule.entrySet().stream().forEach(entry -> {
			LOGGER.info(entry.getKey() + "---->" + entry.getValue().size());
		});

		/*
		 * inflectionByRule.get(InflectionRule.REST).forEach(inflection -> {
		 * LOGGER.info(inflection.getEntry().getWord());
		 * LOGGER.info("=============================="); LOGGER.info("Singular 1: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 1));
		 * LOGGER.info("Singular 2: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 2));
		 * LOGGER.info("Singular 3: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 3));
		 * LOGGER.info("Singular 4: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.SINGULAR, 4));
		 * LOGGER.info("Plural 1: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 1));
		 * LOGGER.info("Plural 2: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 2));
		 * LOGGER.info("Plural 3: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 3));
		 * LOGGER.info("Plural 4: " +
		 * inflection.getInflectionGroup(GrammaticalNumber.PLURAL, 4)); });
		 */
	}

	private InflectionBuilder process(IWiktionaryEntry wiktionaryEntry) {
		final List<IWiktionaryWordForm> wordForms = wiktionaryEntry.getWordForms();
		if (wordForms == null) {
			LOGGER.warning("Word [" + wiktionaryEntry.getWord() + "] has null word forms.");
			return null;
		} else if (wordForms.isEmpty()) {
			LOGGER.warning("Word [" + wiktionaryEntry.getWord() + "] has empty word forms.");
			return null;
		} else {
			final InflectionBuilder inflectionBuilder = new InflectionBuilder(wiktionaryEntry);
			return inflectionBuilder;

		}
	}
}