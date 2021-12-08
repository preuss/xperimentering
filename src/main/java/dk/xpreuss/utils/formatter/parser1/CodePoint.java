package dk.xpreuss.utils.formatter.parser1;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CodePoint {
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
		if (this == other) return true;
		if (other == null) return false;
		return value == other.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}
