package dk.xpreuss.utils;

import java.util.Locale;
import java.util.MissingResourceException;

public class StandardOutput {
	private static final Locale DANISH_LOCALE = Locale.forLanguageTag("da-Latn-DK");
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final Locale DEFAULT_LOCALE = Locale.ITALY;

	public static void main(String[] args) {
		printLocale(DANISH_LOCALE);
	}

	public static void printLocale(Locale locale) {
		System.out.println("--- Printing Locale ---");
		System.out.println("ToString === " + locale);
		System.out.println("Country: " + locale.getCountry());
		System.out.println("Language: " + locale.getLanguage());
		System.out.println("Variant: " + locale.getVariant());
		System.out.println("Script: " + locale.getScript());
		try {
			System.out.println("ISO3 Country: " + locale.getISO3Country());
		} catch (MissingResourceException e) {
			System.out.println("ISO3 Country: === No ISO3 COUNTRY ===");
		}
		System.out.println("ISO3 Language: " + locale.getISO3Language());
		System.out.println("Display Country: " + locale.getDisplayCountry());
		System.out.println("Display Language: " + locale.getDisplayLanguage());
		System.out.println("Display Variant: " + locale.getDisplayVariant());
		System.out.println("Display Name: " + locale.getDisplayName());
		System.out.println("Display Script: " + locale.getDisplayScript());
		System.out.println("Has Extension ? " + (locale.hasExtensions() ? "YES" : "NO"));


		System.out.println("--- Ending Printing Locale ---");
	}
}
