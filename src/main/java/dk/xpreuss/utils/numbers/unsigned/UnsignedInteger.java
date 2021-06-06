package dk.xpreuss.utils.numbers.unsigned;

public class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
	public static final UnsignedInteger MAX_VALUE = new UnsignedInteger(0xFFFFFFFF);
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

	public static UnsignedInteger sum(UnsignedInteger a, UnsignedInteger b) {
		return add(a, b);
	}

	public static UnsignedInteger max(UnsignedInteger a, UnsignedInteger b) {
		return compare(a.value, b.value) >= 0 ? a : b;
	}

	public static UnsignedInteger min(UnsignedInteger a, UnsignedInteger b) {
		return compare(a.value, b.value) >= 0 ? a : b;
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

	public String toHexString() {
		return toHexString(this.value);
	}

	/**
	 * Returns a string representation of the integer argument as an
	 * unsigned integer in base&nbsp;16.
	 *
	 * <p>The unsigned integer value is the argument plus 2<sup>32</sup>
	 * if the argument is negative; otherwise, it is equal to the
	 * argument.  This value is converted to a string of ASCII digits
	 * in hexadecimal (base&nbsp;16) with no extra leading
	 * {@code 0}s.
	 *
	 * <p>The value of the argument can be recovered from the returned
	 * string {@code s} by calling {@link
	 * Integer#parseUnsignedInt(String, int)
	 * Integer.parseUnsignedInt(s, 16)}.
	 *
	 * <p>If the unsigned magnitude is zero, it is represented by a
	 * single zero character {@code '0'} ({@code '\u005Cu0030'});
	 * otherwise, the first character of the representation of the
	 * unsigned magnitude will not be the zero character. The
	 * following characters are used as hexadecimal digits:
	 *
	 * <blockquote>
	 * {@code 0123456789abcdef}
	 * </blockquote>
	 * <p>
	 * These are the characters {@code '\u005Cu0030'} through
	 * {@code '\u005Cu0039'} and {@code '\u005Cu0061'} through
	 * {@code '\u005Cu0066'}. If uppercase letters are
	 * desired, the {@link java.lang.String#toUpperCase()} method may
	 * be called on the result:
	 *
	 * <blockquote>
	 * {@code Integer.toHexString(n).toUpperCase()}
	 * </blockquote>
	 *
	 * @param unsignedIntegerValue an integer to be converted to a string.
	 * @return the string representation of the unsigned integer value
	 * represented by the argument in hexadecimal (base&nbsp;16).
	 * @see #parseUnsignedInt(String, int)
	 * @since 1.0.2
	 */
	public static String toHexString(int unsignedIntegerValue) {
		return Integer.toHexString(unsignedIntegerValue);
	}

	public static String toHexString(UnsignedInteger unsignedInteger) {
		return toHexString(unsignedInteger.value);
	}
}
