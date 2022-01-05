package dk.xpreuss.utils.formatter.parser5;

import dk.xpreuss.utils.formatter.parser5.lexer.ILexer;
import dk.xpreuss.utils.formatter.parser5.lexer.StringMessageLexer;
import dk.xpreuss.utils.formatter.parser5.scanner.IScanner;
import dk.xpreuss.utils.formatter.parser5.scanner.StringScanner;

import java.nio.CharBuffer;

public class Parser5Main {
	public static void main(String[] args) {
		System.out.println("Parser >>5<< Main");

		final String message = "Hello";

		IScanner scanner = new StringScanner(message);
		ILexer lexer = new StringMessageLexer(scanner);

		System.out.println("hasNext   : " + scanner.hasNext());
		System.out.println("Next      : " +scanner.next());
		System.out.println("Peek      : "+scanner.peek());
		System.out.println("PeekCount : "+scanner.peekCount(3));
		System.out.println("NextCount : " +scanner.nextCount(3));
	}
}
