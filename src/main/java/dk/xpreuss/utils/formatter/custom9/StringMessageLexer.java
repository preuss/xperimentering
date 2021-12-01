package dk.xpreuss.utils.formatter.custom9;

import java.util.Optional;
import java.util.function.Function;

public class StringMessageLexer {

	private final StringMessageScanner scanner;

	public StringMessageLexer(StringMessageScanner scanner) {
		this.scanner = scanner;
	}

	public Token readNextToken() {
		if (scanner.endOfSource()) {
			return new Token(TokenType.END_OF_SOURCE, null, scanner.getPosition());
		}

		Optional<Token> tokenResult = Optional.empty();
		if (tokenResult.isEmpty()) {
			tokenResult = tryTokenizeText();
		}
		if (tokenResult.isEmpty()) {
			tokenResult = tryTokenizeWhitespace();
		}

		return tokenResult.orElseThrow(
				() -> new RuntimeException(String.format("Error parsing expression at %s value of '%s'",
						scanner.getPosition(),
						scanner.peek()
				))
		);
	}

	private Optional<Token> tryTokenizeWhitespace() {
		Token token = null;

		Character lookAhead = scanner.peek();
		if (lookAhead != null && Character.isWhitespace(lookAhead)) {
			int position = scanner.getPosition();
			StringBuilder builder = new StringBuilder();
			while (lookAhead != null && Character.isWhitespace(lookAhead)) {
				builder.append(scanner.readNext());
				lookAhead = scanner.peek();
			}
			token = new Token(TokenType.WHITE_SPACE, builder.toString(), position);
		}
		return Optional.ofNullable(token);
	}

	private Optional<Token> tryTokenizeText() {
		Token token = null;

		Function<Character, Boolean> isAlphaNumericText = character -> character != null && !Character.isWhitespace(character) && Character.isLetterOrDigit(character);

		Character lookAhead = scanner.peek();
		StringBuilder builder = new StringBuilder();
		int position = scanner.getPosition();
		while (isAlphaNumericText.apply(lookAhead)) {
			builder.append(scanner.readNext());
			lookAhead = scanner.peek();
		}
		if (!builder.isEmpty()) {
			token = new Token(TokenType.FREE_TEXT, builder.toString(), position);
		}
		return Optional.ofNullable(token);
	}
}
