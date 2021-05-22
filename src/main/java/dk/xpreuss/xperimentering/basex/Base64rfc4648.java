package dk.xpreuss.xperimentering.basex;

import dk.xpreuss.xperimentering.SafeMath;
import dk.xpreuss.xperimentering.basex.coders.*;

/**
 * See https://stackoverflow.com/questions/13378815/base64-length-calculation
 * <p>
 * Different types of base64 length calculations:
 * Padding: Math.floor(n+2) / 3 * 4
 * UnPadding: Math.floor(n*4+2) / 3
 */
public class Base64rfc4648 {
	/**
	 * This is the ceil division of ceil(x / y)
	 * See https://stackoverflow.com/a/45401395/4711156
	 *
	 * @param x value
	 * @param y value
	 * @return the divided x by y ceil
	 */
	private static int ceilDiv(int x, int y) {
		// Remember it's like Maths.floor(x+y-1) / y
		return (x + y - 1) / y;
	}

	private static long ceilDiv(long x, long y) {
		// Remember it's like Maths.floor(x+y-1) / y
		return (x + y - 1) / y;
	}

	private static int ceilDivSafe(int x, int y) {
		// Remember it's like Maths.floor(x+y-1) / y
		return SafeMath.safeDivide(Math.subtractExact(Math.addExact(x, y), 1), y);
	}

	private static long ceilDivSafe(long x, long y) {
		// Remember it's like Maths.floor(x+y-1) / y
		return SafeMath.safeDivide(Math.subtractExact(Math.addExact(x, y), 1), y);
	}

	private static int paddedBase64(int n) {
		int blocks = ceilDiv(n, 3);
		return blocks * 4;
	}

	private static long paddedBase64(long n) {
		long blocks = ceilDiv(n, 3);
		return blocks * 4;
	}

	private static int paddedBase64Safe(int n) {
		int blocks = ceilDivSafe(n, 3);
		return Math.multiplyExact(blocks, 4);
	}

	private static long paddedBase64Safe(long n) {
		long blocks = ceilDivSafe(n, 3);
		return Math.multiplyExact(blocks, 4);
	}

	public static int unpaddedBase64(int n) {
		int bits = 8 * n;
		return ceilDiv(bits, 6);
	}

	public static long unpaddedBase64(long n) {
		long bits = 8 * n;
		return ceilDiv(bits, 6);
	}

	private static int unpaddedBase64Safe(int n) {
		int bits = Math.multiplyExact(8, n);
		return ceilDivSafe(bits, 6);
	}

	private static long unpaddedBase64Safe(long n) {
		long bits = Math.multiplyExact(8, n);
		return ceilDivSafe(bits, 6);
	}

	/**
	 * Calculates the length of the encoded output bytes.
	 *
	 * @param srcLength        length of the bytes to encode
	 * @param padded           if true, tells if it should be padded
	 * @param safeAndThrowOOME if true, tells if it should test for overflow,
	 *                         and throws OutOfMemoryError if the length of
	 *                         the encoded bytes overflows; else returns the
	 *                         length
	 * @return length of the encoded bytes, or -1 if the length overflows
	 */
	public static int calculateOutLenght(int srcLength, boolean padded, boolean safeAndThrowOOME) {
		// Always test for overflow
		final boolean safe = safeAndThrowOOME;
		try {
			if (padded) {
				if (safe) {
					return paddedBase64Safe(srcLength);
				} else {
					return paddedBase64(srcLength);
				}
			} else {
				if (safe) {
					return unpaddedBase64Safe(srcLength);
				} else {
					return unpaddedBase64(srcLength);
				}
			}
		} catch (ArithmeticException e) {
			if (safeAndThrowOOME) {
				throw new OutOfMemoryError("Encoded size is too large");
			} else {
				// let the caller know that encoded bytes length is too large
				return -1;
			}
		}
	}

	/**
	 * Calculates the length of the encoded output bytes.
	 *
	 * @param srcLength        length of the bytes to encode
	 * @param padded           if true, tells if it should be padded
	 * @param safeAndThrowOOME if true, tells if it should test for overflow,
	 *                         and throws OutOfMemoryError if the length of
	 *                         the encoded bytes overflows; else returns the
	 *                         length
	 * @return length of the encoded bytes, or -1 if the length overflows
	 */
	public static long calculateOutLenght(long srcLength, boolean padded, boolean safeAndThrowOOME) {
		// Always test for overflow
		final boolean safe = safeAndThrowOOME;
		try {
			if (padded) {
				if (safe) {
					return paddedBase64Safe(srcLength);
				} else {
					return paddedBase64(srcLength);
				}
			} else {
				if (safe) {
					return unpaddedBase64Safe(srcLength);
				} else {
					return unpaddedBase64(srcLength);
				}
			}
		} catch (ArithmeticException e) {
			if (safeAndThrowOOME) {
				throw new OutOfMemoryError("Encoded size is too large");
			} else {
				// let the caller know that encoded bytes length is too large
				return -1;
			}
		}
	}
}
