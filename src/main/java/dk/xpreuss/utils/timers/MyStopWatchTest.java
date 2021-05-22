package dk.xpreuss.utils.timers;

import dk.xpreuss.utils.timers.clock.TimeSpan;
import dk.xpreuss.utils.timers.clock.providers.*;
import net.time4j.CalendarUnit;
import net.time4j.Duration;
import net.time4j.Moment;
import net.time4j.SystemClock;
import net.time4j.base.ResourceLoader;
import net.time4j.clock.AbstractClock;
import net.time4j.clock.AdjustableClock;
import net.time4j.scale.TickProvider;
import net.time4j.scale.TimeScale;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.Clock;
import java.util.concurrent.TimeUnit;


import lombok.NonNull;

public class MyStopWatchTest {
	public static void main(@NonNull String[] args) throws InterruptedException {
		Thread.sleep(1_000L);


		//StopWatch x = new StopWatch(new CpuTickProvider()) ;
		//StopWatch x = new StopWatch(new MilliTickProvider()) ;
		IStopWatch x = StopWatch.from(new NanoTickProvider());
		x.start();
		Thread.sleep(500L);
		System.out.println("ELAPSED: 0,5xxx ==" + x.elapsed());
		System.out.println("Lap last: " + x.elapsedLastLap());
		System.out.println("Lap last: " + x.elapsedLastLap());
		Thread.sleep(250L);
		System.out.println("Lap ELAPSED: 0,250 ==" + x.lap());
		System.out.println("Lap last: " + x.elapsedLastLap());
		System.out.println("Lap ELAPSED: " + x.lap());
		System.out.println("ELAPSED: " + x.elapsed());
		System.out.println("ELAPSED: " + x.elapsed());
		System.out.println("Lap ELAPSED: " + x.lap());
		Thread.sleep(1200L);
		x.pause();
		Thread.sleep(2500L);
		//x.resume();
		x.start();
		System.out.println("Lap: " + x.lap());
		Thread.sleep(5000L);
		x.stop();
		System.out.println(x.elapsed());
	}
}

