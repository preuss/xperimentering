package dk.xpreuss.utils.formatter.parser3.tokenizer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;

import java.util.List;

public class TokenizedToken {
	private TokenType tokenType;
	private TokenSubType tokenSubType;
	private int usingCodePointsCount;
	private String value;

	private TokenizedToken() {

	}

	public static TokenizedToken begin() {
		return new TokenizedToken();
	}

	public TokenizedToken withToken(TokenType tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	public TokenizedToken withSubToken(TokenSubType subToken) {
		this.tokenSubType = subToken;
		return this;
	}

	public TokenizedToken usingCodePoints(int count) {
		this.usingCodePointsCount = count;
		return this;
	}

	public TokenizedToken withValue(List<CodePoint> codePoints) {
		value = codePointsToString(codePoints);
		return this;
	}

	private static String codePointsToString(List<CodePoint> codePoints) {
		return codePoints.stream().mapToInt(CodePoint::getValue)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public TokenSubType getTokenSubType() {
		return tokenSubType;
	}

	public int getUsingCodePointsCount() {
		return usingCodePointsCount;
	}

	public CharSequence getValue() {
		return value;
	}
}
