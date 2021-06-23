package dk.xpreuss.utils.formatter;

import com.ibm.icu.text.MessageFormat;

import java.util.*;

/**
 * c# format
 * -    {paramIndex[,alignment][:formatString]}
 * -    {paramName[,alignment][:formatString]}
 * Java MessageFormat
 * -    {argumentIndex[,formatType[,formatStyle]}
 * -    {index[,type[,subformat]}
 * Java printf
 * -    % [flags] [width] [.precision] conversion-character
 * <p>
 * Where paramIndex or argumentIndex is the int count from 0 of arguments.
 * Where paramName or argumentName is the named parameter.
 *
 * <p>
 * paramIndex = a number (#)
 * paramName = and alphanumeric
 * <p>
 * Only one type in a string, a param index number or named parameters.
 */
public class StringFormatter {
	enum TagState {
		TEXT, TAG
	}

	enum QuoteState {
		NON_QUOTE, QUOTE
	}

	public static final long MAX_VALUE = 0xFFFFFFFFL;

	public static void main(String[] args) {
		String messagePattern = "Hello {name}, the world is {0}";
		Map params = new HashMap();
		params.put("name", "World!");
		params.put(0, "okay");
		System.out.println("Hello World: " + (Integer.MAX_VALUE + 1));
		System.out.println("Compare: " + Integer.compareUnsigned(1, 0));
		final int inputLength = messagePattern.length();

		TagState tagState = TagState.TEXT;
		QuoteState quoteState = QuoteState.NON_QUOTE;
		int subBraceTags = 0;

		for (String codePoint : new StringCodepointsIterable(messagePattern)) {
			if (tagState == TagState.TEXT) {
				switch (codePoint) {
					case "{":
						System.out.println("Starting tag: " + codePoint);
						tagState = TagState.TAG;
						break;
					default:
						System.out.println("Unexpected value: " + codePoint);
						break;
				}
			} else {
				switch (codePoint) {
					case "{":
						++subBraceTags;

				}
			}
		}

		for (int i = 0; i < messagePattern.length(); i++) {
			char currentChar = messagePattern.charAt(i);

		}

		MessageFormat mf = new MessageFormat(messagePattern);
		
	}
}
