package dk.xpreuss.utils.numbers.unsigned;

import java.math.BigInteger;

/**
 * See https://github.com/ccarpenter04/Unsigned-Integers for ideas
 * @param <T>
 */
public abstract class UnsignedNumber<T extends UnsignedNumber> extends Number implements Comparable<T> {
	/**
	 * Returns this unsigned number as a {@code BigInteger}.
	 *
	 * @return the unsigned numeric value represented by this object after conversion to type {@code BigInteger}.
	 */
	public abstract BigInteger bigIntegerValue();

	/**
	 * Uses the {@code BigInteger} representation's hashCode() of this unsigned number.
	 *
	 * @return a hash code for this unsigned number.
	 */
	@Override
	public int hashCode() {
		return bigIntegerValue().hashCode();
	}

	/**
	 * Abstract method forcing subclasses to provide unique implementation overriding {@code Object} implementations.
	 *
	 * @param   obj   the reference object with which to compare.
	 * @return  {@code true} if this object is the same as the obj
	 *          argument; {@code false} otherwise.
	 * @see     #hashCode()
	 * @see     java.util.HashMap
	 */
	@Override
	public abstract boolean equals(Object obj);

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @param   obj   the reference object with which to compare.
	 * @return  {@code true} if this object is the same as the obj
	 *          argument; {@code false} otherwise.
	 */
	public abstract boolean equals(T obj);

	/**
	 * Abstract method forcing subclasses to provide unique implementation overriding {@code Object} implementations.
	 *
	 * @return a string representation of the number.
	 */
	@Override
	public abstract String toString();
}
