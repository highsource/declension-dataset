package org.hisrc.declension.jwktl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.hisrc.declension.dto.GrammaticalCaseSuffices;
import org.hisrc.declension.dto.Inflection;
import org.hisrc.declension.dto.InflectionGroup;
import org.hisrc.declension.jackson.databind.GenericJsonSerializer;
import org.hisrc.declension.jwktl.WiktionaryEntryInflectionProducer;
import org.junit.Test;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;

public class ExecuteWiktionaryEntryInflectionProducer {

	private final static String TARGET_DIRECTORY = "dumps/dump20181201";

	@Test
	public void processes() throws IOException {

		File wiktionaryDirectory = new File(TARGET_DIRECTORY);

		File outputFile = new File("dataset.json");
		final GenericJsonSerializer<Inflection> serializer = new GenericJsonSerializer<>(Inflection.class, outputFile);
		serializer.start();

		final Map<GrammaticalCaseSuffices, AtomicInteger> singularSuffices = new HashMap<>();
		final Map<GrammaticalCaseSuffices, AtomicInteger> pluralSuffices = new HashMap<>();

		try (IWiktionaryEdition wkt = JWKTL.openEdition(wiktionaryDirectory)) {
			final WiktionaryEntryInflectionProducer producer = new WiktionaryEntryInflectionProducer(wkt);
			producer.process(inflection -> {
				serializer.serialize(inflection);
				inflection.getSingular().stream().map(InflectionGroup::getSufficies).forEach(
						s -> singularSuffices.computeIfAbsent(s, key -> new AtomicInteger()).incrementAndGet());
				inflection.getPlural().stream().map(InflectionGroup::getSufficies).forEach(
						s -> pluralSuffices.computeIfAbsent(s, key -> new AtomicInteger()).incrementAndGet());
			});
		}
		serializer.end();
		singularSuffices.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().get()));
		System.out.println();
		pluralSuffices.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().get()));
	}
}