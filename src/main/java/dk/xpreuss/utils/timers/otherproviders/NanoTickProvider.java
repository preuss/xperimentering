package dk.xpreuss.utils.timers.otherproviders;

import net.time4j.scale.TickProvider;

public class NanoTickProvider implements TickProvider {
	@Override
	public String getPlatform() {
		return System.getProperty("java.vm.name");
	}

	@Override
	public long getNanos() {
		return System.nanoTime();
	}
}