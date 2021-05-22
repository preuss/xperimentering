package dk.xpreuss.utils.locales;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.DecimalFormatSymbols;
import com.ibm.icu.text.NumberFormat;

import java.util.Arrays;
import java.util.Locale;

/**
 * See https://stackoverflow.com/questions/43456068/java-locale-builder-setextensionlocale-unicode-locale-extension
 */
public class NumberHelper {
	/**
	 * @param country where lang code provider is ar en cn fr ur it is as per LocaleCalendarExtensions Enum main declarations
	 * @param date    given date
	 * @param format  definition in which case I have clause to deal with HH:mm and so on just read through below code
	 * @return
	 */
	public static String convertDate(String country, java.util.Date date, String format) {
		StringBuilder output = new StringBuilder();
		if (country != null && date != null) {

			var found = Arrays.stream(LocaleICUCalendarExtensions.values()).anyMatch(it -> it.name().equals(country));
			if (found) {
				var found1 = LocaleNumberExtensions.valueOf(country);
				com.ibm.icu.util.ULocale locale = new com.ibm.icu.util.ULocale(found1.value);
				com.ibm.icu.util.Calendar calendar = com.ibm.icu.util.Calendar.getInstance(locale);
				calendar.setTime(date);
				com.ibm.icu.text.DateFormat df;
				if (format.equals("HH:mm")) {
					df = com.ibm.icu.text.DateFormat.getPatternInstance(com.ibm.icu.text.DateFormat.HOUR_MINUTE, locale);
				} else {
					if (format.equals("dd MMM yyyy HH:mm:ss")) {
						df = com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.FULL, locale);
					} else if (format.equals("dd MMM")) {
						df = com.ibm.icu.text.DateFormat.getPatternInstance(com.ibm.icu.text.DateFormat.ABBR_MONTH_DAY, locale);
					} else {
						df = com.ibm.icu.text.DateFormat.getDateInstance(DateFormat.LONG, locale);
					}
				}
				output.append(df.format(calendar));
			}
		}
		return output.toString();
	}

	/**
	 * Converts number to given locale
	 *
	 * @param country
	 * @param number
	 * @return
	 */
	public static String convertNumber(String country, Number number) {
		String output = null;
		if (country != null) {
			var found = Arrays.stream(LocaleNumberExtensions.values()).filter(it -> it.name().equals(country)).findFirst();
			var locale = found.map(it -> new Locale.Builder().setLanguageTag(it.value).build());

			boolean arabic = LocaleCalendarExtensions.getArabicSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean china = LocaleCalendarExtensions.getChinaSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean japan = LocaleCalendarExtensions.getJapanSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean farsi = LocaleCalendarExtensions.getFarsiSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean urdu = LocaleCalendarExtensions.getUrduSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean hebrew = LocaleCalendarExtensions.getHebrewSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean greek = LocaleCalendarExtensions.getGreekSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean hindu = LocaleCalendarExtensions.getHinduSupport().stream().anyMatch(x -> x.name().equals(country));
			boolean thai = LocaleCalendarExtensions.getThaiSupport().stream().anyMatch(x -> x.name().equals(country));

			if (arabic || hindu | thai || farsi || urdu) {
				if (found.isPresent()) {
					//Locale locale = new Locale.Builder().setLanguageTag(found.get().value).build();
					DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale.get());
					NumberFormat numberFormat = NumberFormat.getNumberInstance(locale.get());
					Number numbers;
					if (number.toString().indexOf('.') > -1) {
						numbers = number.doubleValue();
					} else {
						numbers = number.longValue();
					}
					output = numberFormat != null ? numberFormat.format(numbers) : "";
				}
			} else if (japan | china || hebrew || greek) {
				// to extend look up types here
				//http://www.atetric.com/atetric/javadoc/com.ibm.icu/icu4j/49.1/src-html/com/ibm/icu/util/ULocale.html
				//http://icu-project.org/~yoshito/jacoco_57.1/com.ibm.icu.util/ULocale.java.html
				com.ibm.icu.util.ULocale uLocale = null;

				if (japan) {
					uLocale = new com.ibm.icu.util.ULocale("ja_JP_JP");//ja_JP_JP  //
				}
				if (china) {
					uLocale = new com.ibm.icu.util.ULocale("zh_Hans");//zh_CN_TRADITIONAL@collation=pinyin;
				}
				if (hebrew) {
					uLocale = new com.ibm.icu.util.ULocale("he_IL");

				}
				if (greek) {
					uLocale = new com.ibm.icu.util.ULocale("el_GR");
				}
				com.ibm.icu.text.NumberFormat nf = com.ibm.icu.text.NumberFormat.getInstance(uLocale);
				Number numbers;
				if (number.toString().indexOf('.') > -1) {
					numbers = number.doubleValue();
				} else {
					numbers = number.longValue();
				}
				output = nf.format(numbers);
			} else if (found.isPresent()) {
				DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale.get());
				NumberFormat numberFormat = NumberFormat.getNumberInstance(locale.get());
				Number numbers;
				if (number.toString().indexOf('.') > -1) {
					numbers = number.doubleValue();
				} else {
					numbers = number.longValue();
				}
				output = numberFormat != null ? numberFormat.format(numbers) : "";
			}
		}
		return output != null ? output : number.toString();
	}
}
