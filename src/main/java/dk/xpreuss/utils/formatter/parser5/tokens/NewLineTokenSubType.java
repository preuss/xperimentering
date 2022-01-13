package dk.xpreuss.utils.formatter.parser5.tokens;

import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;
import dk.xpreuss.utils.formatter.parser5.unicode.UString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Info using https://en.wikipedia.org/wiki/Newline
 */
public enum NewLineTokenSubType implements TokenSubType {
	/* CR LF = 0x0D 0x0A */
	WIDOWS_STYLE("\r\n"),
	/* LF = 0x0A */
	POSIX_STYLE("\n"),
	/* CR = 0x0D */
	MAC_STYLE("\r"),
	/* RS = 0x1E */
	QNX_PRE_POSIX("\u001E"),
	/* LF CR = 0x0A 0x0D */
	ACORN_BBC("\n\r"),
	/* '>' = Single right-pointing angle quotation mark = 0x9B. But Using ATASCII, here 0x9b = End of line or RETURN */
	/* Not used because it's a bigger than character.
	//ATARI_8_BIT("\u009B"),

	 */
	/* NAK = Negative Acknowledgement = 0x15. But using EBCD, here NL = New Line = 0x15 */
	IBM_MAINFRAME_SYSTEMS("\u0015"),
	/* 'L' = Uppercase L = 0x76. But using a specific non-ASCII character set where 0x76 is NEWLINE */
	/* Not used, because its an uppercase L.
	//ZX80_ZX81("\u0076")
	 */
	;

	private final CharSequence value;

	NewLineTokenSubType(CharSequence retunValue) {
		this.value = retunValue;
	}

	public static boolean isNewLine(CodePointSequence codePoints) {
		return Arrays.stream(NewLineTokenSubType.values())
				.map(newLineType -> newLineType.value).anyMatch(charSequence -> {
					if(charSequence.length() <= codePoints.length()) {
						return charSequence.toString().equals(codePoints.subSequence(0, charSequence.length()-1).toString());
					}
					return false;
				});
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

