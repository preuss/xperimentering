package dk.xpreuss.utils.locales;

import java.util.Arrays;
import java.util.EnumSet;

public enum LocaleICUCalendarExtensions {
	SA("ar_SA@calendar=islamic"),
	AM("hy_AM@calendar=armenian"),
	CN("zh_Hans@calendar=chinese"),
	CZ("cs_CZ@calendar=latin"),
	DK("da_DK@calendar=latin"),
	NL("nl_NL@calendar=latin"),
	IE("ie_IE@calendar=latin"),
	FR("fr_FR@calendar=latin"),
	DE("de_DE@calendar=latin"),
	GR("el_GR@calendar=greek"),
	IL("iw_IL@calendar=hebrew"),
	IN("hi_IN@calendar=hindu"),
	IT("it_IT@calendar=Latin"),
	JP("ja_JP_TRADITIONAL@calendar=japanese"),
	NO("nb_NO@calendar=latin"),
	IR("fa_IR@calendar=persian"),
	PL("pl_PL@calendar=latin"),
	PT("pt_PT@calendar=latin"),
	RU("ru_RU@calendar=cyrillic"),
	ES("es_ES@calendar=latin"),
	SE("sv_SE@calendar=latin"),
	TH("th_TH_TRADITIONAL@calendar=buddhist"),
	TR("tr_TR@calendar=latin"),
	PK("ur_PK@calendar=pakistan"),
	VN("vi_VN@calendar=latin");

	String value;

	LocaleICUCalendarExtensions(String val) {
		this.value = val;
	}

	public String getValue() {
		return value;
	}

	static LocaleICUCalendarExtensions byValue(String val) {
		return Arrays.stream(values()).filter(v -> v.value.equals(val)).findFirst().orElse(null);
	}

	public static EnumSet<LocaleICUCalendarExtensions> getArabicSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(SA);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getJapanSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(JP);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getChinaSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(CN);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getFarsiSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);

		retVal.add(IR);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getUrduSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(PK);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getHebrewSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(IL);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getHinduSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(IN);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getThaiSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(TH);
		return retVal;
	}

	public static EnumSet<LocaleICUCalendarExtensions> getGreekSupport() {
		final EnumSet<LocaleICUCalendarExtensions> retVal = EnumSet.noneOf(LocaleICUCalendarExtensions.class);
		retVal.add(GR);
		return retVal;
	}
}