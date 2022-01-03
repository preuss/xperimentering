package dk.xpreuss.utils.formatter.parser3.tokentypes;

import java.util.Arrays;

public enum NewLineTokenSubType implements TokenSubType {
	WIDOWS_STYLE("\r\n"),
	POSIX_STYLE("\n"),
	MAC_STYLE("\r");

	private final CharSequence value;

	NewLineTokenSubType(CharSequence retunValue) {
		this.value = retunValue;
	}

	public static boolean isNewLine(int codePoint) {
		return '\n' == codePoint || '\r' == codePoint;
	}

	public CharSequence getValue() {
		return value;
	}

	public static int biggestStyleLength() {
		return Arrays.stream(NewLineTokenSubType.values())
				.map(newLineType -> newLineType.value.length())
				.reduce(Integer::max)
				.orElseThrow();
	}
}

