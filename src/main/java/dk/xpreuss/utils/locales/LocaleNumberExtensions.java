package dk.xpreuss.utils.locales;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;

public enum LocaleNumberExtensions {
	SA("ar-SA-u-nu-arab"),
	AM("hy-AM-u-nu-arevmda"),
	CN("zh-TW-u-nu-arab"), //"zh-TW-u-nu-hant"
	CZ("cs-CZ-u-nu-latn"),
	DK("da-DK-u-nu-latn"),
	NL("nl-NL-u-nu-latn"),
	IE("ie-IE-u-nu-latn"),
	FR("fr-FR-u-nu-latn"),
	DE("de-DE-u-nu-latn"),
	GR("el-GR-u-nu-grek"),
	IL("iw-IL-u-nu-hebr"),
	IN("hi-IN-u-nu-hindu"),
	IT("it-IT-u-nu-latn"),
	JP("ja-JP-u-nu-arab"),
	NO("nb-NO-u-nu-latn"),
	IR("fa-IR-u-nu-arab"),
	PL("pl-PL-u-nu-latn"),
	PT("pt-PT-u-nu-latn"),
	RU("ru-RU-u-nu-cyrl"),
	ES("es-ES-u-nu-latn"),
	SE("sv-SE-u-nu-latn"),
	TH("th-TH-u-nu-thai"),
	TR("tr-TR-u-nu-latn"),
	PK("ur-PK-u-nu-arab"),
	VN("vi-VN-u-nu-latn");

	String value;

	LocaleNumberExtensions(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	static LocaleNumberExtensions byValue(String val) {
		return Arrays.stream(values()).filter(v -> v.value.equals(val)).findFirst().orElse(null);
	}

	static LocaleNumberExtensions byLang(String lang) {
		return Arrays.stream(values()).filter(it -> it.value.substring(0, 2).equals(lang)).findAny().orElse(null);
	}

	public static EnumSet<LocaleNumberExtensions> getArabicSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(SA);
		//TODO
		retVal.add(JP);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getFarsiSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(PK);
		retVal.add(IR);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getAsianSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(JP);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getHebrewSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(IL);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getHinduSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(IN);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getThaiSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(TH);
		return retVal;
	}

	public static EnumSet<LocaleNumberExtensions> getGreekSupport() {
		final EnumSet<LocaleNumberExtensions> retVal = EnumSet.noneOf(LocaleNumberExtensions.class);
		retVal.add(GR);
		return retVal;
	}
}