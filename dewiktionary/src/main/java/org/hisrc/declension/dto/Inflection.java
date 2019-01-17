package org.hisrc.declension.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Inflection {

	private final String word;
	private final String determinatum;
	private final List<InflectionGroup> singular;
	private final List<InflectionGroup> plural;

	public Inflection(String word, List<InflectionGroup> singular, List<InflectionGroup> plural) {
		this(word, null, singular, plural);
	}

	public Inflection(final String word, String determinatum, List<InflectionGroup> singular, List<InflectionGroup> plural) {
		Objects.requireNonNull(word, "word must not be null");
		Objects.requireNonNull(singular, "singular must not be null");
		Objects.requireNonNull(plural, "plural must not be null");
		this.word = word;
		this.determinatum = determinatum;
		this.singular = Collections.unmodifiableList(new ArrayList<>(singular));
		this.plural = Collections.unmodifiableList(new ArrayList<>(plural));
	}

	@JsonProperty("word")
	public String getWord() {
		return word;
	}
	
	@JsonProperty("determinatum")
	public String getDeterminatum() {
		return determinatum;
	}
	
	@JsonProperty("singular")
	public List<InflectionGroup> getSingular() {
		return singular;
	}
	
	@JsonProperty("plural")
	public List<InflectionGroup> getPlural() {
		return plural;
	}
}