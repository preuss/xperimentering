package dk.xpreuss.utils.formatter.parser1;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;

public class Lexer {
	private int bufferSize;
	final private Queue<Integer> bufferdQueue;

	private Reader reader;

	public Lexer(Reader reader, int bufferSize) {
		if (bufferSize < 0) throw new IllegalStateException();
		this.bufferSize = bufferSize;
		bufferdQueue = new LinkedQueue<Integer>();
		this.reader = reader;
	}

	public int peekNextChar() {
		return bufferdQueue.peek();
	}

	public List<Integer> peekRange(int count) {
		return bufferdQueue.peekRange(count);
	}

	public int next() {
		return bufferdQueue.dequeue();
	}

	public static void main(String[] args) {

		LinkedQueue<String> x = new LinkedQueue<>();
		x.enqueue("Hello World!, other words");
		x.enqueue("Second");
		x.enqueue("Last");
		System.out.println("Is empty: " +x.isEmpty());
		System.out.println("Is full: " + x.isFull());
		System.out.println("Size = " + x.size());
		System.out.println("x.peek() = " + x.peek());
		System.out.println("x.peek(3) = " + x.peekRange(3));
		System.out.println("x.peek(0) = " + x.peekRange(0));
		System.out.println(x);


	}
}
