package dk.xpreuss.utils.formatter.parser5.unicode;

import java.util.Objects;

public class UString implements CodePointSequence {
	private final String str;

	public UString() {
		this.str = "";
	}

	public UString(String stringValue) {
		this.str = Objects.requireNonNull(stringValue);
	}

	public UString(CharSequence charSequenceValue) {
		this.str = Objects.requireNonNull(charSequenceValue).toString();
	}

	@Override
	public int length() {
		return str.length();
	}

	@Override
	public CodePoint codePointAt(int index) {
		return CodePoint.wrap(str.codePointAt(index));
	}

	@Override
	public CodePointSequence subSequence(int start, int end) {
		return new UString(str.substring(start, end));
	}

	@Override
	public String toString() {
		return CodePointSequence.toString(this);
	}
}
