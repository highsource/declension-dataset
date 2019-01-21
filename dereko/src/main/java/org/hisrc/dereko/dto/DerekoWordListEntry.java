package org.hisrc.dereko.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({  "additional_form", "base_form", "part_of_speech", "frequency"})
public class DerekoWordListEntry {

	@JsonProperty("base_form")
	private String baseForm;
	@JsonProperty("additional_form")
	private String additionalForm;
	@JsonProperty("part_of_speech")
	private STTSUnterkategories partOfSpeech;
	@JsonProperty("frequency")
	private double frequency;
	
	@JsonCreator
	public DerekoWordListEntry(
			@JsonProperty("base_form")
			String baseForm,
			@JsonProperty("additional_form")
			String additionalForm,
			@JsonProperty("part_of_speech")
			STTSUnterkategories partOfSpeech,
			@JsonProperty("frequency")
			double frequency) {
		this.baseForm = baseForm;
		this.additionalForm = additionalForm;
		this.partOfSpeech = partOfSpeech;
		this.frequency = frequency;
	}
	public String getBaseForm() {
		return baseForm;
	}
	public String getAdditionalForm() {
		return additionalForm;
	}
	public STTSUnterkategories getPartOfSpeech() {
		return partOfSpeech;
	}
	public double getFrequency() {
		return frequency;
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.baseForm, this.additionalForm, this.partOfSpeech, this.frequency);
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
		DerekoWordListEntry that = (DerekoWordListEntry) object;
		return Objects.equals(this.baseForm, that.baseForm) &&
				Objects.equals(this.additionalForm, that.additionalForm) &&
				Objects.equals(this.partOfSpeech, that.partOfSpeech) &&
				Double.doubleToLongBits(frequency) == Double.doubleToLongBits(that.frequency);
	}

	@Override
	public String toString() {
		return "DerekoWordListEntry ["
				+ "baseForm=" + baseForm + ", "
				+ "additionalForm=" + additionalForm + ", "
				+ "partOfSpeech=" + partOfSpeech + ", "
				+ "frequency=" + frequency + "]";
	}
}