package org.hisrc.dereko.dto;

public class DerewoWordBasisFormListEntry {

	private final String word;
	private final int frequencyClass;

	public DerewoWordBasisFormListEntry(String word, int frequencyClass) {
		this.word = word;
		this.frequencyClass = frequencyClass;
	}

	public String getWord() {
		return word;
	}

	public int getFrequencyClass() {
		return frequencyClass;
	}
}
