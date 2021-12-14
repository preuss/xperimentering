package dk.xpreuss.utils.formatter.parser4;

import dk.xpreuss.utils.formatter.parser4.lexer.StringMessageLexer;

public class Parser4Main {
	public static void main(String[] args) {
		String rawMessage = "Hello World";

		ILexer lexer = new StringMessageLexer(rawMessage);
		for(IToken t : lexer) {
			System.out.println("Token: " + t);
		}
		String a = "\u0018Hello\u0094Another\u2060yesæøå\uD834\uDD60";
		System.out.println("'"+a+"'");
		a = "\u0018Hello\u0094Another\u2060yesæøå\uD834\uDD64";
		System.out.println("'"+a+"'");
		System.out.println(a.length());
		System.out.println(a.codePoints().count());
		System.out.println("Char at 21:      " + a.charAt(21));
		System.out.println("Char at 22:      " + a.charAt(22));
		System.out.println("CodePoint at 21: " + Character.toString(a.codePointAt(21)));
	}
}
