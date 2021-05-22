package dk.xpreuss.utils.timers.clock.providers;

public class MilliTickProvider implements ITickProvider {
	private static final int MILLISECONDS_TO_NANOSECONDS = 1_000_000;

	@Override
	public ITick.Resolution getMaxResolution() {
		return ITick.Resolution.MILLISECONDS;
	}

	@Override
	public long nextTickOfNanoseconds() {
		return System.currentTimeMillis() * MILLISECONDS_TO_NANOSECONDS;
	}

	@Override
	public long nextTickOfMilliseconds() {
		return System.currentTimeMillis();
	}

	@Override
	public ITick nextTick() {
		return new Tick(getMaxResolution(), System.currentTimeMillis());
	}
}
