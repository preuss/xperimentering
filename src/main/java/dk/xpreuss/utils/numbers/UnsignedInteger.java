package dk.xpreuss.utils.numbers;

public class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
	public static final UnsignedInteger MAX_VALUE = new UnsignedInteger(-1);
	public static final UnsignedInteger MIN_VALUE = new UnsignedInteger(0);

	private final int value;

	public UnsignedInteger(int unsignedIntValue) {
		this.value = unsignedIntValue;
	}

	public static UnsignedInteger from(int unsignedIntValue) {
		return new UnsignedInteger(unsignedIntValue);
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return Integer.toUnsignedLong(value);
	}

	@Override
	public float floatValue() {
		return Integer.toUnsignedLong(value);
	}

	@Override
	public double doubleValue() {
		return Integer.toUnsignedLong(value);
	}

	@Override
	public int compareTo(UnsignedInteger other) {
		return Integer.compareUnsigned(this.value, other.value);
	}

	public UnsignedInteger add(UnsignedInteger second) {
		return add(this, second);
	}

	public UnsignedInteger add(int secondUnsignedIntValue) {
		return add(this, secondUnsignedIntValue);
	}

	public static UnsignedInteger add(UnsignedInteger first, UnsignedInteger second) {
		return add(first.value, second.value);
	}

	public static UnsignedInteger add(UnsignedInteger first, int secondUnsignedIntValue) {
		return add(first.value, secondUnsignedIntValue);
	}

	public static UnsignedInteger add(int firstUnsignedIntValue, int secondUnsignedIntValue) {
		return new UnsignedInteger(firstUnsignedIntValue + secondUnsignedIntValue);
	}

	public static UnsignedInteger add(int firstUnsignedIntValue, UnsignedInteger second) {
		return add(firstUnsignedIntValue, second.value);
	}

	public UnsignedInteger multiply(UnsignedInteger second) {
		return multiply(this, second);
	}

	public UnsignedInteger multiply(int secondValue) {
		return multiply(this.value, secondValue);
	}

	public static UnsignedInteger multiply(UnsignedInteger first, UnsignedInteger second) {
		return multiply(first.value, second.value);
	}

	public static UnsignedInteger multiply(UnsignedInteger first, int secondValue) {
		return multiply(first.value, secondValue);
	}

	public static UnsignedInteger multiply(int firstValue, int secondValue) {
		return new UnsignedInteger(firstValue * secondValue);
	}

	public static UnsignedInteger multiply(int firstValue, UnsignedInteger second) {
		return multiply(firstValue, second.value);
	}

	public UnsignedInteger div(UnsignedInteger divisor) {
		return div(this, divisor);
	}

	public UnsignedInteger div(int divisorUnsignedIntValue) {
		return div(this, divisorUnsignedIntValue);
	}

	public static UnsignedInteger div(UnsignedInteger dividend, UnsignedInteger divisor) {
		return div(dividend.value, divisor.value);
	}

	public static UnsignedInteger div(UnsignedInteger dividend, int divisorValue) {
		return div(dividend.value, divisorValue);
	}

	public static UnsignedInteger div(int dividendValue, int divisorValue) {
		return new UnsignedInteger(Integer.divideUnsigned(dividendValue, divisorValue));
	}

	public static UnsignedInteger div(int dividentValue, UnsignedInteger divisor) {
		return div(dividentValue, divisor);
	}

	public UnsignedInteger remainder(UnsignedInteger divisor) {
		return remainder(this, divisor);
	}

	public UnsignedInteger remainder(int divisorValue) {
		return remainder(this, divisorValue);
	}

	public static UnsignedInteger remainder(UnsignedInteger dividend, UnsignedInteger divisor) {
		return remainder(dividend.value, divisor.value);
	}

	public static UnsignedInteger remainder(UnsignedInteger dividend, int divisorValue) {
		return remainder(dividend.value, divisorValue);
	}

	public static UnsignedInteger remainder(int dividendValue, int divisorValue) {
		return new UnsignedInteger(Integer.remainderUnsigned(dividendValue, divisorValue));
	}

	public static UnsignedInteger remainder(int dividendValue, UnsignedInteger divisor) {
		return remainder(dividendValue, divisor.value);
	}

	public UnsignedInteger subtract(UnsignedInteger second) {
		return subtract(this, second);
	}

	public UnsignedInteger subtract(int secondValue) {
		return subtract(this, secondValue);
	}

	public static UnsignedInteger subtract(UnsignedInteger first, UnsignedInteger second) {
		return subtract(first.value, second.value);
	}

	public static UnsignedInteger subtract(UnsignedInteger first, int secondValue) {
		return subtract(first.value, secondValue);
	}

	public static UnsignedInteger subtract(int firstValue, int secondValue) {
		return new UnsignedInteger(firstValue - secondValue);
	}

	public static UnsignedInteger subtract(int firstValue, UnsignedInteger second) {
		return subtract(firstValue, second.value);
	}

	public static UnsignedInteger parseUnsignedInt(String str) {
		return new UnsignedInteger(Integer.parseUnsignedInt(str));
	}

	public static UnsignedInteger parseUnsignedInt(String str, int radix) {
		return new UnsignedInteger(Integer.parseUnsignedInt(str, radix));
	}

	public static UnsignedInteger parseUnsignedInt(CharSequence str, int beginIndex, int endIndex, int radix) {
		return new UnsignedInteger(Integer.parseUnsignedInt(str, beginIndex, endIndex, radix));
	}

	public long toLong() {
		return Integer.toUnsignedLong(value);
	}

	@Override
	public String toString() {
		return toString(value);
	}

	public static String toString(int uint) {
		return Integer.toUnsignedString(uint);
	}

	public static String toString(int uint, int radix) {
		return Integer.toUnsignedString(uint, radix);
	}
}
