package dk.xpreuss.utils.formatter.parser5.scanner;


import dk.xpreuss.utils.formatter.parser5.unicode.CodePoint;
import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;
import dk.xpreuss.utils.formatter.parser5.unicode.UString;

import java.nio.CharBuffer;
import java.util.Objects;
import java.util.Optional;

public class StringScanner implements IScanner {
	private final CodePointSequence value;
	private int position = 0; // Unintialized

	public StringScanner(String value) {
		this.value = new UString(Objects.requireNonNull(value));
	}

	public StringScanner(UString value) {
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
			return value.codePointAt(position++);
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public Optional<CodePoint> peek() {
		if (hasNext()) {
			return Optional.of(value.codePointAt(position));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public CodePointSequence nextCount(int nextCount) {
		if(nextCount < 0) throw new IllegalArgumentException();
		if(nextCount ==0) return new UString();
		CharBuffer charBuffer = CharBuffer.allocate(nextCount);
		while (hasNext()) {
			charBuffer.append(next().toString());
		}

	}

	@Override
	public CodePointSequence peekCount(int peekCount) {
		if (peekCount < 0) throw new IllegalArgumentException();
		if (peekCount == 0) return new UString();
		final int minEndIndex = Integer.min(position + peekCount, value.length());
		if (hasNext()) {
			return value.subSequence(position, minEndIndex);
		} else {
			return new UString();
		}
	}

	@Override
	public int getPosition() {
		return position;
	}
}
