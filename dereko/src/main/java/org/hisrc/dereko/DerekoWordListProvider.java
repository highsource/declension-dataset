package org.hisrc.dereko;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.hisrc.dereko.dto.DerekoWordListEntry;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class DerekoWordListProvider {

	private final InputStream is;

	public DerekoWordListProvider(InputStream is) {
		super();
		this.is = is;
	}

	public void loadWordList(Consumer<DerekoWordListEntry> consumer) throws IOException {

		final CsvMapper mapper = new CsvMapper();
		final CsvSchema schema = mapper.schemaFor(DerekoWordListEntry.class).withoutHeader().withoutQuoteChar()
				.withColumnSeparator('\t');

		final MappingIterator<DerekoWordListEntry> wordListEntriesIterator = mapper.readerFor(DerekoWordListEntry.class)
				.with(schema).readValues(new InputStreamReader(is, "UTF-8"));
		while (wordListEntriesIterator.hasNext()) {
			consumer.accept(wordListEntriesIterator.next());
		}
	}

}
