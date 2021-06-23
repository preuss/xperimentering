package dk.xpreuss.utils.formatter.custom2;

import com.ibm.icu.util.ULocale;

import java.util.Formattable;
import java.util.Locale;
import java.util.Map;

public class Message2 {
	private final Locale locale;
	private final ULocale uLocale;
	private final String messagePattern;

	private final Map<String, Object> namedArguments;

	private Message2(Locale locale, ULocale uLocale, String messagePattern, Map<String, Object> namedArguments) {
		this.locale = locale;
		this.uLocale = uLocale;
		this.messagePattern = messagePattern;
		this.namedArguments = namedArguments;
	}

	private String applyMessagePattern(String messagePattern) {
		Formattable f;
		return null;
	}
}
