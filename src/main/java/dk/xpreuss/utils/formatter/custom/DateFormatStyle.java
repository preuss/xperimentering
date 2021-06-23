package dk.xpreuss.utils.formatter.custom;

public enum DateFormatStyle {
	/**
	 * Constant for full style pattern.
	 */
	FULL(0),
	/**
	 * Constant for long style pattern.
	 */
	LONG(1),
	/**
	 * Constant for medium style pattern.
	 */
	MEDIUM(2),
	/**
	 * Constant for short style pattern.
	 */
	SHORT(3),
	/**
	 * Constant for default style pattern.  Its value is MEDIUM.
	 */
	DEFAULT(MEDIUM.value);

	private final int value;

	DateFormatStyle(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public int value() {
		return getValue();
	}
}
