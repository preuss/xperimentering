package dk.xpreuss.utils.timers.clock.providers;

/**
 * This is a holder for tick values, that has a resolution.
 *
 */
public class Tick implements ITick {
	private final Resolution internalResolution;

	private final long rawTicks;

	public Tick(Resolution internalResolution, long rawTicks) {
		this.internalResolution = internalResolution;
		this.rawTicks = rawTicks;
	}

	@Override
	public Resolution getRawTickResolution() {
		return internalResolution;
	}

	@Override
	public long getRawTicks() {
		return rawTicks;
	}
}
