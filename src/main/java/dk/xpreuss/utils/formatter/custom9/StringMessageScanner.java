package dk.xpreuss.utils.formatter.custom9;

import java.util.Stack;

public class StringMessageScanner {
	/**
	 * The raw message format we are scanning
	 * TODO Need to handle CodePoint correctly use StringCodePointsIterable
	 * TODO Need to handle streaming, is faster and less memory
	 */
	private final String buffer;

	/**
	 * The current index position location within the buffer.
	 * Zero (0) based index.
	 */
	private int position;

	private final Stack<Integer> positionStack = new Stack<>();

	public StringMessageScanner(String rawMessage) {
		this.buffer = rawMessage;
	}

	public int getPosition() {
		return position;
	}

	public boolean endOfSource() {
		return position >= buffer.length();
	}

	public Character readNext() {
		return endOfSource() ? null : buffer.charAt(position++);
	}

	// TODO: Later change to use CodePoint or String for a single char. Because of UTF-8 not Char
	public Character peek() {
		saveCurrentPosition();
		var nextChar = readNext();
		restoreLastPosition();
		return nextChar;
	}

	private int saveCurrentPosition() {
		return positionStack.push(position);
	}

	private int restoreLastPosition() {
		return position = positionStack.pop();
	}
}
