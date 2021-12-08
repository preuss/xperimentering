package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringScanner implements IScanner {
	private final String value;
	private int position = 0; // Uninitialized.

	public StringScanner(String value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return value.length() > position;
	}

	@Override
	public boolean isEof() {
		return !hasNext();
	}

	@Override
	public CodePoint next() {
		if (hasNext()) {
			return CodePoint.wrap(value.charAt(position++));
		} else {
			return null;
		}
	}

	@Override
	public CodePoint peek() {
		if (hasNext()) {
			return CodePoint.wrap(value.charAt(position));
		} else {
			return null;
		}
	}

	@Override
	public List<CodePoint> readCodePoint(int nextCount) {
		if (nextCount < 0) throw new IllegalArgumentException();
		if (nextCount == 0) return new ArrayList<>(0);
		final int minEndIndex = Integer.min(position + nextCount, value.length());
		if (hasNext()) {
			List<CodePoint> retVal = value.substring(position, minEndIndex).chars().mapToObj(CodePoint::wrap).toList();
			position += minEndIndex;
			return retVal;
		} else {
			return new ArrayList<>(0);
		}
	}

	@Override
	public List<CodePoint> peekCodePoint(int peekCount) {
		if (peekCount < 0) throw new IllegalArgumentException();
		if (peekCount == 0) return new ArrayList<>(0);
		final int minEndIndex = Integer.min(position + peekCount, value.length());
		if (hasNext()) {
			return value.substring(position, minEndIndex).chars().mapToObj(CodePoint::wrap).toList();
		} else {
			return new ArrayList<>(0);
		}
	}

	@Override
	public int getPosition() {
		return position;
	}

	public static void main(String[] args) {
		// Test
		IScanner scanner = new StringScanner("Hello world");
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}
	}
}
