package org.hisrc.declension.jwktl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.hisrc.declension.dto.GrammaticalCaseSuffices;
import org.hisrc.declension.dto.Inflection;
import org.hisrc.declension.dto.InflectionGroup;
import org.hisrc.declension.jackson.databind.GenericJsonSerializer;
import org.junit.Test;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;

public class ExecuteWiktionaryEntryInflectionProducer {

	private final static String TARGET_DIRECTORY = "dumps/dump20181201";

	@Test
	public void processes() throws IOException {

		File wiktionaryDirectory = new File(TARGET_DIRECTORY);

		File outputFile = new File("dataset.json");
		File outputCsvFile = new File("dataset.csv");
		final FileWriter few = new FileWriter(outputCsvFile);
		final GenericJsonSerializer<Inflection> serializer = new GenericJsonSerializer<>(Inflection.class, outputFile);
		serializer.start();

		final Map<GrammaticalCaseSuffices, AtomicInteger> singularSuffices = new HashMap<>();
		final Map<GrammaticalCaseSuffices, AtomicInteger> pluralSuffices = new HashMap<>();

		List<Inflection> inflections = new ArrayList<>();

		try (IWiktionaryEdition wkt = JWKTL.openEdition(wiktionaryDirectory)) {
			final WiktionaryEntryInflectionProducer producer = new WiktionaryEntryInflectionProducer(wkt);
			producer.process(inflections::add);
		}

		Set<String> allWords = inflections.stream().map(Inflection::getWord).collect(Collectors.toSet());

		Set<String> longerLowercaseWords = allWords.stream().filter(w -> w.length() >= 4).map(String::toLowerCase)
				.collect(Collectors.toSet());

		Set<String> words = new TreeSet<>();
		inflections.stream().forEach(inflection -> {

			final String word = inflection.getWord();
			final String lowercaseWord = word.toLowerCase();

			for (int suffixBeginIndex = 1; suffixBeginIndex < lowercaseWord.length(); suffixBeginIndex++) {
				String suffix = lowercaseWord.substring(suffixBeginIndex);
				if (longerLowercaseWords.contains(suffix)) {
					System.out.println("Ignoring " + word + " due to suffix " + suffix + ".");
					return;
				}
			}

			serializer.serialize(inflection);
			try {
				if (words.add(word)) {
					few.append(word + ",\n");
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			inflection.getSingular().stream().map(InflectionGroup::getSuffixes)
					.forEach(s -> singularSuffices.computeIfAbsent(s, key -> new AtomicInteger()).incrementAndGet());
			inflection.getPlural().stream().map(InflectionGroup::getSuffixes)
					.forEach(s -> pluralSuffices.computeIfAbsent(s, key -> new AtomicInteger()).incrementAndGet());
		});

		few.close();
		serializer.end();
		singularSuffices.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().get()));
		System.out.println();
		pluralSuffices.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().get()));
	}
}