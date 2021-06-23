package dk.xpreuss.utils.formatter.custom;

//import com.ibm.icu.text.MessageFormat;
import dk.xpreuss.utils.timers.IStopWatch;
import dk.xpreuss.utils.timers.StopWatch;

import java.text.MessageFormat;
import java.util.*;

public class MessageFormatter {
	public static void main(String[] args) {
		String messagePattern = "Hello world{1}___{0,number,percent}_{2,date,short}";
		String messagePattern2 = "Hello world{b}___{a,number,percent}_{c,date,short}";


		String messageFormattedText = "";
		final Map namedArguments = Map.of(0, 112.8, "1", "ONEONEONE", 2, new Date());
		final Map namedArguments2 = Map.of("a", 112.8, "b", "ONEONEONE", "c", new Date());
		final Object [] numberedArguments = new Object[]{112.8, "TWOTWOTWO", new Date()};
//		final int ITERATIONS = 10_000_000;
		final int ITERATIONS = 1_000_000;
//		final int ITERATIONS = 1;
		IStopWatch sw = StopWatch.from("First");
		sw.start();
		for (int i = 0; i < ITERATIONS; i++) {
			MessageFormat mf = new MessageFormat(messagePattern);
			messageFormattedText = mf.format(numberedArguments);
		}
		sw.stop();
		System.out.println(sw.elapsed());
		System.out.println(messageFormattedText);
		System.out.println("----------------");

		sw = StopWatch.from("Second");
		sw.start();
		for (int i = 0; i < ITERATIONS; i++) {
			Message m = Message.from(messagePattern, namedArguments, new ArrayList(0));
			messageFormattedText = m.format();
		}
		sw.stop();
		System.out.println(sw.elapsed());
		System.out.println(messageFormattedText);
		System.out.println("----------------");

		Optional x;
	}
}
