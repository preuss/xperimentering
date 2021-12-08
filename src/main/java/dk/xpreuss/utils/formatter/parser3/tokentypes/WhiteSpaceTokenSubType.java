package dk.xpreuss.utils.formatter.parser3.tokentypes;

import java.util.Arrays;

public enum WhiteSpaceTokenSubType implements TokenSubType {
	WIDOWS_STYLE("\r\n"),
	POSIX_STYLE("\n"),
	MAC_STYLE("\r");

	private final CharSequence value;

	WhiteSpaceTokenSubType(CharSequence retunValue) {
		this.value = retunValue;
	}

	public CharSequence getValue() {
		return value;
	}

	public static int biggestStyleLength() {
		return Arrays.stream(WhiteSpaceTokenSubType.values())
				.map(newLineType -> newLineType.value.length())
				.reduce(Integer::max)
				.orElseThrow();
	}
}

