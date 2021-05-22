package dk.xpreuss.utils.locales;

import java.util.Arrays;
import java.util.EnumSet;

public enum LocaleCalendarExtensions {
	SA("ar-SA-u-ca-arab"),
	AM("hy-AM-u-ca-arevmda"),
	CN("zh-TW-u-ca-hant"),
	CZ("cs-CZ-u-ca-latn"),
	DK("da-DK-u-ca-latn"),
	NL("nl-NL-u-ca-latn"),
	IE("ie-IE-u-ca-latn"),
	FR("fr-FR-u-ca-latn"),
	DE("de-DE-u-ca-latn"),
	GR("el-GR-u-ca-grek"),
	IL("iw-IL-u-ca-hebr"),
	IN("hi-IN-u-ca-hindu"),
	IT("it-IT-u-ca-latn"),
	JP("ja-JP-u-ca-jpan"),
	NO("nb-NO-u-ca-latn"),
	IR(""), //"fa-IR-u-ca-fars"),
	PL("pl-PL-u-ca-latn"),
	PT("pt-PT-u-ca-latn"),
	RU("ru-RU-u-ca-cyrl"),
	ES("es-ES-u-ca-latn"),
	SE("sv-SE-u-ca-latn"),
	TH("th-TH-u-ca-thai"),
	TR("tr-TR-u-ca-latn"),
	PK(""),//"ur-PK-u-ca-arab"),
	VN("vi-VN-u-ca-latn");

	String value;

	LocaleCalendarExtensions(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	static LocaleCalendarExtensions byValue(String val) {
		return Arrays.stream(values()).filter(v -> v.value.equals(val)).findFirst().orElse(null);
	}

	public static EnumSet<LocaleCalendarExtensions> getArabicSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(SA);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getJapanSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(JP);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getChinaSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getFarsiSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);

		retVal.add(IR);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getUrduSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(PK);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getAsianSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(JP);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getHebrewSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(IL);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getHinduSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(IN);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getThaiSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(TH);
		return retVal;
	}

	public static EnumSet<LocaleCalendarExtensions> getGreekSupport() {
		final EnumSet<LocaleCalendarExtensions> retVal = EnumSet.noneOf(LocaleCalendarExtensions.class);
		retVal.add(GR);
		return retVal;
	}
}