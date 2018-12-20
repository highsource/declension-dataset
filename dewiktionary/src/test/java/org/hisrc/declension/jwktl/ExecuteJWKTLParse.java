package org.hisrc.declension.jwktl;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.tudarmstadt.ukp.jwktl.JWKTL;

public class ExecuteJWKTLParse {

	private final static String PATH_TO_DUMP_FILE = "data/dewiktionary-20181201-pages-articles.xml.bz2";
	private final static String TARGET_DIRECTORY = "dumps/dump20181201";

	@Test
	public void parses() throws IOException {
		File dumpFile = new File(PATH_TO_DUMP_FILE);
		File outputDirectory = new File(TARGET_DIRECTORY);
		if (outputDirectory.isDirectory()) {
			for (File file : outputDirectory.listFiles()) {
				file.delete();
			}
			outputDirectory.delete();
		}
		boolean overwriteExisting = false;
		JWKTL.parseWiktionaryDump(dumpFile, outputDirectory, overwriteExisting);
	}
}