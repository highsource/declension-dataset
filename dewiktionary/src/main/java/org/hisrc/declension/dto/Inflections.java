package org.hisrc.declension.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Inflections {

	private final String word;
	private final List<Inflection> inflections;

	public Inflections(final String word, List<Inflection> inflections) {
		Objects.requireNonNull(word, "word must not be null");
		Objects.requireNonNull(inflections, "inflections must not be null");
		this.word = word;
		this.inflections = Collections.unmodifiableList(new ArrayList<>(inflections));
	}

	@JsonProperty("word")
	public String getWord() {
		return word;
	}

	@JsonProperty("inflections")
	public List<Inflection> getInflections() {
		return inflections;
	}
}