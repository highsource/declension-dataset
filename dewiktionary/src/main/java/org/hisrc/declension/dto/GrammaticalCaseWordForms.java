package org.hisrc.declension.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GrammaticalCaseWordForms {

	private final List<String> nominative;
	private final List<String> genitive;
	private final List<String> dative;
	private final List<String> accusative;

	public GrammaticalCaseWordForms(List<String> nominative, List<String> genitive, List<String> dative,
			List<String> accusative) {
		this.nominative = nominative == null ? null : Collections.unmodifiableList(new ArrayList<>(nominative));
		this.genitive = genitive == null ? null : Collections.unmodifiableList(new ArrayList<>(genitive));
		this.dative = dative == null ? null : Collections.unmodifiableList(new ArrayList<>(dative));
		this.accusative = accusative == null ? null : Collections.unmodifiableList(new ArrayList<>(accusative));
	}

	@JsonProperty("nominative")
	public List<String> getNominative() {
		return nominative;
	}

	@JsonProperty("genitive")
	public List<String> getGenitive() {
		return genitive;
	}

	@JsonProperty("dative")
	public List<String> getDative() {
		return dative;
	}

	@JsonProperty("accusative")
	public List<String> getAccusative() {
		return accusative;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nominative, this.genitive, this.dative, this.accusative);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		GrammaticalCaseWordForms that = (GrammaticalCaseWordForms) object;
		return Objects.equals(this.nominative, that.nominative) && Objects.equals(this.genitive, that.genitive)
				&& Objects.equals(this.dative, that.dative) && Objects.equals(this.accusative, that.accusative);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append('(');
		if (this.nominative != null) {
			sb.append(this.nominative.stream().map(item -> '-' + item).collect(Collectors.joining(",")));
		} else {
			sb.append("-");
		}
		sb.append('|');
		if (this.genitive != null) {
			sb.append(this.genitive.stream().map(item -> '-' + item).collect(Collectors.joining(",")));
		} else {
			sb.append("-");
		}
		sb.append('|');
		if (this.dative != null) {
			sb.append(this.dative.stream().map(item -> '-' + item).collect(Collectors.joining(",")));
		} else {
			sb.append("-");
		}
		sb.append('|');
		if (this.accusative != null) {
			sb.append(this.accusative.stream().map(item -> '-' + item).collect(Collectors.joining(",")));
		} else {
			sb.append("-");
		}
		sb.append(')');
		return sb.toString();
	}
}
