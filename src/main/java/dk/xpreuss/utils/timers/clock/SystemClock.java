package dk.xpreuss.utils.timers.clock;

import dk.xpreuss.utils.timers.clock.providers.ITickProvider;
import dk.xpreuss.utils.timers.clock.providers.MilliTickProvider;
import net.time4j.scale.LeapSeconds;

import java.time.Clock;
import java.time.Instant;

public class SystemClock implements IClock {
	private static final long MILLI = 1_000;
	private static final long MICRO = 1_000_000;
	private static final long NANO = 1_000_000_000;

	private final ITickProvider tickProvider;
	private final long offset;

	private SystemClock() {
		this(new MilliTickProvider(), calculateOffsetCalibration(new MilliTickProvider()));
	}

	private SystemClock(ITickProvider tickProvider, long offset) {
		this.tickProvider = tickProvider;
		this.offset = offset;
	}

	private static long calculateOffsetCalibration(ITickProvider tickProvider) {
		// see https://bugs.openjdk.java.net/browse/JDK-8068730 (affects Java 9 or later)
		Instant instant = Clock.systemUTC().instant();

		long compareNanos = tickProvider.nextTickOfNanoseconds();

		long currentMillis = System.currentTimeMillis();

		long compareMillis = compareNanos / MILLI;


		// handle Instant like POSIX, see real conversion between Instant and j.u.Date
		long utc = LeapSeconds.getInstance().enhance(instant.getEpochSecond());

		// offset = [instant] - [counter]
		long instantNanos = Math.multiplyExact(utc, NANO) + instant.getNano();
		return Math.subtractExact(instantNanos, compareNanos);
	}

	private long utcNanos() {
		return Math.addExact(tickProvider.nextTickOfNanoseconds(), this.offset);
	}

	@Override
	public Instant instant() {
		System.out.println("offset: " + offset);
		System.out.println("getNanos: " + tickProvider.nextTickOfNanoseconds());
		return Instant.ofEpochSecond(tickProvider.nextTickOfNanoseconds());
	}

	public static void main(String[] args) {
		System.out.println(new SystemClock().instant());
		System.out.println(new SystemClock().instant().getNano());
		System.out.println(new SystemClock().instant().getEpochSecond());
		System.out.println(new SystemClock().instant());
		Clock.systemUTC().instant();
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());


		System.out.println("TimeSpan.Zero => " + TimeSpan.Zero);
		System.out.println("TimeSpan.44 => " + TimeSpan.Builder().minutes(59).build());
		System.out.println("xx TimeSpan.Zero => " + TimeSpan.Zero.toStringFull());
		System.out.println("xxTimeSpan.44 => " + TimeSpan.Builder().days(77).milliseconds(123).microseconds(0).nanoseconds(678).build().toStringFull());

		TimeSpan a = TimeSpan.Builder().minutes(-5).build();
		TimeSpan b = TimeSpan.Builder().minutes(10).build();
		TimeSpan result = a.add(b).add(TimeSpan.fromElapsedInNanoseconds(100000000000L));
		System.out.println(result);
	}
}
