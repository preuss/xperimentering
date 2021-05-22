package dk.xpreuss.utils.timers.clock;

import java.time.DateTimeException;
import java.time.Instant;

public interface IClock {
	//-------------------------------------------------------------------------
	/**
	 * Gets the current millisecond instant of the clock.
	 * <p>
	 * This returns the millisecond-based instant, measured from 1970-01-01T00:00Z (UTC).
	 * This is equivalent to the definition of {@link System#currentTimeMillis()}.
	 * <p>
	 * Most applications should avoid this method and use {@link Instant} to represent
	 * an instant on the time-line rather than a raw millisecond value.
	 * This method is provided to allow the use of the clock in high performance use cases
	 * where the creation of an object would be unacceptable.
	 * <p>
	 * The default implementation currently calls {@link #instant}.
	 *
	 * @return the current millisecond instant from this clock, measured from
	 * the Java epoch of 1970-01-01T00:00Z (UTC), not null
	 * @throws DateTimeException if the instant cannot be obtained, not thrown by most implementations
	 */
	default long millis() {
		return instant().toEpochMilli();
	}

	/**
	 * Gets the current instant of the clock.
	 * <p>
	 * This returns an instant representing the current instant as defined by the clock.
	 *
	 * @return the current instant from this clock, not null
	 * @throws DateTimeException if the instant cannot be obtained, not thrown by most implementations
	 */
	abstract Instant instant();
}
