package dk.xpreuss.utils.formatter.parser4;

import dk.xpreuss.utils.formatter.parser4.lexer.StringMessageLexer;

public class Parser4Main {
	public static void main(String[] args) {
		String rawMessage = "Hello World";

		ILexer lexer = new StringMessageLexer(rawMessage);
		for(IToken t : lexer) {
			System.out.println("Token: " + t);
		}
		String a = "\u0018Hello\u0094Another\u2060yesæøå";
		System.out.println("'"+a+"'");
		System.out.println(a.length());
	}
}
