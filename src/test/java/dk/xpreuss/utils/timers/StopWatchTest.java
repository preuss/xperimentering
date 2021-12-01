package dk.xpreuss.utils.timers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StopWatchTest {
	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll executed static");
	}

	@AfterAll
	static void tear() {
		System.out.println("@AfterAll executed");
	}

	/**
	 * determine whether two numbers are "approximately equal" by seeing if they
	 * are within a certain "tolerance percentage," with `tolerancePercentage` given
	 * as a percentage (such as 10.0 meaning "10%").
	 *
	 * @param desiredValue
	 * @param actualValue
	 * @param tolerancePercentage 1 = 1%, 2.5 = 2.5%, etc.
	 * @return if actualValue is inside tolerance +- %
	 */
	public static boolean approximatelyEqual(Number desiredValue, Number actualValue, Number tolerancePercentage) {
		double diff = Math.abs(desiredValue.doubleValue() - actualValue.doubleValue());            //  1000 - 950  = 50
		double tolerance = tolerancePercentage.doubleValue() / 100.0 * desiredValue.doubleValue(); //  20/100*1000 = 200
		return diff <= tolerance; //  50<=200      = true
	}

	public static boolean isBetweenFromTo(Number actualValue, Number validFrom, Number validTo) {
		return actualValue.doubleValue() >= validFrom.doubleValue() && actualValue.doubleValue() <= validTo.doubleValue();
	}

	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}

	@Test
	public void testLap() throws InterruptedException {
		IStopWatch stopWatch = StopWatch.from("TestLap");
		stopWatch.start();
		final long SLEEP_FIRST = 100L;
		final long APPROX_TIME = 50L;
		Thread.sleep(SLEEP_FIRST);
		long elapsedLastLap = stopWatch.elapsedLastLap().getTotalTimeMilliseconds();
		long elapsedCurrentLap = stopWatch.elapsedCurrentLap().getTotalTimeMilliseconds();
		long elapsed = stopWatch.elapsed().getTotalTimeMilliseconds();
		long elapsedcurrentLapFinished = stopWatch.lap().getTotalTimeMilliseconds();
		assertTrue(approximatelyEqual(elapsedLastLap, elapsedCurrentLap, 5), "Elapsed current lap and last lap should be the same.");
		assertTrue(isBetweenFromTo(elapsedLastLap, SLEEP_FIRST, SLEEP_FIRST + APPROX_TIME));
		assertTrue(isBetweenFromTo(elapsedCurrentLap, SLEEP_FIRST, SLEEP_FIRST + APPROX_TIME));
		assertTrue(isBetweenFromTo(elapsed, SLEEP_FIRST, SLEEP_FIRST + APPROX_TIME));
		assertTrue(isBetweenFromTo(elapsedcurrentLapFinished, SLEEP_FIRST, SLEEP_FIRST + APPROX_TIME));

		final long SLEEP_SECOND = 200L;
		long currentTotalSleep = SLEEP_FIRST + SLEEP_SECOND;
		Thread.sleep(SLEEP_SECOND);
		elapsedLastLap = stopWatch.elapsedLastLap().getTotalTimeMilliseconds();
		elapsedCurrentLap = stopWatch.elapsedCurrentLap().getTotalTimeMilliseconds();
		elapsed = stopWatch.elapsed().getTotalTimeMilliseconds();
		elapsedcurrentLapFinished = stopWatch.lap().getTotalTimeMilliseconds();
		assertTrue(isBetweenFromTo(elapsedLastLap, SLEEP_FIRST, SLEEP_FIRST + APPROX_TIME), "Should be " + SLEEP_FIRST + " but was " + elapsedLastLap);
		assertTrue(isBetweenFromTo(elapsedCurrentLap, SLEEP_SECOND, SLEEP_SECOND + APPROX_TIME));
		assertTrue(isBetweenFromTo(elapsed, currentTotalSleep, currentTotalSleep + APPROX_TIME));
		assertTrue(isBetweenFromTo(elapsedcurrentLapFinished, SLEEP_SECOND, SLEEP_SECOND+ APPROX_TIME));

	}

	@Test
	public void testIsRunning() {
		assertTrue(true);
	}

	@Test
	public void testIsPaused() {
		assertTrue(true);
	}

	@Test
	public void testIsStopped() {
		assertTrue(true);
	}

	@Test
	public void testGetTimeLaps() {
		assertTrue(true);
	}

	@AfterEach
	void tearThis() {
		System.out.println("@AfterEach executed");
	}
}