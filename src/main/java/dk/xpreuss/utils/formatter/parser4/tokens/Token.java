package dk.xpreuss.utils.formatter.parser4.tokens;

import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser4.AbstractToken;
import dk.xpreuss.utils.formatter.parser4.scanner.CodePointSequence;

public class Token extends AbstractToken {
	public Token(TokenType type, int position) {
		this(type, null, null, position, 0);
	}

	public Token(TokenType tokenType, TokenSubType tokenSubType, CodePointSequence value, int position, int codePointCount) {
		super(tokenType, tokenSubType, value, position, codePointCount);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private TokenType type;
		private TokenSubType subType;
		private CodePointSequence value;
		private int position;
		private int codePointCount;

		public Builder() {
		}

		public Builder type(TokenType val) {
			type = val;
			return this;
		}

		public Builder subType(TokenSubType val) {
			subType = val;
			return this;
		}

		public Builder value(CodePointSequence val) {
			value = val;
			return this;
		}

		public Builder position(int val) {
			position = val;
			return this;
		}

		public Builder codePointCount(int val) {
			codePointCount = val;
			return this;
		}

		public Token build() {
			return new Token(type, subType, value, position, codePointCount);
		}
	}
}
