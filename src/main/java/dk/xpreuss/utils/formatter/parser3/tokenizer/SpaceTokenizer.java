package dk.xpreuss.utils.formatter.parser3.tokenizer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;

import java.util.List;

public class SpaceTokenizer implements ITokenizer {
	@Override
	public int needMin() {
		return 1;
	}

	@Override
	public int needMax() {
		return 1;
	}

	@Override
	public boolean has(List<CodePoint> peekCodePoints) {
		if(peekCodePoints.size() > needMax() || peekCodePoints.size() < needMin()) return false;
		return peekCodePoints.stream().allMatch(codePoint -> codePoint.getValue() == ' ');
	}

	@Override
	public TokenizedToken toTokenType(List<CodePoint> codePoints) {
		if(codePoints.size() > needMax() || codePoints.size() < needMin()) throw new IllegalArgumentException();
		if(codePoints.stream().allMatch(codePoint -> codePoint.getValue() == ' ')) {
			return TokenizedToken.begin().withToken(TokenType.WHITE_SPACE).usingCodePoints(1);
		} else {
			return TokenizedToken.begin().withToken(TokenType.ERROR);
		}
	}
}
