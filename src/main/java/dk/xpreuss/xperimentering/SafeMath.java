package dk.xpreuss.xperimentering;

public class SafeMath {
	public static final int safeAdd(int left, int right) {
		if (right > 0 ? left > Integer.MAX_VALUE - right
				: left < Integer.MIN_VALUE - right) {
			throw new ArithmeticException("Integer overflow");
		}
		return left + right;
	}

	public static final long safeAdd(long left, long right) {
		if (right > 0 ? left > Long.MAX_VALUE - right
				: left < Long.MIN_VALUE - right) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return left + right;
	}

	public static final int safeSubtract(int left, int right) {
		if (right > 0 ? left < Integer.MIN_VALUE + right
				: left > Integer.MAX_VALUE + right) {
			throw new ArithmeticException("Integer overflow");
		}
		return left - right;
	}

	public static final long safeSubtract(long left, long right) {
		if (right > 0 ? left < Long.MIN_VALUE + right
				: left > Long.MAX_VALUE + right) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return left - right;
	}

	public static final int safeMultiply(int left, int right) {
		if (right > 0 ? left > Integer.MAX_VALUE / right
				|| left < Integer.MIN_VALUE / right
				: (right < -1 ? left > Integer.MIN_VALUE / right
				|| left < Integer.MAX_VALUE / right
				: right == -1
				&& left == Integer.MIN_VALUE)) {
			throw new ArithmeticException("Integer overflow");
		}
		return left * right;
	}

	public static final long safeMultiply(long left, long right) {
		if (right > 0 ? left > Long.MAX_VALUE / right
				|| left < Long.MIN_VALUE / right
				: (right < -1 ? left > Long.MIN_VALUE / right
				|| left < Long.MAX_VALUE / right
				: right == -1
				&& left == Long.MIN_VALUE)) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return left * right;
	}

	public static final int safeDivide(int left, int right) {
		if ((left == Integer.MIN_VALUE) && (right == -1)) {
			throw new ArithmeticException("Integer overflow");
		}
		return left / right;
	}

	public static final long safeDivide(long left, long right) {
		if ((left == Long.MIN_VALUE) && (right == -1)) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return left / right;
	}

	public static final int safeNegate(int a) {
		if (a == Integer.MIN_VALUE) {
			throw new ArithmeticException("Integer overflow");
		}
		return -a;
	}

	public static final long safeNegate(long a) {
		if (a == Long.MIN_VALUE) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return -a;
	}

	public static final int safeAbs(int a) {
		if (a == Integer.MIN_VALUE) {
			throw new ArithmeticException("Integer overflow");
		}
		return Math.abs(a);
	}

	public static final long safeAbs(long a) {
		if (a == Long.MIN_VALUE) {
			throw new ArithmeticException("Long Integer overflow");
		}
		return Math.abs(a);
	}
}
