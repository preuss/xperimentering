package dk.xpreuss.utils.formatter.parser3.tokenizer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.WhiteSpaceTokenSubType;

import java.util.List;

public class NewLineTokenizer implements ITokenizer {
	// Windows Style \r\n
	private final static CodePoint WINDOWS_STYLE_FIRST = CodePoint.wrap('\r');
	private final static CodePoint WINDOWS_STYLE_SECOND = CodePoint.wrap('\n');
	// Posix Style \n
	private final static CodePoint POSIX_STYLE = CodePoint.wrap('\n');
	// Mac Style \r
	private final static CodePoint MAC_STYLE = CodePoint.wrap('\r');

	private static final int needMin = 1;
	private static final int needMax = 2;

	@Override
	public int needMin() {
		return needMin;
	}

	@Override
	public int needMax() {
		return needMax;
	}

	@Override
	public boolean has(List<CodePoint> peekCodePoints) {
		if (peekCodePoints.size() >= needMin()) {
			if (POSIX_STYLE.equals(peekCodePoints.get(0))) {
				return true;
			} else if (WINDOWS_STYLE_FIRST.equals(peekCodePoints.get(0))) {
				if (peekCodePoints.size() >= needMax()) {
					if (WINDOWS_STYLE_SECOND.equals(peekCodePoints.get(1))) {
						return true;
					}
				}
				if (MAC_STYLE.equals(peekCodePoints.get(0))) {
					return true;
				}
			}
		}
		return false;
	}

	public TokenizedToken toTokenType(List<CodePoint> codePoints) {
		TokenizedToken tokenizedToken = TokenizedToken.begin();
		if (codePoints.size() >= needMin()) {
			if (POSIX_STYLE.equals(codePoints.get(0))) {
				return tokenizedToken.withToken(TokenType.NEW_LINE).withSubToken(WhiteSpaceTokenSubType.POSIX_STYLE).usingCodePoints(1);
			} else if (WINDOWS_STYLE_FIRST.equals(codePoints.get(0))) {
				if (codePoints.size() >= needMax()) {
					if (WINDOWS_STYLE_SECOND.equals(codePoints.get(1))) {
						return tokenizedToken.withToken(TokenType.NEW_LINE).withSubToken(WhiteSpaceTokenSubType.WIDOWS_STYLE).usingCodePoints(2);
					}
				}
				if (MAC_STYLE.equals(codePoints.get(0))) {
					return tokenizedToken.withToken(TokenType.NEW_LINE).withSubToken(WhiteSpaceTokenSubType.MAC_STYLE).usingCodePoints(1);
				}
			}
		}
		return null;
	}
}
