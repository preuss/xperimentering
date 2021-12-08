package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser3.tokenizer.ITokenizer;
import dk.xpreuss.utils.formatter.parser3.tokenizer.NewLineTokenizer;
import dk.xpreuss.utils.formatter.parser3.tokenizer.TokenizedToken;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.WhiteSpaceTokenSubType;

import java.util.List;

public class Lexer implements ILexer{

	private final IScanner sourceScanner;

	public Lexer(IScanner sourceScanner) {
		this.sourceScanner = sourceScanner;
	}

	@Override
	public Token readNext() {
		if(sourceScanner.isEof()) {
			Token found = new Token(TokenType.END_OF_SOURCE, sourceScanner.getPosition());
		}
		try(ITokenizer tokenizer = new NewLineTokenizer()) {
			if(tokenizer.has(sourceScanner.peekCodePoint(tokenizer.needMax()))) {
				TokenizedToken tokenizedToken = tokenizer.toTokenType(sourceScanner.peekCodePoint(tokenizer.needMax()));
				int pos = sourceScanner.getPosition();
				// Consume.
				sourceScanner.readCodePoint(tokenizedToken.getUsingCodePointsCount());
				Token token = new Token(tokenizedToken.getTokenType(), tokenizedToken.getTokenSubType(), pos,
						tokenizedToken.getUsingCodePointsCount());
				return token;
			}
		}
		while(!sourceScanner.isEof()) {
			CodePoint current = sourceScanner.next();

		}
		if(sourceScanner.isEof()) {
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
		Lexer lexer = new Lexer(new StringScanner("\n\n\r\r\nHello World!"));
		Token next = null;
		while((next = lexer.readNext()).getType() != null) {
			System.out.println(next);
			if(next.getType() == TokenType.END_OF_SOURCE) break;
		}
	}
}
