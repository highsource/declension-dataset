package org.hisrc.dereko;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class DerewoWordBasisFormListProviderTest {

	public static final String DEREWO_FILE = "data/derewo-v-ww-bll-320000g-2012-12-31-1.0.txt";

	@Test
	public void parses() throws IOException {
		try (InputStream is = new FileInputStream(new File(DEREWO_FILE))) {
			final DerewoWordBasisFormListProvider provider = new DerewoWordBasisFormListProvider(is);

			provider.loadWordList(entry -> {
				System.out.println(entry.getWord() + " -> " + entry.getFrequencyClass());
			});
		}
	}
}
