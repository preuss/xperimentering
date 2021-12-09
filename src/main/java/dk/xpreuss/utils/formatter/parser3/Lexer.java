package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokenizer.*;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;

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
			int needRead = tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax()));
			if (needRead > 0) {
				List<CodePoint> codePoints = sourceScanner.readCodePoint(needRead);
				TokenizedToken tokenizedToken = tokenizer.toTokenType(codePoints);
				int position = sourceScanner.getPosition();
				// Consume.
				Token token = new Token(
						tokenizedToken.getTokenType(),
						tokenizedToken.getTokenSubType(),
						tokenizedToken.getValue(),
						position,
						tokenizedToken.getUsingCodePointsCount());
				return token;
			}
		}
		try (ITokenizer tokenizer = new SpaceTokenizer()) {
			StringBuilder spaces = new StringBuilder(1);
			TokenizedToken tokenizedToken = null;
			int startPos = sourceScanner.getPosition();
			int needRead = 0;
			while ((needRead = tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax())))>0) {
				List<CodePoint> codePoints = sourceScanner.readCodePoint(needRead);

				tokenizedToken = tokenizer.toTokenType(codePoints);
				spaces.append(tokenizedToken.getValue());
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
			int needRead = 0;
			while ((needRead = tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax())))>0) {
				List<CodePoint> codePoints = sourceScanner.readCodePoint(needRead);
				tokenizedToken = tokenizer.toTokenType(codePoints);
				freeText.append(tokenizedToken.getValue());
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

	@Override
	public Token peekNext() {
		return null;
	}

	@Override
	public int getPosition() {
		return sourceScanner.getPosition();
	}

	public static void main(String[] args) {
		Lexer lexer = new Lexer(new StringScanner("  \n\n\r\r\nHello World!!"));
		Token next = null;
		while ((next = lexer.readNext()).getType() != null) {
			System.out.println(next);
			if (next.getType() == TokenType.END_OF_SOURCE) break;
		}
	}
}
