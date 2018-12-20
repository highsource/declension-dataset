package org.hisrc.declension.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;

@JsonInclude(Include.NON_EMPTY)
public class InflectionGroup {

	private final GrammaticalGender grammaticalGender;
	private final GrammaticalCaseWordForms forms;

	public InflectionGroup(GrammaticalGender grammaticalGender, GrammaticalCaseWordForms sufficies) {
		this.grammaticalGender = grammaticalGender;
		this.forms = sufficies;
	}

	@JsonProperty("gender")
	public GrammaticalGender getGrammaticalGender() {
		return grammaticalGender;
	}

	@JsonProperty("forms")
	public GrammaticalCaseWordForms getForms() {
		return forms;
	}
}