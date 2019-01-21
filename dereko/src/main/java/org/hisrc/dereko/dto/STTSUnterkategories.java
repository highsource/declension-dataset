package org.hisrc.dereko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// Quellen:
// * http://www.ims.uni-stuttgart.de/forschung/ressourcen/lexika/TagSets/stts-table.html
// * https://www.linguistik.hu-berlin.de/de/institut/professuren/korpuslinguistik/mitarbeiter-innen/hagen/STTS_Tagset_Tiger
// * http://www.kiezdeutschkorpus.de/files/kidko/downloads/POS-KiDKo.pdf
public enum STTSUnterkategories {

	// ADJA | attributives Adjektiv | [das] große [Haus]
	@JsonProperty("ADJA")
	ATTRIBUTIVES_ADJEKTIV(STTSOberklasse.ADJEKTIV),

	// ADJD | adverbiales oder prädikatives Adjektiv | [er fährt] schnell, [er ist]
	// schnell
	@JsonProperty("ADJD")
	ADVERBIALES_ODER_PRAEDIKATIVES_ADJEKTIV(STTSOberklasse.ADJEKTIV),

	// ADV | Adverb | schon, bald, doch
	@JsonProperty("ADV")
	ADVERB(STTSOberklasse.ADVERB),

	// APPR | Präposition; Zirkumposition links | in [der Stadt], ohne [mich]
	@JsonProperty("APPR")
	PRAEPOSITION_ZIRKUMPOSITION_LINKS(STTSOberklasse.ADPOSITION),

	// APPRART | Präposition mit Artikel | im [Haus], zur [Sache]
	@JsonProperty("APPRART")
	PRAEPOSITION_MIT_ARTIKEL(STTSOberklasse.ADPOSITION),

	// APPO | Postposition | [ihm] zufolge, [der Sache] wegen
	@JsonProperty("APPO")
	POSTPOSITION(STTSOberklasse.ADPOSITION),

	// APZR | Zirkumposition rechts | [von jetzt] an
	@JsonProperty("APZR")
	ZIRKUMPOSITION_RECHTS(STTSOberklasse.ADPOSITION),

	// ART | bestimmter oder unbestimmter Artikel | der, die, das, ein, eine
	@JsonProperty("ART")
	BESTIMMTER_ODER_UNBESTIMMTER_ARTIKEL(STTSOberklasse.ARTIKEL),

	// CARD | Kardinalzahl | zwei [Männer], [im Jahre] 1994
	@JsonProperty("CARD")
	KARDINALZAHL(STTSOberklasse.KARDINALZAHL),

	// FM | Fremdsprachliches Material | [Er hat das mit ``] A big fish [''
	// übersetzt]
	@JsonProperty("FM")
	FREMDSPRACHLICHES_MATERIAL(STTSOberklasse.FREMDSPRACHLICHES_MATERIAL),

	// ITJ | Interjektion | mhm, ach, tja
	@JsonProperty("ITJ")
	INTERJEKTION(STTSOberklasse.INTERJEKTION),

	// KOUI | unterordnende Konjunktion mit ``zu'' und Infinitiv | um [zu leben],
	// anstatt [zu fragen]
	@JsonProperty("KOUI")
	UNTERORDNENDE_KONJUNKTION_MIT_ZU_UND_INFINITIV(STTSOberklasse.KONJUNKTION),

	// KOUS | unterordnende Konjunktion mit Satz | weil, dass, damit, wenn, ob
	@JsonProperty("KOUS")
	UNTERORDNENDE_KONJUNKTION_MIT_SATZ(STTSOberklasse.KONJUNKTION),

	// KON | nebenordnende Konjunktion | und, oder, aber
	@JsonProperty("KON")
	NEBENORDNENDE_KONJUNKTION(STTSOberklasse.KONJUNKTION),

	// KOKOM | Vergleichskonjunktion | als, wie
	@JsonProperty("KOKOM")
	VERGLEICHSKONJUNKTION(STTSOberklasse.KONJUNKTION),

	// NN | normales Nomen | Tisch, Herr, [das] Reisen
	@JsonProperty("NN")
	NORMALES_NOMEN(STTSOberklasse.NOMINA),

	// NE | Eigennamen | Hans, Hamburg, HSV
	@JsonProperty("NE")
	EIGENNAMEN(STTSOberklasse.NOMINA),

	// PDS | substituierendes Demonstrativpronomen | dieser, jener
	@JsonProperty("PDS")
	SUBSTITUIERENDES_DEMONSTRATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PDAT | attribuierendes Demonstrativpronomen | jener [Mensch]
	@JsonProperty("PDAT")
	ATTRIBUIERENDES_DEMONSTRATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PIS | substituierendes Indefinitpronomen | keiner, viele, man, niemand
	@JsonProperty("PIS")
	SUBSTITUIERENDES_INDEFINITPRONOMEN(STTSOberklasse.PRONOMINA),

	// PIAT | attribuierendes Indefinitpronomen ohne Determiner | kein [Mensch],
	// irgendein [Glas]
	@JsonProperty("PIAT")
	ATTRIBUIERENDES_INDEFINITPRONOMEN_OHNE_DETERMINER(STTSOberklasse.PRONOMINA),

	// PIDAT | attribuierendes Indefinitpronomen mit Determiner | [ein] wenig
	// [Wasser], [die] beiden [Brüder]
	@JsonProperty("PIDAT")
	ATTRIBUIERENDES_INDEFINITPRONOMEN_MIT_DETERMINER(STTSOberklasse.PRONOMINA),

	// PPER | irreflexives Personalpronomen | ich, er, ihm, mich, dir
	@JsonProperty("PPER")
	IRREFLEXIVES_PERSONALPRONOMEN(STTSOberklasse.PRONOMINA),

	// PPOSS | substituierendes Possessivpronomen | meins, deiner
	@JsonProperty("PPOSS")
	SUBSTITUIERENDES_POSSESSIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PPOSAT | attribuierendes Possessivpronomen | mein [Buch], deine [Mutter]
	@JsonProperty("PPOSAT")
	ATTRIBUIERENDES_POSSESSIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PRELS | substituierendes Relativpronomen | [der Hund ,] der
	@JsonProperty("PRELS")
	SUBSTITUIERENDES_RELATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PRELAT | attribuierendes Relativpronomen | [der Mann ,] dessen [Hund]
	@JsonProperty("PRELAT")
	ATTRIBUIERENDES_RELATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PROAV | Pronominaladverb | Deswegen/PROAV sprechen wir darüber/PROAV
	@JsonProperty("PROAV")
	PRONOMINALADVERB_1(STTSOberklasse.PRONOMINA),

	// PRF | reflexives Personalpronomen | sich, einander, dich, mir
	@JsonProperty("PRF")
	REFLEXIVES_PERSONALPRONOMEN(STTSOberklasse.PRONOMINA),

	// PWS | substituierendes Interrogativpronomen | wer, was
	@JsonProperty("PWS")
	SUBSTITUIERENDES_INTERROGATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PWAT | attribuierendes Interrogativpronomen | welche[Farbe], wessen [Hut]
	@JsonProperty("PWAT")
	ATTRIBUIERENDES_INTERROGATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PWAV | adverbiales Interrogativ- oder Relativpronomen | warum, wo, wann,
	// worüber, wobei
	@JsonProperty("PWAV")
	ADVERBIALES_INTERROGATIV_ODER_RELATIVPRONOMEN(STTSOberklasse.PRONOMINA),

	// PAV | Pronominaladverb | dafür, dabei, deswegen, trotzdem
	@JsonProperty("PAV")
	PRONOMINALADVERB(STTSOberklasse.PRONOMINA),

	// PTKZU | ``zu'' vor Infinitiv | zu [gehen]
	@JsonProperty("PTKZU")
	ZU_VOR_INFINITIV(STTSOberklasse.PARTIKEL),

	// PTKNEG | Negationspartikel | nicht
	@JsonProperty("PTKNEG")
	NEGATIONSPARTIKEL(STTSOberklasse.PARTIKEL),

	// PTKVZ | abgetrennter Verbzusatz | [er kommt] an, [er fährt] rad
	@JsonProperty("PTKVZ")
	ABGETRENNTER_VERBZUSATZ(STTSOberklasse.PARTIKEL),

	// PTKANT | Antwortpartikel | ja, nein, danke, bitte
	@JsonProperty("PTKANT")
	ANTWORTPARTIKEL(STTSOberklasse.PARTIKEL),

	// PTKA | Partikel bei Adjektiv oder Adverb | am [schönsten], zu [schnell]
	@JsonProperty("PTKA")
	PARTIKEL_BEI_ADJEKTIV_ODER_ADVERB(STTSOberklasse.PARTIKEL),

	// TRUNC | Kompositions-Erstglied | An- [und Abreise]
	@JsonProperty("TRUNC")
	KOMPOSITIONS_ERSTGLIED(STTSOberklasse.KOMPOSITIONS_ERSTGLIED),

	// VVFIN | finites Verb, voll | [du] gehst, [wir] kommen [an]
	@JsonProperty("VVFIN")
	FINITES_VERB_VOLL(STTSOberklasse.VERB),

	// VVIMP | Imperativ, voll | komm [!]
	@JsonProperty("VVIMP")
	IMPERATIV_VOLL(STTSOberklasse.VERB),

	// VVINF | Infinitiv, voll | gehen, ankommen
	@JsonProperty("VVINF")
	INFINITIV_VOLL(STTSOberklasse.VERB),

	// VVIZU | Infinitiv mit ``zu'', voll | anzukommen, loszulassen
	@JsonProperty("VVIZU")
	INFINITIV_MIT_ZU_VOLL(STTSOberklasse.VERB),

	// VVPP | Partizip Perfekt, voll | gegangen, angekommen
	@JsonProperty("VVPP")
	PARTIZIP_PERFEKT_VOLL(STTSOberklasse.VERB),

	// VAFIN | finites Verb, aux | [du] bist, [wir] werden
	@JsonProperty("VAFIN")
	FINITES_VERB_AUX(STTSOberklasse.VERB),

	// VAIMP | Imperativ, aux | sei [ruhig !]
	@JsonProperty("VAIMP")
	IMPERATIV_AUX(STTSOberklasse.VERB),

	// VAINF | Infinitiv, aux | werden, sein
	@JsonProperty("VAINF")
	INFINITIV_AUX(STTSOberklasse.VERB),

	// VAPP | Partizip Perfekt, aux | gewesen
	@JsonProperty("VAPP")
	PARTIZIP_PERFEKT_AUX(STTSOberklasse.VERB),

	// VMFIN | finites Verb, modal | dürfen
	@JsonProperty("VMFIN")
	FINITES_VERB_MODAL(STTSOberklasse.VERB),

	// VMINF | Infinitiv, modal | wollen
	@JsonProperty("VMINF")
	INFINITIV_MODAL(STTSOberklasse.VERB),

	// VMPP | Partizip Perfekt, modal | gekonnt, [er hat gehen] können
	@JsonProperty("VMPP")
	PARTIZIP_PERFEKT_MODAL(STTSOberklasse.VERB),

	// XY | Nichtwort, Sonderzeichen enthaltend | 3:7, H2O, D2XW3
	@JsonProperty("XY")
	NICHTWORT_SONDERZEICHEN_ENTHALTEND(STTSOberklasse.NICHTWORT),

	// $, | Komma | ,
	@JsonProperty("$,")
	KOMMA(STTSOberklasse.INTERPUNKTION),

	// $. | Satzbeendende Interpunktion | . ? ! ; :
	@JsonProperty("$.")
	SATZBEENDENDE_INTERPUNKTION(STTSOberklasse.INTERPUNKTION),

	// $( | sonstige Satzzeichen; satzintern | - [,]()
	@JsonProperty("$(")
	SONSTIGE_SATZZEICHEN_SATZINTERN(STTSOberklasse.INTERPUNKTION);

	private final STTSOberklasse oberklasse;

	private STTSUnterkategories(STTSOberklasse oberklasse) {
		this.oberklasse = oberklasse;
	}
	
	public STTSOberklasse getOberklasse() {
		return oberklasse;
	}
}
