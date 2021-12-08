package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokenizer.*;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.WhiteSpaceTokenSubType;

import java.util.ArrayList;
import java.util.List;

public class Lexer implements ILexer {

	private final IScanner sourceScanner;

	public Lexer(IScanner sourceScanner) {
		this.sourceScanner = sourceScanner;
	}

	@Override
	public Token readNext() {
		if (sourceScanner.isEof()) {
			Token found = new Token(TokenType.END_OF_SOURCE, sourceScanner.getPosition());
		}
		try (ITokenizer tokenizer = new NewLineTokenizer()) {
			if (tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax()))) {
				TokenizedToken tokenizedToken = tokenizer.toTokenType(sourceScanner.peekCodePoint(tokenizer.needMax()));
				int position = sourceScanner.getPosition();
				// Consume.
				String value = codePointsToString(
						sourceScanner.readCodePoint(tokenizedToken.getUsingCodePointsCount())
				);
				Token token = new Token(
						tokenizedToken.getTokenType(),
						tokenizedToken.getTokenSubType(),
						value,
						position,
						tokenizedToken.getUsingCodePointsCount());
				return token;
			}
		}
		try (ITokenizer tokenizer = new SpaceTokenizer()) {
			StringBuilder spaces = new StringBuilder(1);
			TokenizedToken tokenizedToken = null;
			int startPos = sourceScanner.getPosition();
			while (tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax()))) {
				tokenizedToken = tokenizer.toTokenType(sourceScanner.peekCodePoint(tokenizer.needMax()));
				String value = codePointsToString(
						sourceScanner.readCodePoint(tokenizedToken.getUsingCodePointsCount())
				);
				spaces.append(value);
			}
			if (tokenizedToken != null) {
				return new Token(
						tokenizedToken.getTokenType(),
						tokenizedToken.getTokenSubType(),
						spaces.toString(),
						startPos,
						spaces.length()
				);
			}
		}
		try (ITokenizer tokenizer = new FreeTextTokenizer()) {
			StringBuilder freeText = new StringBuilder(1);
			TokenizedToken tokenizedToken = null;
			int startPos = sourceScanner.getPosition();
			while (tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax()))) {
				tokenizedToken = tokenizer.toTokenType(sourceScanner.peekCodePoint(tokenizer.needMax()));
				String value = codePointsToString(
						sourceScanner.readCodePoint(tokenizedToken.getUsingCodePointsCount())
				);
				freeText.append(value);
			}
			if (tokenizedToken != null) {
				return new Token(
						tokenizedToken.getTokenType(),
						tokenizedToken.getTokenSubType(),
						freeText.toString(),
						startPos,
						freeText.length()
				);
			}
		}
		while (!sourceScanner.isEof()) {
			CodePoint current = sourceScanner.next();

		}
		if (sourceScanner.isEof()) {
			return new Token(TokenType.END_OF_SOURCE, sourceScanner.getPosition());
		}
		throw new IllegalStateException();
	}

	private String codePointsToString(List<CodePoint> codePoints) {
		return codePoints.stream().mapToInt(CodePoint::getValue)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	@Override
	public Token peekNext() {
		return null;
	}

	@Override
	public int getPosition() {
		return sourceScanner.getPosition();
	}

	public static void main(String[] args) {
		Lexer lexer = new Lexer(new StringScanner("  \n\n\r\r\nHello World!"));
		Token next = null;
		while ((next = lexer.readNext()).getType() != null) {
			System.out.println(next);
			if (next.getType() == TokenType.END_OF_SOURCE) break;
		}
	}
}
