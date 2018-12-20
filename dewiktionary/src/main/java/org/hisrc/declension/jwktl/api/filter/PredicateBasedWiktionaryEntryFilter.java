package org.hisrc.declension.jwktl.api.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.filter.IWiktionaryEntryFilter;

public class PredicateBasedWiktionaryEntryFilter implements IWiktionaryEntryFilter {

	private final List<Predicate<IWiktionaryEntry>> predicates;

	public PredicateBasedWiktionaryEntryFilter(Predicate<IWiktionaryEntry>... predicates) {
		this.predicates = Collections.unmodifiableList(Arrays.asList(predicates));
	}

	@Override
	public boolean accept(IWiktionaryEntry entry) {
		return predicates.stream().allMatch(predicate -> predicate.test(entry));
	}
}