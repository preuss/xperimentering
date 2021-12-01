package dk.xpreuss.utils.formatter.custom9;

public class Main {
	public static void main(String[] args) {
		String rawMessage = "Hello with you";
		StringMessageScanner scanner = new StringMessageScanner(rawMessage);
		StringMessageLexer lexer = new StringMessageLexer(scanner);
		for(int i=0;i<10;i++) {
			Token token = lexer.readNextToken();
			System.out.println(token);
		}

	}
}
