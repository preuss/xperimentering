package dk.xpreuss.utils.formatter.parser5;

import dk.xpreuss.utils.formatter.parser5.lexer.ILexer;
import dk.xpreuss.utils.formatter.parser5.lexer.StringMessageLexer;
import dk.xpreuss.utils.formatter.parser5.scanner.IScanner;
import dk.xpreuss.utils.formatter.parser5.scanner.StringScanner;

import java.nio.CharBuffer;

public class Parser5Main {
	public static void main(String[] args) {
		System.out.println("Parser >>5<< Main");

		final String message = "\n\rHello\u001E\n\r\r World";

		IScanner scanner = new StringScanner(message);
		ILexer lexer = new StringMessageLexer(scanner);

		while(lexer.hasNext()) {
			System.out.println("next : " + lexer.next());
		}
	}
}
