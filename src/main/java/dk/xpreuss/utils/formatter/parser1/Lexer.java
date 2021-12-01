package dk.xpreuss.utils.formatter.parser1;

import dk.xpreuss.utils.formatter.CodepointStream;

import java.io.*;
import java.util.*;

public class Lexer {
	private static final int DEFAULT_BUFFER_SIZE = 2;
	private int bufferSize;
	final private Queue<Integer> bufferdQueue;

	private CodepointStream reader;

	public Lexer(Reader reader) {
		this(reader, DEFAULT_BUFFER_SIZE);
	}
	public Lexer(Reader reader, int bufferSize) {
		if (bufferSize < 0) throw new IllegalStateException();
		this.bufferSize = bufferSize;
		bufferdQueue = new LinkedQueue<Integer>();
		this.reader = new CodepointStream(reader);
	}

	private int peekNextChar() throws IOException {
		if(!bufferdQueue.isEmpty()) {
			bufferdQueue.enqueue(reader.read());
		}
		return bufferdQueue.peek();
	}

	private List<Integer> peekRange(int count) {
		return bufferdQueue.peekRange(count);
	}

	private int nextChar() {
		return bufferdQueue.dequeue();
	}

	public boolean isNextToken() {
		return false;
	}
	public Token nextToken() {
		throw new IllegalStateException();
	}

	public static void main(String[] args) {
		System.out.println("Start -->");
		String formattableString = "Hello World I am here.";
		Reader formattableStringReader = new StringReader(formattableString);
		Lexer lexer = new Lexer(formattableStringReader);
		while(lexer.isNextToken()) {
			Token token = lexer.nextToken();
			System.out.println(token);
		}

		System.out.println("--> End");
		//InputStreamReader
	}
}
