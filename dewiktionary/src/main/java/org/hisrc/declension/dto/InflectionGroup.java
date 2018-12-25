package org.hisrc.declension.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;

@JsonInclude(Include.NON_EMPTY)
public class InflectionGroup {

	private final GrammaticalGender grammaticalGender;
	private final String root;
	private final GrammaticalCaseSuffices suffixes;

	public InflectionGroup(GrammaticalGender grammaticalGender, String root, GrammaticalCaseSuffices suffixes) {
		this.grammaticalGender = grammaticalGender;
		this.root = root;
		this.suffixes = suffixes;
	}

	@JsonProperty("gender")
	public GrammaticalGender getGrammaticalGender() {
		return grammaticalGender;
	}
	
	@JsonProperty("root")
	public String getRoot() {
		return root;
	}
	
	@JsonProperty("suffixes")
	public GrammaticalCaseSuffices getSuffixes() {
		return suffixes;
	}
}