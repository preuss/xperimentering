package dk.xpreuss.utils.timers.clock;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TimeSpan implements Comparable<TimeSpan> {
	public static final TimeSpan Zero = new TimeSpan(0);
	private static final int HoursPerDay = 24;
	private static final int MinutesPerHour = 60;
	private static final int SecondsPerMinute = 60;
	private static final int MillisecondsPerSecond = 1_000;
	private static final int MicrosecondsPerMillisecond = 1_000;
	private static final int NanosecondsPerMicrosecond = 1_000;
	private static final long ElapsedTimeInNanosecondsPerNanosecond = 1;
	private static final long ElapsedTimeInNanosecondsPerMicrosecond = ElapsedTimeInNanosecondsPerNanosecond * 1_000;
	private static final long ElapsedTimeInNanosecondsPerMillisecond = ElapsedTimeInNanosecondsPerMicrosecond * 1_000;
	private static final long ElapsedTimeInNanosecondsPerSecond = ElapsedTimeInNanosecondsPerMillisecond * 1_000;
	private static final long ElapsedTimeInNanosecondsPerMinute = ElapsedTimeInNanosecondsPerSecond * 60;
	private static final long ElapsedTimeInNanosecondsPerHour = ElapsedTimeInNanosecondsPerMinute * 60;
	private static final long ElapsedTimeInNanosecondsPerDay = ElapsedTimeInNanosecondsPerHour * 24;
	private static final int MillisPerSecond = 1000;
	private static final int MillisPerMinute = MillisPerSecond * 60; //     60,000
	private static final int MillisPerHour = MillisPerMinute * 60;   //  3,600,000
	private static final int MillisPerDay = MillisPerHour * 24;      // 86,400,000
	private BigInteger elapsedTimeInNanoseconds;
	private long days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds;

	private TimeSpan(long elapsedInNanoseconds) {
		initialize(elapsedInNanoseconds);
	}

	private TimeSpan(BigInteger elapsedTimeInNanoseconds) {
		this(elapsedTimeInNanoseconds.longValueExact());
	}

	public static TimeSpan Interval(double value, int scale) {
		if (Double.isNaN(value)) {
			throw new IllegalArgumentException("Cannot be NaN");
		}
		double tmp = value * scale;
		double millis = tmp + (value >= 0 ? 0.5 : -0.5);
		if ((millis > Integer.MAX_VALUE / Zero.ElapsedTimeInNanosecondsPerMillisecond) || (millis < Integer.MAX_VALUE / ElapsedTimeInNanosecondsPerMillisecond)) {
			throw new ArithmeticException("double value out of byte range");
		}
		return new TimeSpan((long) millis * ElapsedTimeInNanosecondsPerMillisecond);
	}

	public static TimeSpan fromElapsedInNanoseconds(long elapsedInNanoseconds) {
		return new TimeSpan(elapsedInNanoseconds);
	}

	public static Builder Builder() {
		return new Builder();
	}

	private void initialize(long elapsedTimeInNanoseconds) {
		this.elapsedTimeInNanoseconds = BigInteger.valueOf(elapsedTimeInNanoseconds);
		this.days = calculateDays(elapsedTimeInNanoseconds);
		this.hours = calculateHours(elapsedTimeInNanoseconds);
		this.minutes = calculateMinutes(elapsedTimeInNanoseconds);
		this.seconds = calculateSeconds(elapsedTimeInNanoseconds);
		this.milliseconds = calculateMilliseconds(elapsedTimeInNanoseconds);
		this.microseconds = calculateMicroseconds(elapsedTimeInNanoseconds);
		this.nanoseconds = calculateNanoseconds(elapsedTimeInNanoseconds);
	}

	private long calculateDays(long elapsedTimeInNanoseconds) {
		return elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerDay;
	}

	private long calculateHours(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerHour) % HoursPerDay;
	}

	private long calculateMinutes(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerMinute) % MinutesPerHour;
	}

	private long calculateSeconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerSecond) % SecondsPerMinute;
	}

	private long calculateMilliseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerMillisecond) % MillisecondsPerSecond;
	}

	private long calculateMicroseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerMicrosecond) % MicrosecondsPerMillisecond;
	}

	private long calculateNanoseconds(long elapsedTimeInNanoseconds) {
		return (elapsedTimeInNanoseconds / ElapsedTimeInNanosecondsPerNanosecond) % NanosecondsPerMicrosecond;
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

	public double getTotalTimeDays() {
		return elapsedTimeInNanoseconds.doubleValue() / ElapsedTimeInNanosecondsPerDay;
	}

	public double getTotalTimeHours() {
		return elapsedTimeInNanoseconds.doubleValue() / ElapsedTimeInNanosecondsPerHour;
	}

	public double getTotalTimeMinutes() {
		return elapsedTimeInNanoseconds.doubleValue() / ElapsedTimeInNanosecondsPerMinute;
	}

	public double getTotalTimeSeconds() {
		return elapsedTimeInNanoseconds.doubleValue() / ElapsedTimeInNanosecondsPerSecond;
	}

	public long getTotalTimeMilliseconds() {
		return elapsedTimeInNanoseconds.longValueExact() / ElapsedTimeInNanosecondsPerMillisecond;
	}

	public long getTotalTimeMicroseconds() {
		return elapsedTimeInNanoseconds.longValueExact() / ElapsedTimeInNanosecondsPerMicrosecond;
	}

	public long getTotalTimeNanoseconds() {
		return elapsedTimeInNanoseconds.longValueExact() / ElapsedTimeInNanosecondsPerNanosecond;
	}

	public long getNanoAdjustment() {
		return Math.addExact(nanoseconds,
				Math.addExact(
						Math.multiplyExact(microseconds, NanosecondsPerMicrosecond),
						Math.multiplyExact(milliseconds, MicrosecondsPerMillisecond * NanosecondsPerMicrosecond)
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
		private BigInteger days, hours, minutes, seconds, milliseconds, microseconds, nanoseconds = BigInteger.ZERO;

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
			BigInteger dayElapsedTimeInNanoseconds = multiply(days, TimeSpan.Zero.ElapsedTimeInNanosecondsPerDay);
			BigInteger hourElapsedTimeInNanoseconds = multiply(hours, TimeSpan.Zero.ElapsedTimeInNanosecondsPerHour);
			BigInteger minuteElapsedTimeInNanoseconds = multiply(minutes, TimeSpan.Zero.ElapsedTimeInNanosecondsPerMinute);
			BigInteger secondElapsedTimeInNanoseconds = multiply(seconds, TimeSpan.Zero.ElapsedTimeInNanosecondsPerSecond);
			BigInteger millisecondElapsedTimeInNanoseconds = multiply(milliseconds, TimeSpan.Zero.ElapsedTimeInNanosecondsPerMillisecond);
			BigInteger microsecondElapsedTimeInNanoseconds = multiply(microseconds, TimeSpan.Zero.ElapsedTimeInNanosecondsPerMicrosecond);
			BigInteger nanosecondElapsedTimeInNanoseconds = multiply(nanoseconds, TimeSpan.Zero.ElapsedTimeInNanosecondsPerNanosecond);

			BigInteger totalElapsed = add(
					dayElapsedTimeInNanoseconds,
					hourElapsedTimeInNanoseconds,
					minuteElapsedTimeInNanoseconds,
					secondElapsedTimeInNanoseconds,
					millisecondElapsedTimeInNanoseconds,
					microsecondElapsedTimeInNanoseconds,
					nanosecondElapsedTimeInNanoseconds
			);
			System.out.println(totalElapsed);
			return new TimeSpan(totalElapsed);
		}
	}
}
