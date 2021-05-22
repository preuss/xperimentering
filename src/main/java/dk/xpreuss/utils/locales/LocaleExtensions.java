package dk.xpreuss.utils.locales;

import com.ibm.icu.util.ULocale;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;

public enum LocaleExtensions {
	SA("ar-SA-u-ca-arab-nu-arab"),
	AM("hy-AM-u-ca-arevmda-nu-arevmda"),
	CN("zh-TW-u-ca-nu-arab"), //"zh-TW-u-nu-hant"
	CZ("cs-CZ-u-ca-latn-nu-latn"),
	DK("da-DK-u-ca-latn-nu-latn"),
	NL("nl-NL-u-ca-latn-nu-latn"),
	IE("ie-IE-u-ca-latn-nu-latn"),
	FR("fr-FR-u-ca-latn-nu-latn"),
	DE("de-DE-u-ca-latn-nu-latn"),
	GR("el-GR-u-ca-latn-nu-grek"),
	IL("iw-IL-u-ca-latn-nu-hebr"),
	IN("hi-IN-u-ca-latn-nu-hindu"),
	IT("it-IT-u-ca-latn-nu-latn"),
	JP("ja-JP-u-ca-jpan-nu-arab"),
	NO("nb-NO-u-ca-latn-nu-latn"),
	IR("fa-IR-u-nu-arab"),
	PL("pl-PL-u-ca-latn-nu-latn"),
	PT("pt-PT-u-ca-latn-nu-latn"),
	RU("ru-RU-u-ca-cyrl-nu-cyrl"),
	ES("es-ES-u-ca-latn-nu-latn"),
	SE("sv-SE-u-ca-latn-nu-latn"),
	TH("th-TH-u-ca-thai-nu-thai"),
	TR("tr-TR-u-ca-latn-nu-latn"),
	PK("ur-PK-u-nu-arab"),
	VN("vi-VN-u-ca-latn-nu-latn");

	private final String value;
	private final Locale locale;
	private final ULocale uLocale;

	LocaleExtensions(String ietfBcp47LanguageTag) {
		this.value = ietfBcp47LanguageTag;
		this.locale = Locale.forLanguageTag(ietfBcp47LanguageTag);
		this.uLocale = ULocale.forLanguageTag(ietfBcp47LanguageTag);
	}

	public String getValue() {
		return value;
	}

	public Locale getLocale() {
		return locale;
	}

	public ULocale getULocale() {
		return uLocale;
	}

	static LocaleExtensions byValue(String val) {
		return Arrays.stream(values()).filter(v -> v.value.equals(val)).findFirst().orElse(null);
	}

	static LocaleExtensions byLang(String lang) {
		return Arrays.stream(values()).filter(it -> it.value.substring(0, 2).equals(lang)).findAny().orElse(null);
	}

	public static EnumSet<LocaleExtensions> getArabicSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(SA);
		//TODO
		retVal.add(JP);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getFarsiSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(PK);
		retVal.add(IR);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getAsianSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(JP);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getHebrewSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(IL);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getHinduSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(IN);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getThaiSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(TH);
		return retVal;
	}

	public static EnumSet<LocaleExtensions> getGreekSupport() {
		final EnumSet<LocaleExtensions> retVal = EnumSet.noneOf(LocaleExtensions.class);
		retVal.add(GR);
		return retVal;
	}
}