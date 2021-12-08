package dk.xpreuss.utils.formatter.parser3.tokenizer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;

import java.util.List;

public class FreeTextTokenizer implements ITokenizer{
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
		if(peekCodePoints.size() < needMin() || peekCodePoints.size() > needMax()) {
			return false;
		}
		return true;
	}

	@Override
	public TokenizedToken toTokenType(List<CodePoint> codePoints) {
		if(codePoints.size() < needMin() || codePoints.size() > needMax()) {
			throw new IllegalArgumentException();
		}
		return TokenizedToken.begin().withToken(TokenType.FREE_TEXT).usingCodePoints(1);
	}
}
