package dk.xpreuss.utils.formatter.parser4.lexer;

import dk.xpreuss.utils.formatter.parser4.IToken;
import dk.xpreuss.utils.formatter.parser4.scanner.StringScanner;
import dk.xpreuss.utils.formatter.parser4.ILexer;
import dk.xpreuss.utils.formatter.parser4.IScanner;

import java.util.NoSuchElementException;

public class StringMessageLexer implements ILexer {
	private IScanner scanner;
	private IToken currentToken;

	public StringMessageLexer(String replaceableMessage) {
		this.scanner = new StringScanner(replaceableMessage);
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public IToken next() {
		throw new NoSuchElementException();
	}
}
