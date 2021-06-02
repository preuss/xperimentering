package dk.xpreuss.utils.numbers;

public class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
	public static final UnsignedInteger MAX_VALUE = new UnsignedInteger(0x80000000);
	public static final UnsignedInteger MIN_VALUE = new UnsignedInteger(0x00000000);

	private final int value;

	public UnsignedInteger(int unsignedIntValue) {
		this.value = unsignedIntValue;
	}

	public static UnsignedInteger valueOf(int unsignedIntValue) {
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

	/**
	 * Compares two {@code UnsignedInteger} objects numerically.
	 *
	 * @param another the {@code UnsignedInteger} to be compared.
	 * @return the value {@code 0} if this {@code Integer} is
	 * equal to the argument {@code Integer}; a value less than
	 * {@code 0} if this {@code Integer} is numerically less
	 * than the argument {@code Integer}; and a value greater
	 * than {@code 0} if this {@code Integer} is numerically
	 * greater than the argument {@code Integer} (signed
	 * comparison).
	 */
	@Override
	public int compareTo(UnsignedInteger another) {
		return compare(this.value, another.value);
	}

	/**
	 * Compares a {@code UnsignedInteger} with a {@code int} objects numerically.
	 *
	 * @param anotherValue the {@code int} to be compared.
	 * @return the value {@code 0} if this {@code Integer} is
	 * equal to the argument {@code Integer}; a value less than
	 * {@code 0} if this {@code Integer} is numerically less
	 * than the argument {@code Integer}; and a value greater
	 * than {@code 0} if this {@code Integer} is numerically
	 * greater than the argument {@code Integer} (signed
	 * comparison).
	 */
	public int compareTo(int anotherValue) {
		return compare(this.value, anotherValue);
	}

	/**
	 * Compares two {@code int} values numerically treating the values
	 * as unsigned.
	 * <p>
	 * The value returned is identical to what would be returned by:
	 * <pre>
	 *    UnsignedInteger.valueOf(x).compareTo(Integer.valueOf(y))
	 * </pre>
	 *
	 * @param first  the first {@code UnsignedInteger} to compare
	 * @param second the second {@code UnsignedInteger} to compare
	 * @return the value {@code 0} if {@code x == y}; a value less
	 * than {@code 0} if {@code x < y} as unsigned values; and
	 * a value greater than {@code 0} if {@code x > y} as
	 * unsigned values
	 */
	public static int compare(UnsignedInteger first, UnsignedInteger second) {
		return compare(first.value, second.value);
	}

	public static int compare(int firstValue, int secondValue) {
		return Integer.compareUnsigned(firstValue, secondValue);
	}

	public static int compare(int firstValue, UnsignedInteger second) {
		return compare(firstValue, second.value);
	}

	public UnsignedInteger add(UnsignedInteger anotherUnsignedInteger) {
		return add(this, anotherUnsignedInteger);
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
