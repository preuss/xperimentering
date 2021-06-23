package dk.xpreuss.utils.timers.clock;

import java.time.format.DecimalStyle;
import java.util.Objects;

public class TimeSpanPrintContext {
	/**
	 * The timeSpan being output
	 */
	private TimeSpan timeSpan;
	/**
	 * The formatter, not null
	 */
	private TimeSpanFormatter formatter;
	/**
	 * Wether the current formatter is optional.
	 */
	private int optional;

	public TimeSpanPrintContext(TimeSpan timeSpan, TimeSpanFormatter formatter) {
		this.timeSpan = Objects.requireNonNull(timeSpan, "timeSpan");
		this.formatter = Objects.requireNonNull(formatter, "timeSpanFormatter");
	}

	/**
	 * The timeSpan object being output.
	 * @return the timespan object, not null
	 */
	public TimeSpan getTimeSpan() {
		return timeSpan;
	}

	/**
	 * Returns the
	 * @return
	 */
	public TimeSpanFormatter getFormatter() {
		return formatter;
	}

	/**
	 * Gets the DecimalStyle.
	 * <p>
	 * The DecimalStyle controls the localization of numeric output.
	 *
	 * @return the DecimalStyle, not null
	 */
	DecimalStyle getDecimalStyle() {
		//return formatter.getDecimalStyle();
		// TODO: What am I doing ???
		return null;
	}
}
