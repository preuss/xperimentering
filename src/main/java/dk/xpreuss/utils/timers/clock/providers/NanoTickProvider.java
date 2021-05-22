package dk.xpreuss.utils.timers.clock.providers;

public class NanoTickProvider implements ITickProvider {
	private static final long NANO_TO_MILLI = 1_000_000;

	@Override
	public ITick.Resolution getMaxResolution() {
		return ITick.Resolution.NANOSECONDS;
	}

	@Override
	public long nextTickOfNanoseconds() {
		return System.nanoTime();
	}

	@Override
	public long nextTickOfMilliseconds() {
		return System.nanoTime() / NANO_TO_MILLI;
	}

	@Override
	public ITick nextTick() {
		return new Tick(getMaxResolution(), System.nanoTime());
	}
}