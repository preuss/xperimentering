package dk.xpreuss.utils.timers.clock;

public enum TimeResolution {
	WEEKS(TimeResolution.WEEK_SCALE),
	DAYS(TimeResolution.DAY_SCALE),
	HOURS(TimeResolution.HOUR_SCALE),
	MINUTES(TimeResolution.MINUTE_SCALE),
	SECONDS(TimeResolution.SECOND_SCALE),
	MILLISECONDS(TimeResolution.MILLISECOND_SCALE),
	MICROSECONDS(TimeResolution.MICROSECOND_SCALE),
	NANOSECONDS(TimeResolution.NANOSECOND_SCALE);

	// Other scales as constants
	static final int DAYS_PER_WEEK = 7;
	static final int HOURS_PER_DAY = 24;
	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int MILLISECONDS_PER_SECOND = 1_000;
	static final int MICROSECONDS_PER_MILLISECOND = 1_000;
	static final int NANOSECONDS_PER_MICROSECOND = 1_000;

	// Scales as constants, ticked a a nanosecond number.
	static final long NANOSECOND_SCALE = 1L;
	static final long MICROSECOND_SCALE = NANOSECONDS_PER_MICROSECOND * NANOSECOND_SCALE;
	static final long MILLISECOND_SCALE = MICROSECONDS_PER_MILLISECOND * MICROSECOND_SCALE;
	static final long SECOND_SCALE = MILLISECONDS_PER_SECOND * MILLISECOND_SCALE;
	static final long MINUTE_SCALE = SECONDS_PER_MINUTE * SECOND_SCALE;
	static final long HOUR_SCALE = MINUTES_PER_HOUR * MINUTE_SCALE;
	static final long DAY_SCALE = HOURS_PER_DAY * HOUR_SCALE;
	static final long WEEK_SCALE = DAYS_PER_WEEK * DAY_SCALE;

	private final long scale;

	TimeResolution(long scale) {
		this.scale = scale;
	}

	/**
	 * General conversion utility.
	 *
	 * @param sourceDuration   source duration in source resolution
	 * @param sourceResolution the source resolution
	 * @param targetResolution the target
	 * @return the duration to target resolution
	 */
	private static long generalTimeConversion(long sourceDuration, TimeResolution sourceResolution, TimeResolution targetResolution) {
		if (targetResolution == sourceResolution) {
			return sourceDuration;
		}

		if (sourceResolution.scale < targetResolution.scale) {
			long scaleDiff = Math.floorDiv(targetResolution.scale, sourceResolution.scale);
			return Math.floorDiv(sourceDuration, scaleDiff);
		} else {
			long scaleDiff = Math.floorDiv(sourceResolution.scale, targetResolution.scale);
			return Math.multiplyExact(sourceDuration, scaleDiff);
		}
	}

	/**
	 * This defines the nano seconds included in this time resolution.
	 *
	 * @return the scale in amount of nanos for this time resolution
	 */
	public long getScale() {
		return scale;
	}

	/**
	 * Converts a duration to another.
	 *
	 * @param sourceDuration   converts from this duration
	 * @param targetResolution converts from this duration type
	 * @return the converted duration
	 */
	public long convert(long sourceDuration, TimeResolution targetResolution) {
		return generalTimeConversion(sourceDuration, this, targetResolution);
	}

	public long convert(TimeSpan timeSpan) {
		return generalTimeConversion(timeSpan.getTotalTimeNanoseconds(), NANOSECONDS, this);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) NANOSECONDS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toNanos(long duration) {
		return generalTimeConversion(duration, this, NANOSECONDS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) MICROSECONDS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toMicros(long duration) {
		return generalTimeConversion(duration, this, MICROSECONDS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) MILLISECONDS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toMillis(long duration) {
		return generalTimeConversion(duration, this, MILLISECONDS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) SECONDS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toSeconds(long duration) {
		return generalTimeConversion(duration, this, SECONDS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) MINUTES.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toMinutes(long duration) {
		return generalTimeConversion(duration, this, MINUTES);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) HOURS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration,
	 * or {@code Long.MIN_VALUE} if conversion would negatively overflow,
	 * or {@code Long.MAX_VALUE} if it would positively overflow.
	 */
	public long toHours(long duration) {
		return generalTimeConversion(duration, this, HOURS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) DAYS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration
	 */
	public long toDays(long duration) {
		return generalTimeConversion(duration, this, DAYS);
	}

	/**
	 * Equivalent to
	 * {@link #convert(long, TimeResolution) WEEKS.convert(duration, this)}.
	 *
	 * @param duration the duration
	 * @return the converted duration
	 */
	public long toWeeks(long duration) {
		return generalTimeConversion(duration, this, WEEKS);
	}
}
