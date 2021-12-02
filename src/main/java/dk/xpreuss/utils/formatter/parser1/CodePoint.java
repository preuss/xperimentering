package dk.xpreuss.utils.formatter.parser1;

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
}
