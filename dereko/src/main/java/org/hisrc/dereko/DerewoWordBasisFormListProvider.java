package org.hisrc.dereko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.hisrc.dereko.dto.DerewoWordBasisFormListEntry;

public class DerewoWordBasisFormListProvider {

	private final InputStream is;

	public DerewoWordBasisFormListProvider(InputStream is) {
		this.is = is;
	}

	public void loadWordList(Consumer<DerewoWordBasisFormListEntry> consumer) throws IOException {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"))) {
			for (String line; (line = reader.readLine()) != null;) {
				if (line.startsWith("#")) {
					continue;
				}

				String[] fields = line.split(" ");

				if (fields.length < 2) {
					throw new AssertionError();
				}

				String word = fields[0];
				int frequencyClass = Integer.parseInt(fields[1]);
				consumer.accept(new DerewoWordBasisFormListEntry(word, frequencyClass));
			}
		}
	}
}
