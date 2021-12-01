package dk.xpreuss.utils.formatter.parser1;

import dk.xpreuss.utils.formatter.CodepointStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

public class SourceScanner {
	final static int END_OFF_SOURCE = -1;

	private CodepointStream source;

	final private Queue<PositionValue> bufferdQueue = new LinkedQueue<>();

	private int sourcePosition;
	private int sourceValueCodePoint;
	private boolean endOfSource;

	//private int currentCodePoint;

	public SourceScanner(String source) {
		this.source = new CodepointStream(new BufferedReader(new StringReader(source)));
		this.sourcePosition = -1;
	}

	public int getSourcePosition() {
		return sourcePosition;
	}

	private Optional<Character> getCurrentCodePoint() {
		if (sourceValueCodePoint == -1) return Optional.empty();
		return Optional.of((char) sourceValueCodePoint);
	}


	public boolean isEndOfSource() {
		return endOfSource;
	}

	private void markEndOfSource() {
		this.endOfSource = true;
	}

	private Optional<Character> read() {
		if (isEndOfSource()) return Optional.empty();
		try {
			int codePoint = source.read();
			sourcePosition++;

			sourceValueCodePoint = codePoint;
			if (codePoint == -1) {
				markEndOfSource();
				return Optional.empty();
			} else {
				return Optional.of((char) codePoint);
			}
		} catch (IOException e) {
			this.markEndOfSource();
			return Optional.empty();
		}
	}

	private void readToQueue() {
		if (isEndOfSource()) return;
		Optional<Character> value = read();
		value.ifPresent(
				character -> bufferdQueue.enqueue(new PositionValue(this.sourcePosition, character))
		);
	}


	public Optional<PositionValue> readNext() {
		if (bufferdQueue.isEmpty()) {
			readToQueue();
		}
		if (bufferdQueue.isEmpty()) return Optional.empty();

		return Optional.of(bufferdQueue.dequeue());
	}

	public Optional<PositionValue> peekNext() {
		if (bufferdQueue.isEmpty()) {
			readToQueue();
		}
		if (bufferdQueue.isEmpty()) return Optional.empty();

		return Optional.of(bufferdQueue.peek());
	}

	public static void main(String[] args) {
		String message = "Hello World! æøå";
		System.out.println("Message: " + message);
		SourceScanner sc = new SourceScanner(message);
		Optional<PositionValue> posVal = sc.readNext();
		System.out.println(sc.getCurrentCodePoint());
		Optional<PositionValue> peek = sc.peekNext();
		System.out.println("Peek: " + peek);
		while (!sc.isEndOfSource()) {
			System.out.println(sc.getCurrentCodePoint());
			System.out.println(sc.getSourcePosition());
			System.out.println("YY ---> Peek : " + sc.peekNext());
			System.out.println("XX: " + posVal.orElse(new PositionValue(-1, null)));
			posVal = sc.readNext();
		}
	}
}

record PositionValue(int position, Character value) {
}