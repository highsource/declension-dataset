package org.hisrc.dereko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum STTSOberklasse {
	@JsonProperty("ADJ")
	ADJEKTIV,
	@JsonProperty("ADV")
	ADVERB,
	@JsonProperty("AP")
	ADPOSITION,
	@JsonProperty("ART")
	ARTIKEL,
	@JsonProperty("CARD")
	KARDINALZAHL,
	@JsonProperty("ITJ")
	INTERJEKTION,
	@JsonProperty("KO")
	KONJUNKTION,
	@JsonProperty("N")
	NOMINA,
	@JsonProperty("P")
	PRONOMINA,
	@JsonProperty("PTK")
	PARTIKEL,
	@JsonProperty("V")
	VERB,
	@JsonProperty("FM")
	FREMDSPRACHLICHES_MATERIAL,
	@JsonProperty("TRUNC")
	KOMPOSITIONS_ERSTGLIED,
	@JsonProperty("XY")
	NICHTWORT,
	@JsonProperty("$")
	INTERPUNKTION
}
