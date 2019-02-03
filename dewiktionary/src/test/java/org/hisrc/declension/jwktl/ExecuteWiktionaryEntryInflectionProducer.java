package org.hisrc.declension.jwktl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.print.attribute.HashAttributeSet;

import org.hisrc.declension.dto.GrammaticalCaseSuffices;
import org.hisrc.declension.dto.Inflection;
import org.hisrc.declension.dto.InflectionGroup;
import org.hisrc.declension.jackson.databind.GenericJsonSerializer;
import org.hisrc.dereko.DerewoWordBasisFormListProvider;
import org.hisrc.dereko.dto.DerewoWordBasisFormListEntry;
import org.junit.Test;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;

public class ExecuteWiktionaryEntryInflectionProducer {

	private final static String TARGET_DIRECTORY = "dumps/dump20181201";
	public static final String DEREWO_FILE = "../dereko/data/derewo-v-ww-bll-320000g-2012-12-31-1.0.txt";

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
		
		final Map<String, DerewoWordBasisFormListEntry> wordBasisFormListEntries = new HashMap<>();
		
		try (InputStream is = new FileInputStream(new File(DEREWO_FILE))) {
			final DerewoWordBasisFormListProvider provider = new DerewoWordBasisFormListProvider(is);
			provider.loadWordList(entry -> {
				wordBasisFormListEntries.put(entry.getWord(), entry);
			});
		}

		AtomicInteger count = new AtomicInteger(0);
		
		Set<String> words = new TreeSet<>();
		inflections.stream().forEach(inflection -> {
			
			if (inflection.getDeterminatum() != null) {
				final DerewoWordBasisFormListEntry wordBasisFormListEntry = wordBasisFormListEntries.get(inflection.getWord());
				if (wordBasisFormListEntry == null) {
					return;
				} else {
					int frequencyClass = wordBasisFormListEntry.getFrequencyClass();
					if (frequencyClass >= 18) {
						return;
					}
				}
			}
			

			final String word = inflection.getWord();

			serializer.serialize(inflection);
			try {
				if (words.add(word)) {
					count.incrementAndGet();
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
		System.out.println("Count:" + count.get());
	}
}