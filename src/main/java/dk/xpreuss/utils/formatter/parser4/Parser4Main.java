package dk.xpreuss.utils.formatter.parser4;

import dk.xpreuss.utils.formatter.parser4.lexer.StringMessageLexer;

public class Parser4Main {
	public static void main(String[] args) {
		String rawMessage = "";

		ILexer lexer = new StringMessageLexer(rawMessage);
		for(IToken t : lexer) {

		}
	}
}
