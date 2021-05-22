package dk.xpreuss.utils.timers;

import dk.xpreuss.utils.timers.clock.TimeSpan;
import dk.xpreuss.utils.timers.clock.providers.ITickProvider;

import java.sql.Time;
import java.util.List;

/**
 * SE https://github.com/maaza/Stopwatch/blob/master/edu/nyu/pqs/stopwatch/api/IStopwatch.java
 * for idé
 * <p>
 * An interface defining a StopWatch used for timing, including laps and pause.
 * The stopwatch objects are created from each implementation.
 */
public interface IStopWatch {
	//long getTicksInNanoseconds();
	//long getElapsedTicksInNanoseconds();

	/**
	 * Returns the name or id of this stopwatch.
	 *
	 * @return the name or id of this stopwatch. Can never be null.
	 */
	String getName();
	/**
	 * Starts the stopwatch.
	 *
	 * @throws IllegalStateException if called when the stopwatch is already running.
	 */
	void start() throws IllegalStateException;
	/**
	 * Stops the stopwatch (and records one final lap).
	 *
	 * @throws IllegalStateException if called when the stopwatch isn't running.
	 */
	void stop() throws IllegalStateException;

	/**
	 * Resets the stopwatch.  If the stopwatch is running, this method stops the watch and resets it.  This also clears all recorded laps.
	 */
	void reset();

	/**
	 * Convenience method for replacing {sw.Reset(); sw.Start();} with a single sw.Restart().
	 */
	void restart();
	/**
	 * Pauses an already startet stopwatch that has not been paused.
	 * It will save the current elapsed time.
	 *
	 * @throws IllegalStateException if called when the stopwatch is not running or when stopwatch is already paused.
	 */
	void pause() throws IllegalStateException;
	/**
	 * @throws IllegalStateException if called when the stopwatch
	 */
	void resume() throws IllegalStateException;
	/**
	 * Stores the time elapsed since the last time lap() was called or since start() was called if this is the first lap.
	 *
	 * @return the lap elapsed that has just been done
	 * @throws IllegalStateException if called when the stopwatch isn't running
	 */
	TimeSpan lap();
	ITickProvider getTickProvider();
	TimeSpan elapsed();
	TimeSpan elapsedLastLap();
	TimeSpan elapsedCurrentLap();
	long elapsedNanoseconds();
	long elapsedMilliseconds();
	double elapsedSeconds();
	boolean isRunning();
	boolean isPaused();
	boolean isStopped();
	/**
	 * Returns a list of lap times (in TimeSpans).  This method can be called at any time and will not throw an exception.
	 *
	 * @return a list of recorded lap times or an empty list if no times are recorded.
	 */
	List<TimeSpan> getTimeLaps();


}
