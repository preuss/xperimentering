package dk.xpreuss.utils.timers.otherproviders;

import net.time4j.scale.TickProvider;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CpuTickProvider implements TickProvider {
	private final ThreadMXBean threadTimer;

	public CpuTickProvider() {
		this.threadTimer = ManagementFactory.getThreadMXBean();
	}

	@Override
	public String getPlatform() {
		return System.getProperty("java.vm.name");
	}

	@Override
	public long getNanos() {
		return threadTimer.getCurrentThreadCpuTime();
	}
}
