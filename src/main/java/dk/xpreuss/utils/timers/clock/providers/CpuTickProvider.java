package dk.xpreuss.utils.timers.clock.providers;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CpuTickProvider implements ITickProvider {
	private static final long NANO_TO_MILLI = 1_000_000;

	private final ThreadMXBean threadTimer;

	public CpuTickProvider() {
		this.threadTimer = ManagementFactory.getThreadMXBean();
	}

	@Override
	public ITick.Resolution getMaxResolution() {
		return ITick.Resolution.NANOSECONDS;
	}

	@Override
	public long nextTickOfNanoseconds() {
		return threadTimer.getCurrentThreadCpuTime();
	}

	@Override
	public long nextTickOfMilliseconds() {
		return threadTimer.getCurrentThreadCpuTime() / NANO_TO_MILLI;
	}

	@Override
	public ITick nextTick() {
		return new Tick(getMaxResolution(), threadTimer.getCurrentThreadCpuTime());
	}
}
