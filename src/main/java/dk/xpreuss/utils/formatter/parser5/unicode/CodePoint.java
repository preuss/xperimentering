package dk.xpreuss.utils.formatter.parser5.unicode;

import java.util.Objects;

public class CodePoint implements Comparable<CodePoint> {
	private int value;

	private CodePoint(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static CodePoint wrap(int codePointValue) {
		return new CodePoint(codePointValue);
	}

	public static CodePoint wrap(char charValue) {
		return new CodePoint((int) charValue);
	}

	@Override
	public String toString() {
		return "CodePoint{Char:'" + Character.toString(value) + "', Int:#" + value + ", Name: '" + Character.getName(value) + "'}";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other instanceof CodePoint codePoint) {
			return value == codePoint.value;
		} else {
			return false;
		}
	}

	public boolean equals(CodePoint other) {
		if (this == Objects.requireNonNull(other)) return true;
		return value == other.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public int compareTo(CodePoint other) {
		if (this == Objects.requireNonNull(other)) {
			return 0;
		}
		return Integer.compare(this.value, other.value);
	}
}
