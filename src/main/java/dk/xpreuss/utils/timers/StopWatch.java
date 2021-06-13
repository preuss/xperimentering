package dk.xpreuss.utils.timers;

import dk.xpreuss.utils.timers.clock.TimeSpan;
import dk.xpreuss.utils.timers.clock.providers.ITick;
import dk.xpreuss.utils.timers.clock.providers.ITickProvider;
import dk.xpreuss.utils.timers.clock.providers.NanoTickProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static dk.xpreuss.utils.timers.StopWatch.State.PAUSED;
import static dk.xpreuss.utils.timers.StopWatch.State.READY;
import static dk.xpreuss.utils.timers.StopWatch.State.RUNNING;
import static dk.xpreuss.utils.timers.StopWatch.State.STOPPED;

public class StopWatch implements IStopWatch {
	public static final long NanosPerMillisecond = 1_000;
	public static final long NanosPerSecond = NanosPerMillisecond * 1_000;

	private final String name;

	private final ITickProvider tickProvider;
	private State runningState;
	//	private boolean isRunning;
//	private boolean isPaused;
	private ITick startTick;
	private TimeSpan elapsedNano;
	private LinkedList<TimeSpan> laps;


	protected StopWatch(String name, ITickProvider tickProvider) {
		this.name = Objects.requireNonNull(name);
		this.tickProvider = tickProvider;
		reset();
	}

	public static IStopWatch from(String name, ITickProvider tickProvider) {
		return new StopWatch(name, tickProvider);
	}

	public static IStopWatch from(ITickProvider tickProvider) {
		return new StopWatch("", tickProvider);
	}

	public static IStopWatch from() {
		return from("");
	}

	public static IStopWatch from(String name) {
		return new StopWatch(name, new NanoTickProvider());
	}

	/**
	 * <p>Generates a count of nanoseconds. </p>
	 *
	 * @return count of nanosecond ticks since an arbitrary but fixed start time which is typically the boot time
	 */
	private ITick getTicks() {
		return tickProvider.nextTick();
	}

	private TimeSpan getElapsedTime() {
		TimeSpan timeElapsedInNano = this.elapsedNano;
		if (RUNNING == this.runningState) {
			// If the StopWatch is running, add elapsed time since the StopWatch is started last time.
			ITick currentTick = getTicks();
			TimeSpan elapsedInNanosecondsUntilNow = currentTick.diff(this.startTick);
			timeElapsedInNano = timeElapsedInNano.add(elapsedInNanosecondsUntilNow);
		}
		return timeElapsedInNano;
	}

	@Override
	public String getName() {
		return name;
	}

	public final void start() {
		if (RUNNING == this.runningState) {
			throw new IllegalStateException("The StopWatch has already been started.");
		}
		if (PAUSED == this.runningState) {
			// Doing a resume
			resume();
		} else {
			this.startTick = getTicks();
			//isRunning = true;
			this.runningState = RUNNING;
		}
	}

	@Override
	public final void stop() {
		if (STOPPED == this.runningState) {
			throw new IllegalStateException("StopWatch is already stopped.");
		}
		ITick endTicksInNano = getTicks();
		TimeSpan elapsedThisPeriod = endTicksInNano.diff(startTick);
		this.elapsedNano = this.elapsedNano.add(elapsedThisPeriod);
		//isRunning = false;
		this.runningState = STOPPED;

		this.elapsedNano = resetIfNegative(this.elapsedNano);
	}

	@Override
	public final void reset() {
		this.runningState = READY;
		//isRunning = false;
		//isPaused = false;
		startTick = getTicks(); // Don't care the start, this is not set yet.
		elapsedNano = TimeSpan.ZERO;
		laps = new LinkedList<>();
	}

	/**
	 * Convenience method for replacing {sw.Reset(); sw.Start();} with a single sw.Restart()
	 */
	@Override
	public final void restart() {
		this.runningState = RUNNING;
		//isRunning = true;
		//isPaused = false;
		startTick = getTicks(); // This is important, because we start again.
		elapsedNano = TimeSpan.ZERO;
		laps = new LinkedList<>();
	}

	@Override
	public void pause() {
		if (!this.runningState.isStarted()) {
			throw new IllegalStateException("StopWatch can not be paused if it is not started.");
		}
		if (this.runningState.isPaused()) {
			throw new IllegalStateException("StopWatch is already paused.");
		}

		ITick nowTicksInNano = getTicks();
		TimeSpan elapsedThisPeriod = nowTicksInNano.diff(startTick);
		this.elapsedNano = this.elapsedNano.add(elapsedThisPeriod);

		//isPaused = true;
		this.runningState = PAUSED;

		this.elapsedNano = resetIfNegative(this.elapsedNano);
	}

	@Override
	public void resume() {
		if (!this.runningState.isStarted()) {
			throw new IllegalStateException("StopWatch can be resumed if it is not started.");
		}
		if (!this.runningState.isPaused()) {
			throw new IllegalStateException("StopWatch has not been paused.");
		}

		ITick nowTicksInNano = getTicks();
		this.startTick = nowTicksInNano;

		//isPaused = false;
		this.runningState = RUNNING;
	}

	@Override
	public TimeSpan lap() {
		//if (isRunning && !isPaused) {
		if (this.runningState.isStarted() && !this.runningState.isPaused()) {
			ITick nowTicksInNano = getTicks();
			TimeSpan elapsedThisLap = nowTicksInNano.diff(this.startTick);
			this.elapsedNano = this.elapsedNano.add(elapsedThisLap);
			this.startTick = nowTicksInNano;

			this.elapsedNano = resetIfNegative(this.elapsedNano);
			this.laps.add(elapsedThisLap);
			return elapsedThisLap;
		} else {
			throw new IllegalStateException("StopWatch is not running or is paused.");
		}
	}

	/**
	 * If measuring small time periods and return of negative value, the reset elapsed time.
	 *
	 * @param elapsedTime to be tested if negative
	 * @return the elapsedTime or the reset to zero if elapsedTime was negative
	 */
	private TimeSpan resetIfNegative(TimeSpan elapsedTime) {
		if (this.elapsedNano.compareTo(TimeSpan.ZERO) < 0) {
			return TimeSpan.ZERO;
		}
		return elapsedTime;
	}

	@Override
	public final boolean isRunning() {
		return this.runningState.isStarted();
	}

	@Override
	public boolean isPaused() {
		return this.runningState.isPaused();
	}

	@Override
	public boolean isStopped() {
		return !(isRunning() || isPaused());
	}

	@Override
	public List<TimeSpan> getTimeLaps() {
		return laps.stream().toList();
	}

	@Override
	public ITickProvider getTickProvider() {
		return tickProvider;
	}

	@Override
	public TimeSpan elapsed() {
		return getElapsedTime();
	}

	@Override
	public TimeSpan elapsedLastLap() {
		if (laps.size() > 0) {
			return laps.getLast();
		} else {
			return getElapsedTime();
		}
	}

	@Override
	public TimeSpan elapsedCurrentLap() {
		TimeSpan timeElapsedInNano = this.elapsedNano;
		if (RUNNING == this.runningState) {
			// If the StopWatch is running, add elapsed time since the StopWatch is started last time.
			ITick currentTick = getTicks();
			TimeSpan elapsedInNanosecondsUntilNow = currentTick.diff(this.startTick);
			//timeElapsedInNano = timeElapsedInNano.add(elapsedInNanosecondsUntilNow);
			timeElapsedInNano = elapsedInNanosecondsUntilNow;
		}
		return timeElapsedInNano;
	}

	@Override
	public long elapsedNanoseconds() {
		return (long) getElapsedTime().getTotalTimeNanoseconds();
	}

	@Override
	public long elapsedMilliseconds() {
		return (long) getElapsedTime().getTotalTimeMilliseconds();
	}

	@Override
	public double elapsedSeconds() {
		return getElapsedTime().getTotalTimeSeconds();
	}

	/**
	 * Enumeration type which indicates the status of stopwatch.
	 */
	protected enum State {
		READY(false, false, false),
		RUNNING(true, false, false),
		PAUSED(true, false, true),
		STOPPED(false, true, false);

		private final boolean isStarted;
		private final boolean isStopped;
		private final boolean isPaused;

		State(boolean isStarted, boolean isStopped, boolean isPaused) {
			this.isStarted = isStarted;
			this.isStopped = isStopped;
			this.isPaused = isPaused;
		}

		public boolean isStarted() {
			return isStarted;
		}

		public boolean isStopped() {
			return isStopped;
		}

		public boolean isPaused() {
			return isPaused;
		}
	}

	@Override
	public String toString() {
		return "StopWatch{" +
				"name='" + name + '\'' +
				", tickProvider=" + tickProvider.getClass().getSimpleName() +
				", runningState=" + runningState.name() +
				", elapsedNano=" + elapsedNano +
				", lapsCount=" + laps.size() +
				'}';
	}
}
