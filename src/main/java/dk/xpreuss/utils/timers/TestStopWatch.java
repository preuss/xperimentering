package dk.xpreuss.utils.timers;

import dk.xpreuss.utils.timers.clock.providers.MilliTickProvider;
import dk.xpreuss.utils.timers.clock.providers.NanoTickProvider;

import java.time.LocalDateTime;
import java.util.Date;

public class TestStopWatch {
	public static void main(String[] args) throws InterruptedException {
		IStopWatch stopWatch = StopWatch.from(new MilliTickProvider());
		//stopWatch = new StopWatch(new NanoTickProvider());
		stopWatch.start();
		//Thread.sleep(seconds(5));
		stopWatch.stop();
		System.out.println(stopWatch.elapsed());
		System.out.println(new Date(-1));
		System.out.println(new Date(0));
		//System.out.println(new Date(Long.MIN_VALUE));
		System.out.println(LocalDateTime.of(-1, 1, 1, 1, 1));
	}

	public static long seconds(long seconds) {
		return seconds * 1_000;
	}
}
