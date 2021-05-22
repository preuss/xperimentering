package dk.xpreuss.utils.timers.clock.providers;

public interface ITickProvider {
	ITick.Resolution getMaxResolution();

	/**
	 * <p>Generates a count of nanoseconds. </p>
	 *
	 * @return count of nanosecond ticks since an arbitrary but fixed start time which is typically the boot time
	 */
	long nextTickOfNanoseconds();
	long nextTickOfMilliseconds();
	ITick nextTick();
}

