package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.preuss.StringJoiner;

public class Token {
	private final TokenType type;
	private final TokenSubType subType;
	private final int position;
	private final int codePointCount;

	public Token(TokenType type, int position) {
		this.type = type;
		this.subType = null;
		this.position = position;
		this.codePointCount = 0;
	}

	public Token(TokenType tokenType, TokenSubType tokenSubType, int position, int codePointCount) {
		this.type = tokenType;
		this.subType = tokenSubType;
		this.position = position;
		this.codePointCount = codePointCount;
	}

	public TokenType getType() {
		return type;
	}

	public TokenSubType getSubType() {
		return subType;
	}

	public int getPosition() {
		return position;
	}

	public int getCodePointCount() {
		return codePointCount;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", Token.class.getSimpleName() + "(", ")");
		joiner
				.add("type=" + type)
				.addIfNotEmpty("subType=", subType)
				.add("position=" + position)
				.addTrueElseOther("codePointCount=", codePointCount, "EMPTY", count -> count > 0);
		return joiner.toString();
	}
}
