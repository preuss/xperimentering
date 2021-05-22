package dk.xpreuss.utils.timers.clock.providers;

import dk.xpreuss.utils.timers.clock.TimeSpan;

import static dk.xpreuss.utils.timers.clock.providers.ITick.Resolution.*;

public interface ITick {
	/**
	 * The internal tick resolution
	 *
	 * @return the internal tick resolution
	 */
	Resolution getRawTickResolution();

	/**
	 * Returns the internal tick using the internal tick resolution;
	 *
	 * @return number of ticks in internal tick resolution
	 */
	long getRawTicks();

	/**
	 * Converts the raw internal resolution to this resolution.
	 *
	 * @param resolution the ticks to be converted to this resolution
	 * @return returns the converted ticks
	 */
	default long getTicksBy(Resolution resolution) {
		return getRawTicks() * (resolution.size / getRawTickResolution().size);
	}

	/**
	 * Converts the ticks to a custom resolution
	 *
	 * @param customResolution the new resolution size
	 * @return the ticks in the custom resolution
	 */
	default long getTicksBy(long customResolution) {
		return getTicksBy(NANOSECONDS) * (customResolution / NANOSECONDS.size);
	}

	/**
	 * @return ticks by default resolution
	 */
	default long getDefaultTicks() {
		return getTicksBy(getDefaultResolution());
	}

	/**
	 * @return the default resolution
	 */
	default Resolution getDefaultResolution() {
		return NANOSECONDS;
	}

	/**
	 * Returns ticks at resolution seconds
	 *
	 * @return ticks with resolution seconds
	 */
	default long getSeconds() {
		return getTicksBy(SECONDS);
	}

	/**
	 * Returns ticks at resolution milliseconds
	 *
	 * @return ticks with resolution milliseconds
	 */
	default long getMilliseconds() {
		return getTicksBy(MILLISECONDS);
	}

	/**
	 * Returns ticks at resolution microseconds
	 *
	 * @return ticks with resolution microseconds
	 */
	default long getMicroseconds() {
		return getTicksBy(MICROSECONDS);
	}

	/**
	 * Returns ticks at resolution nanoseconds
	 *
	 * @return ticks with resolution nanoseconds
	 */
	default long getNanoseconds() {
		return getTicksBy(NANOSECONDS);
	}

	default TimeSpan diff(ITick tickBefore) {
		return TimeSpan.fromElapsedInNanoseconds(getNanoseconds() - tickBefore.getNanoseconds());
	}

	/**
	 * TODO: In the future two things:
	 * TODO: 1. Change to use TimeUnit constants
	 * TODO: 2. Or change to include Minute, hour and day this means change the second as one to nano to one.
	 * TODO: Perhaps call it scale... ??? no maybe not.
	 */
	enum Resolution {
		SECONDS(1),
		MILLISECONDS(1_000),
		MICROSECONDS(1_000_000),
		NANOSECONDS(1_000_000_000),
		OTHER(-1);

		private final long size;

		Resolution(long size) {
			this.size = size;
		}

		public long getSize() {
			return size;
		}
	}
}

