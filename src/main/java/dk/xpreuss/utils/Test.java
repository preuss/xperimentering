package dk.xpreuss.utils;

import com.ibm.icu.util.ULocale;
import dk.xpreuss.utils.locales.NumberHelper;
import dk.xpreuss.utils.locales.LocaleCalendarExtensions;
import dk.xpreuss.utils.locales.LocaleNumberExtensions;

import java.util.Date;
import java.util.Locale;

public class Test {
	public static void main(String[] args) {

		Locale locale = Locale.forLanguageTag(LocaleNumberExtensions.DK.getValue());
		System.out.println(locale);

		String x = NumberHelper.convertDate(LocaleCalendarExtensions.DK.toString(), new Date(), "HH:mm");
		System.out.println("X: " + x);

		String num = NumberHelper.convertNumber(LocaleCalendarExtensions.DK.name(), 44455.44);
		System.out.println("Num: " + num);

		System.out.println("Locale bulgarien bg: " + Locale.forLanguageTag("bg"));
		Locale[] availableLocales = Locale.getAvailableLocales();
		for (int i = 0; i < availableLocales.length; i++) {
			Locale availableLocale = availableLocales[i];
			//System.out.println(availableLocale);
		}
		StandardOutput.printLocale(Locale.forLanguageTag("bg-Cyrl-BG"));
		System.out.println(ULocale.forLanguageTag("bg-Cyrl-BG"));
		ULocale uLocale = ULocale.forLanguageTag("bg-Cyrl-BG");
		ULocale uLocale1 = ULocale.acceptLanguage("bg,da", null);
		System.out.println("Ulocale 1: " + uLocale1);
		uLocale1 = ULocale.forLocale(Locale.forLanguageTag("bg-Cyrl-BG"));
		System.out.println("Ulocale 1: " + uLocale1);

		System.out.println(": " + ULocale.forLanguageTag("ar_SA@calendar=islamic"));
		uLocale1 = new ULocale("ar_SA@calendar=islamic");
		System.out.println(" : " + uLocale1);
		System.out.println(new ULocale(LocaleNumberExtensions.DK.getValue()));
		System.out.println(new Locale(LocaleNumberExtensions.DK.getValue()));
		System.out.println(new Locale("da-DK-u-ca-islamic"));
		System.out.println(new ULocale("da-DK-u-ca-islamic"));
		Locale[] locales = Locale.getAvailableLocales();
		for (int i = 0; i < locales.length; i++) {
			Locale locale1 = locales[i];
			if (locale1.getScript() != null && !locale1.getScript().equals("")) {
				StandardOutput.printLocale(locale1);
			}
		}

		StandardOutput.printLocale(Locale.forLanguageTag(LocaleCalendarExtensions.DK.getValue()));
		StandardOutput.printLocale(Locale.forLanguageTag(LocaleNumberExtensions.DK.getValue()));
		StandardOutput.printLocale(Locale.forLanguageTag("da-DK-u-nu-latn-ca-latn"));
		System.out.println(ULocale.forLanguageTag(LocaleCalendarExtensions.DK.getValue()));
		System.out.println(ULocale.forLanguageTag(LocaleNumberExtensions.DK.getValue()));
		System.out.println(ULocale.forLanguageTag("da-DK-u-nu-latn-ca-latn"));
	}
}
