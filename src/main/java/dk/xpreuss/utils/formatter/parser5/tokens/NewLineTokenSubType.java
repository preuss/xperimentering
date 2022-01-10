package dk.xpreuss.utils.formatter.parser5.tokens;

import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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

	public static NewLineTokenSubType parseNewLine(CodePointSequence codePoints) {
		if (codePoints.length() > 0 && codePoints.length() <= biggestStyleLength()) {
			List<NewLineTokenSubType> types =
					Arrays.stream(NewLineTokenSubType.values()).sorted(Comparator.comparingInt(o -> o.getValue().length())).sorted(Comparator.reverseOrder())
							.toList();
			nextNewLineType:
			for (NewLineTokenSubType type : types) {
				int[] typeValueArray = type.value.codePoints().toArray();

				// If type is longer than source, ignore
				if(typeValueArray.length > codePoints.length()) {
					continue;
				}

				for(int i=0;i<typeValueArray.length;i++) {
					if(typeValueArray[i] != codePoints.codePointAt(i).getValue()) {
						continue nextNewLineType;
					}
				}
				return type;
			}
		} else {
			throw new IllegalArgumentException();
		}
		throw new NoSuchElementException();
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

