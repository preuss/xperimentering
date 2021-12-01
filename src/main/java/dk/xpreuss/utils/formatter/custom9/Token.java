package dk.xpreuss.utils.formatter.custom9;

public class Token {
	private TokenType type;
	private String value;

	// For error handling
	private int positionOffset; // The index of the raw string format
	//private int lineBreak; // The number of line breaks this tokens contains from the raw string format
	//private int startLineOffset; // The offset of the line
	//private int endLineOffset; // The end offset of the line


	public Token(TokenType type, String value, int positionOffset) {
		this.type = type;
		this.value = value;
		this.positionOffset = positionOffset;
	}

	public TokenType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public int getPositionOffset() {
		return positionOffset;
	}

	@Override
	public String toString() {
		return "Token{" +
				"type=" + type +
				", value='" + value + '\'' +
				", positionOffset=" + positionOffset +
				'}';
	}
}
