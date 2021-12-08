package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.preuss.StringJoiner;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

public class Token {
	private final TokenType type;
	private final TokenSubType subType;
	private final CharSequence value;
	private final int position;
	private final int codePointCount;

	public Token(TokenType type, int position) {
		this(type, null, null, position, 0);
	}

	public Token(TokenType tokenType, TokenSubType tokenSubType, CharSequence value, int position, int codePointCount) {
		this.type = Objects.requireNonNull(tokenType);
		this.subType = tokenSubType;
		this.value = value;
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
				.addIfNotEmpty("value=", Objects.requireNonNullElse(value, "").toString().trim())
				.add("position=" + position)
				.addIfTrue("codePointCount=", codePointCount, value -> value > 0);
		return joiner.toString();
	}
}
