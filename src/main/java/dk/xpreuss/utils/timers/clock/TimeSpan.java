package dk.xpreuss.utils.timers.clock;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class TimeSpan implements Comparable<TimeSpan> {
	public static final TimeSpan ZERO = new TimeSpan(0);
	//private static final int HOURS_PER_DAY = 24;
	//private static final int MINUTES_PER_HOUR = 60;
	//private static final int SECONDS_PER_MINUTE = 60;
	//private static final int MILLISECONDS_PER_SECOND = 1_000;
	//private static final int MICROSECONDS_PER_MILLISECOND = 1_000;
	//private static final int NANOSECONDS_PER_MICROSECOND = 1_000;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_NANOSECOND = 1;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_MICROSECOND = ELAPSED_TIME_IN_NANOSECONDS_PER_NANOSECOND * TimeResolution.NANOSECONDS_PER_MICROSECOND;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND = ELAPSED_TIME_IN_NANOSECONDS_PER_MICROSECOND * TimeResolution.MICROSECONDS_PER_MILLISECOND;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_SECOND = ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND * TimeResolution.MILLISECONDS_PER_SECOND;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_MINUTE = ELAPSED_TIME_IN_NANOSECONDS_PER_SECOND * TimeResolution.SECONDS_PER_MINUTE;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_HOUR = ELAPSED_TIME_IN_NANOSECONDS_PER_MINUTE * TimeResolution.MINUTES_PER_HOUR;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_DAY = ELAPSED_TIME_IN_NANOSECONDS_PER_HOUR * TimeResolution.HOURS_PER_DAY;
	private static final long ELAPSED_TIME_IN_NANOSECONDS_PER_WEEK = ELAPSED_TIME_IN_NANOSECONDS_PER_DAY * TimeResolution.DAYS_PER_WEEK;

	private static final int MILLIS_PER_SECOND = TimeResolution.MILLISECONDS_PER_SECOND;                //        1_000
	private static final int MILLIS_PER_MINUTE = MILLIS_PER_SECOND * TimeResolution.SECONDS_PER_MINUTE; //       60_000
	private static final int MILLIS_PER_HOUR = MILLIS_PER_MINUTE * TimeResolution.MINUTES_PER_HOUR;     //    3_600_000
	private static final int MILLIS_PER_DAY = MILLIS_PER_HOUR * TimeResolution.HOURS_PER_DAY;           //   86_400_000
	private static final int MILLIS_PER_WEEK = MILLIS_PER_DAY * TimeResolution.DAYS_PER_WEEK;           //  604_800_000

	private BigInteger elapsedTimeInNanoseconds;
	private long weeks, days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds;

	private TimeSpan(long elapsedTimeInNanoseconds) {
		this(BigInteger.valueOf(elapsedTimeInNanoseconds));
	}

	private TimeSpan(BigInteger elapsedTimeInNanoseconds) {
		initialize(elapsedTimeInNanoseconds.longValueExact());
	}

	public static TimeSpan Interval(double value, int scale) {
		if (Double.isNaN(value)) {
			throw new IllegalArgumentException("Cannot be NaN");
		}
		double tmp = value * scale;
		double millis = tmp + (value >= 0 ? 0.5 : -0.5);
		if ((millis > Integer.MAX_VALUE / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND) || (millis < Integer.MAX_VALUE / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND)) {
			throw new ArithmeticException("double value out of byte range");
		}
		return new TimeSpan((long) millis * TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND);
	}

	public static TimeSpan fromElapsedInNanoseconds(long elapsedTimeInNanoseconds) {
		return new TimeSpan(elapsedTimeInNanoseconds);
	}

	public static Builder Builder() {
		return new Builder();
	}

	public static void main(String[] args) {
		TimeSpan t = Builder().milliseconds(53).weeks(44).build();
		System.out.println("t = " + t);
	}

	private static BigInteger div(BigInteger num1, long num2) {
		return num1.divide(BigInteger.valueOf(num2));
	}

	private static BigInteger mul(BigInteger... numbers) {
		return Arrays.stream(numbers).reduce(BigInteger::multiply).orElseThrow(()->new IllegalArgumentException("Needs more than zero arguments"));
	}

	private static BigInteger add(BigInteger... numbers) {
		return Arrays.stream(numbers).reduce(BigInteger::add).orElseThrow(()->new IllegalArgumentException("Needs more than zero arguments"));
	}

	private void initialize(long elapsedTimeInNanoseconds) {
		this.elapsedTimeInNanoseconds = BigInteger.valueOf(elapsedTimeInNanoseconds);
		this.weeks = calculateWeeks(elapsedTimeInNanoseconds);
		this.days = calculateDays(elapsedTimeInNanoseconds);
		this.hours = calculateHours(elapsedTimeInNanoseconds);
		this.minutes = calculateMinutes(elapsedTimeInNanoseconds);
		this.seconds = calculateSeconds(elapsedTimeInNanoseconds);
		this.milliseconds = calculateMilliseconds(elapsedTimeInNanoseconds);
		this.microseconds = calculateMicroseconds(elapsedTimeInNanoseconds);
		this.nanoseconds = calculateNanoseconds(elapsedTimeInNanoseconds);
	}

	private long calculateWeeks(long elapsedTimeInNanoseconds) {
		return elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_WEEK;
	}

	private long calculateDays(long elapsedTimeInNanoseconds) {
		return elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_DAY % TimeResolution.DAYS_PER_WEEK;
	}

	private long calculateHours(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_HOUR) % TimeResolution.HOURS_PER_DAY;
	}

	private long calculateMinutes(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MINUTE) % TimeResolution.MINUTES_PER_HOUR;
	}

	private long calculateSeconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_SECOND) % TimeResolution.SECONDS_PER_MINUTE;
	}

	private long calculateMilliseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND) % TimeResolution.MILLISECONDS_PER_SECOND;
	}

	private long calculateMicroseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MICROSECOND) % TimeResolution.MICROSECONDS_PER_MILLISECOND;
	}

	private long calculateNanoseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_NANOSECOND) % TimeResolution.NANOSECONDS_PER_MICROSECOND;
	}

	public long getWeeks() {
		return weeks;
	}

	public long getDays() {
		return days;
	}

	public long getHours() {
		return hours;
	}

	public long getMinutes() {
		return minutes;
	}

	public long getSeconds() {
		return seconds;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public long getMicroseconds() {
		return microseconds;
	}

	public long getNanoseconds() {
		return nanoseconds;
	}

	public double getTotalTimeWeeks() {
		return elapsedTimeInNanoseconds.doubleValue() / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_WEEK;
	}

	public double getTotalTimeDays() {
		return elapsedTimeInNanoseconds.doubleValue() / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_DAY;
	}

	public double getTotalTimeHours() {
		return elapsedTimeInNanoseconds.doubleValue() / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_HOUR;
	}

	public double getTotalTimeMinutes() {
		return elapsedTimeInNanoseconds.doubleValue() / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MINUTE;
	}

	public double getTotalTimeSeconds() {
		return elapsedTimeInNanoseconds.doubleValue() / TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_SECOND;
	}

	public long getTotalTimeMilliseconds() {
		return div(elapsedTimeInNanoseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND).longValueExact();
	}

	public long getTotalTimeMicroseconds() {
		return div(elapsedTimeInNanoseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MICROSECOND).longValueExact();
	}

	public long getTotalTimeNanoseconds() {
		return div(elapsedTimeInNanoseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_NANOSECOND).longValueExact();
	}

	public long getNanoAdjustment() {
		return Math.addExact(nanoseconds,
				Math.addExact(
						Math.multiplyExact(microseconds, TimeResolution.NANOSECONDS_PER_MICROSECOND),
						Math.multiplyExact(milliseconds, TimeResolution.MICROSECONDS_PER_MILLISECOND * TimeResolution.NANOSECONDS_PER_MICROSECOND)
				)
		);
	}

	public TimeSpan add(TimeSpan other) {
		BigInteger result = elapsedTimeInNanoseconds.add(other.elapsedTimeInNanoseconds);
		return new TimeSpan(result);
	}

	public TimeSpan subtract(TimeSpan other) {
		BigInteger result = elapsedTimeInNanoseconds.subtract(other.elapsedTimeInNanoseconds);
		return new TimeSpan(result);
	}

	public TimeSpan negate() {
		BigInteger result = elapsedTimeInNanoseconds.negate();
		return new TimeSpan(result);
	}

	public TimeSpan duration() {
		BigInteger result = elapsedTimeInNanoseconds.abs();
		return new TimeSpan(result);
	}

	@Override
	public String toString() {
		return TimeSpanFormat.format(this, TimeSpanFormat.FormatPattern.MINIMUM);
	}

	public String toStringFull() {
		return TimeSpanFormat.format(this, TimeSpanFormat.FormatPattern.FULL);
	}

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure
	 * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
	 * for all {@code x} and {@code y}.  (This
	 * implies that {@code x.compareTo(y)} must throw an exception iff
	 * {@code y.compareTo(x)} throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 *
	 * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
	 * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
	 * all {@code z}.
	 *
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 *
	 * <p>In the foregoing description, the notation
	 * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
	 * <i>signum</i> function, which is defined to return one of {@code -1},
	 * {@code 0}, or {@code 1} according to whether the value of
	 * <i>expression</i> is negative, zero, or positive, respectively.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(TimeSpan o) {
		return this.elapsedTimeInNanoseconds.compareTo(o.elapsedTimeInNanoseconds);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TimeSpan timeSpan = (TimeSpan) o;
		return days == timeSpan.days && hours == timeSpan.hours && minutes == timeSpan.minutes && seconds == timeSpan.seconds && milliseconds == timeSpan.milliseconds && microseconds == timeSpan.microseconds && nanoseconds == timeSpan.nanoseconds && elapsedTimeInNanoseconds.equals(timeSpan.elapsedTimeInNanoseconds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(elapsedTimeInNanoseconds, days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds);
	}

	public static class Builder {
		private BigInteger weeks, days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds = BigInteger.ZERO;

		private static BigInteger multiply(long... values) {
			return Arrays.stream(values).mapToObj(BigInteger::valueOf)
					.reduce(BigInteger.ONE, BigInteger::multiply);
		}

		private static BigInteger multiply(BigInteger mainValue, long... values) {
			return Arrays.stream(values).mapToObj(BigInteger::valueOf)
					.reduce(Optional.ofNullable(mainValue).orElse(BigInteger.ZERO), BigInteger::multiply);
		}

		private static BigInteger add(BigInteger... values) {
			return Arrays.stream(values).reduce(BigInteger.ZERO, BigInteger::add);
		}

		public Builder weeks(BigInteger weeks) {
			this.weeks = weeks;
			return this;
		}

		public Builder weeks(long weeks) {
			this.weeks = BigInteger.valueOf(weeks);
			return this;
		}

		public Builder days(BigInteger days) {
			this.days = days;
			return this;
		}

		public Builder days(long days) {
			this.days = BigInteger.valueOf(days);
			return this;
		}

		public Builder hours(BigInteger hours) {
			this.hours = hours;
			return this;
		}

		public Builder hours(long hours) {
			this.hours = BigInteger.valueOf(hours);
			return this;
		}

		public Builder minutes(BigInteger minutes) {
			this.minutes = minutes;
			return this;
		}

		public Builder minutes(long minutes) {
			this.minutes = BigInteger.valueOf(minutes);
			return this;
		}

		public Builder seconds(BigInteger seconds) {
			this.seconds = seconds;
			return this;
		}

		public Builder seconds(long seconds) {
			this.seconds = BigInteger.valueOf(seconds);
			return this;
		}

		public Builder milliseconds(BigInteger milliseconds) {
			this.milliseconds = milliseconds;
			return this;
		}

		public Builder milliseconds(long milliseconds) {
			this.milliseconds = BigInteger.valueOf(milliseconds);
			return this;
		}

		public Builder microseconds(BigInteger microseconds) {
			this.microseconds = microseconds;
			return this;
		}

		public Builder microseconds(long microseconds) {
			this.microseconds = BigInteger.valueOf(microseconds);
			return this;
		}

		public Builder nanoseconds(BigInteger nanoseconds) {
			this.nanoseconds = nanoseconds;
			return this;
		}

		public Builder nanoseconds(long nanoseconds) {
			this.nanoseconds = BigInteger.valueOf(nanoseconds);
			return this;
		}

		public TimeSpan build() {
			BigInteger weekElapsedTimeInNanoseconds = multiply(weeks, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_WEEK);
			BigInteger dayElapsedTimeInNanoseconds = multiply(days, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_DAY);
			BigInteger hourElapsedTimeInNanoseconds = multiply(hours, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_HOUR);
			BigInteger minuteElapsedTimeInNanoseconds = multiply(minutes, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MINUTE);
			BigInteger secondElapsedTimeInNanoseconds = multiply(seconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_SECOND);
			BigInteger millisecondElapsedTimeInNanoseconds = multiply(milliseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MILLISECOND);
			BigInteger microsecondElapsedTimeInNanoseconds = multiply(microseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_MICROSECOND);
			BigInteger nanosecondElapsedTimeInNanoseconds = multiply(nanoseconds, TimeSpan.ELAPSED_TIME_IN_NANOSECONDS_PER_NANOSECOND);

			BigInteger totalElapsed = add(
					weekElapsedTimeInNanoseconds,
					dayElapsedTimeInNanoseconds,
					hourElapsedTimeInNanoseconds,
					minuteElapsedTimeInNanoseconds,
					secondElapsedTimeInNanoseconds,
					millisecondElapsedTimeInNanoseconds,
					microsecondElapsedTimeInNanoseconds,
					nanosecondElapsedTimeInNanoseconds
			);
			return new TimeSpan(totalElapsed);
		}
	}
}
