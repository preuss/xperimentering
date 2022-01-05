package dk.xpreuss.utils.formatter.parser5;

import dk.xpreuss.utils.formatter.parser5.lexer.ILexer;
import dk.xpreuss.utils.formatter.parser5.lexer.StringMessageLexer;
import dk.xpreuss.utils.formatter.parser5.scanner.IScanner;
import dk.xpreuss.utils.formatter.parser5.scanner.StringScanner;

public class Parser5Main {
	public static void main(String[] args) {
		System.out.println("Parser >>5<< Main");

		final String message = "Hello";

		IScanner scanner = new StringScanner(message);
		ILexer lexer = new StringMessageLexer(scanner);

		System.out.println(scanner.hasNext());
		System.out.println(scanner.next());
		System.out.println(scanner.peek());
		System.out.println(scanner.peekCount(3));
		System.out.println(scanner.nextCount(3));
	}
}
