package dk.xpreuss.utils.timers.clock;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeSpanTest {

	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll executed static");
	}

	@AfterAll
	static void tear() {
		System.out.println("@AfterAll executed");
	}

	@Test
	public void testInterval() {
	}

	@Test
	public void testFromElapsedInNanoseconds() {
		final long totalNanoSecondsTestValue = 1234567890098765432L;
		final long nanoSecondsTestValue = 432L;
		TimeSpan sut = TimeSpan.fromElapsedInNanoseconds(totalNanoSecondsTestValue);
		long nanoseconds = sut.getNanoseconds();
		long totalNanoseconds = sut.getTotalTimeNanoseconds();

		assertEquals(nanoseconds, nanoSecondsTestValue);
		assertEquals(totalNanoseconds, totalNanoSecondsTestValue);
	}

	@Test
	public void testBuilder() {
		final TimeSpan oneNano = TimeSpan.Builder().nanoseconds(1).build();
		final TimeSpan oneMicro = TimeSpan.Builder().microseconds(1).build();
		final TimeSpan oneMilli = TimeSpan.Builder().milliseconds(1).build();
		final TimeSpan oneSecond = TimeSpan.Builder().seconds(1).build();
		final TimeSpan oneMinute = TimeSpan.Builder().minutes(1).build();
		final TimeSpan oneHour = TimeSpan.Builder().hours(1).build();
		final TimeSpan oneDay = TimeSpan.Builder().days(1).build();
		final TimeSpan oneWeek = TimeSpan.Builder().weeks(1).build();

		assertEquals(1, oneNano.getTotalTimeNanoseconds(), "one nano with total nanoseconds");
		assertEquals(1, oneNano.getNanoseconds(), "nanoseconds");

		assertEquals(1, oneMicro.getTotalTimeMicroseconds(), "micro with total microseconds");
		assertEquals(1, oneMicro.getMicroseconds());

		assertEquals(1, oneMilli.getTotalTimeMilliseconds());
		assertEquals(1, oneMilli.getMilliseconds());

		assertEquals(7*24*60*60*1000_000_000L, oneWeek.getTotalTimeNanoseconds(), "one week with total nanoseconds");
		assertEquals(0, oneWeek.getMilliseconds());
		assertEquals(0, oneWeek.getHours());
		assertEquals(0, oneWeek.getDays(), "one week with days" );
		assertEquals(1, oneWeek.getWeeks(), "one week as weeks");

	}

	@Test
	public void testGetWeeks() {
	}

	@Test
	public void testGetDays() {
	}

	@Test
	public void testGetHours() {
	}

	@Test
	public void testGetMinutes() {
	}

	@Test
	public void testGetSeconds() {
	}

	@Test
	public void testGetMilliseconds() {
	}

	@Test
	public void testGetMicroseconds() {
	}

	@Test
	public void testGetNanoseconds() {
	}

	@Test
	public void testGetTotalTimeWeeks() {
	}

	@Test
	public void testGetTotalTimeDays() {
	}

	@Test
	public void testGetTotalTimeHours() {
	}

	@Test
	public void testGetTotalTimeMinutes() {
	}

	@Test
	public void testGetTotalTimeSeconds() {
	}

	@Test
	public void testGetTotalTimeMilliseconds() {
	}

	@Test
	public void testGetTotalTimeMicroseconds() {
	}

	@Test
	public void testGetTotalTimeNanoseconds() {
	}

	@Test
	public void testGetNanoAdjustment() {
	}

	@Test
	public void testAdd() {
	}

	@Test
	public void testSubtract() {
	}

	@Test
	public void testNegate() {
	}

	@Test
	public void testDuration() {
	}

	@Test
	public void testTestToString() {
	}

	@Test
	public void testToStringFull() {
		final TimeSpan ONE_NANO = TimeSpan.fromElapsedInNanoseconds(1);
		final TimeSpan ONE_SECOND = TimeSpan.Builder().seconds(1).build();
		final TimeSpan ONE_MINUTE = TimeSpan.Builder().minutes(1).build();
		final TimeSpan ONE_HOUR = TimeSpan.Builder().hours(1).build();
		final TimeSpan ONE_DAY = TimeSpan.Builder().days(1).build();
		final TimeSpan ONE_WEEK = TimeSpan.Builder().weeks(1).build();

		String expectedNano = "00:00:00,000000001";
		String expectedSecond = "00:00:01";
		String expectedMinute = "00:01:00";
		String expectedHour = "01:00:00";
		String expectedDay = "1,00:00:00";
		String expectedWeek = "1w00:00:00";
		assertEquals(expectedNano, ONE_NANO.toString(), "Nano");
		assertEquals(expectedSecond, ONE_SECOND.toString(), "Second");
		assertEquals(expectedMinute, ONE_MINUTE.toString(), "Minute");
		assertEquals(expectedHour, ONE_HOUR.toString(), "Hour");
		assertEquals(expectedDay, ONE_DAY.toString(), "Day");
		assertEquals(expectedWeek, ONE_WEEK.toString(), "Week");

	}

	@Test
	public void testCompareTo() {
		final TimeSpan ONE = TimeSpan.fromElapsedInNanoseconds(1_000_000_000L);
		MatcherAssert.assertThat("timespan",
				ONE,
				Matchers.greaterThan(TimeSpan.ZERO));
	}
}