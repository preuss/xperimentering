package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringScanner implements IScanner {
	private final String value;
	private int pointer = -1; // Uninitialized.

	public StringScanner(String value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	@Override
	public boolean hasNext() {
		return value.length() > (pointer+1);
	}

	@Override
	public boolean isEof() {
		return value.length() == pointer;
	}

	@Override
	public CodePoint next() {
		if(hasNext()) {
			return CodePoint.wrap(value.charAt(++pointer));
		} else {
			return null;
		}
	}

	@Override
	public CodePoint peek() {
		if(hasNext()) {
			return CodePoint.wrap(value.charAt(pointer+1));
		} else {
			return null;
		}
	}

	@Override
	public List<CodePoint> readCodePoint(int nextCount) {
		if(nextCount < 0) throw new IllegalArgumentException();
		if(nextCount == 0) return new ArrayList<>(0);
		final int minEndIndex = Integer.min(pointer+nextCount, value.length());
		if(hasNext()) {
			List<CodePoint> retVal= value.substring(pointer+1, minEndIndex).chars().mapToObj(CodePoint::wrap).toList();
			pointer = minEndIndex -1;
			return retVal;
		} else {
			return new ArrayList<>(0);
		}
	}

	@Override
	public List<CodePoint> peekCodePoint(int peekCount) {
		if(peekCount < 0) throw new IllegalArgumentException();
		if(peekCount == 0) return new ArrayList<>(0);
		final int minEndIndex = Integer.min(pointer+peekCount, value.length());
		if(hasNext()) {
			return value.substring(pointer+1, minEndIndex).chars().mapToObj(CodePoint::wrap).toList();
		} else {
			return new ArrayList<>(0);
		}
	}

	@Override
	public int getPointer() {
		return pointer;
	}

	public static void main(String[] args) {
		// Test
		IScanner scanner = new StringScanner("Hello world");
		while(scanner.hasNext()) {
			System.out.println(scanner.next());
		}
	}
}
