package org.hisrc.wordlist.dereko;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import org.hisrc.dereko.DerekoWordListProvider;
import org.hisrc.dereko.dto.DerekoWordListEntry;
import org.hisrc.dereko.dto.STTSUnterkategories;
import org.junit.Test;

public class DerekoWordListProviderTest {

	public static final String DEREKO_FILE = "data/DeReKo-2014-II-MainArchive-STT.100000.freq";

	@Test
	public void parses() throws IOException {
		try (InputStream is = new FileInputStream(new File(DEREKO_FILE))) {
			final DerekoWordListProvider wordListProvider = new DerekoWordListProvider(is);
			final Set<String> nouns = new TreeSet<>();
			final Set<String> nounsLowercase = new HashSet<>();
			final AtomicInteger nounsCount = new AtomicInteger();
			wordListProvider.loadWordList(wordListEntry -> {
				DerekoWordListEntry derekoWordListEntry = (DerekoWordListEntry) wordListEntry;
				String word = derekoWordListEntry.getBaseForm();
				if (!nounsLowercase.contains(word.toLowerCase())
						&& word.indexOf('-') == -1
						&& word.indexOf('.') == -1
						&& word.indexOf('{') == -1
						&& word.indexOf('|') == -1
						&& word.indexOf('}') == -1
						&& word.indexOf(' ') == -1
						&& word.indexOf('/') == -1
						&& word.indexOf('#') == -1
						&& word.indexOf('$') == -1
						&& word.indexOf('%') == -1
						&& word.indexOf('*') == -1
						&& word.indexOf('+') == -1
						&& STTSUnterkategories.NORMALES_NOMEN == derekoWordListEntry.getPartOfSpeech()
						&& derekoWordListEntry.getFrequency() > 80000) {
					nounsCount.incrementAndGet();
					nouns.add(word);
					nounsLowercase.add(word.toLowerCase());
//					System.out.println(derekoWordListEntry.getBaseForm());
				}
			});
			System.out.println("Number of nouns:" + nouns.size());
			assertThat(nouns.size()).isGreaterThan(1000); 
			nouns.forEach(System.out::println);
		}
	}
}