package dk.xpreuss.utils.formatter.parser5.tokens;

import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;
import dk.xpreuss.utils.preuss.StringJoiner;

import java.util.Objects;

public abstract class AbstractToken implements IToken {
	private final TokenType type;
	private final TokenSubType subType;
	private final CodePointSequence value;
	private final int position;
	private final int codePointCount;

	protected AbstractToken(TokenType tokenType,
	                        TokenSubType tokenSubType,
	                        CodePointSequence value,
	                        int position,
	                        int codePointCount) {
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

	public CodePointSequence getValue() {
		return value;
	}

	public int getPosition() {
		return position;
	}

	public int codePointCount() {
		return codePointCount;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", Token.class.getSimpleName() + "(", ")");
		joiner
				.add("type=" + getType())
				.addIfNotEmpty("subType=", getSubType())
				.addIfNotEmpty("value=", Objects.requireNonNullElse(getValue(), "").toString().trim())
				.add("position=" + getPosition())
				.addIfTrue("codePointCount=", getCodePointCount(), value -> value > 0);
		return joiner.toString();
	}
}
