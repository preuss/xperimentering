package dk.xpreuss.utils.formatter.parser5;

import dk.xpreuss.utils.formatter.Message;
import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;
import dk.xpreuss.utils.formatter.parser5.unicode.UString;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class StringReplaceFormat {
	private final CodePointSequence rawMessageSequence;

	private StringReplaceFormat(CodePointSequence rawMessageSequence) {
		this.rawMessageSequence = Objects.requireNonNull(rawMessageSequence);
	}

	private StringReplaceFormat(String rawMessageString) {
		this(new UString(rawMessageString));
	}

	public String format(Object[] arguments, StringBuilder result) {
		return null;
	}

	public String format(Object[] arguments) {
		return null;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("Testing Message Parser (PS. put this in test later)");

		String pattern = "First: {0}, Second: {1}";
		Object[] arguments = {"første", "anden", "tredje"};
		MessageFormat messageFormat = new MessageFormat(pattern);
		String output = MessageFormat.format(pattern, (Object[]) arguments);
		System.out.println("Output: " + output);

		Object[] parsed = messageFormat.parse("første, anden");

		//System.out.println("Output2: " + output2);
	}

	public static StringReplaceFormat from(CodePointSequence rawMessageCpSequence) {
		return new StringReplaceFormat(rawMessageCpSequence);
	}

	public static StringReplaceFormat from(UString rawMessageString) {
		return from((CodePointSequence) rawMessageString);
	}

	public static StringReplaceFormat from(String rawMessageString) {
		return new StringReplaceFormat(rawMessageString);
	}

	public static StringReplaceFormat from(CharSequence rawMessageCharSequence) {
		return from(new UString(rawMessageCharSequence));
	}

	public static String format(String rawMessageString, Object... arguments) {
		StringReplaceFormat formatter = new StringReplaceFormat(rawMessageString);
		return formatter.format(arguments);
	}
}
