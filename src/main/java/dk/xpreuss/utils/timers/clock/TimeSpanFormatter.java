package dk.xpreuss.utils.timers.clock;

import io.reactivex.rxjava3.core.Single;

import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Objects;

public class TimeSpanFormatter {
	TimeSpanFormatter() {

	}

	public String format(TimeSpan timeSpan) {
		StringBuilder buffer = new StringBuilder(24);
		formatTo(timeSpan, buffer);
		return buffer.toString();
	}

	public void formatTo(TimeSpan timeSpan, Appendable appendable) {
		Objects.requireNonNull(timeSpan, "timeSpan");
		Objects.requireNonNull(appendable, "appendable");

		// Complete Time
		String basicCompleteTimePatternDuration = "PnYnMnDTnHnMnS";
		String extendedCompleteTimePatternDuration = "PnYnMnDTnHnMnS";
		// Complete Week
		String basicCompleteWeekPatternDuration = "PnW";
		String extendedCompleteWeekPatternDuration = "PnW";
		// Reduced Time
		String basicReducedTimePatternDuration = "PnYnMnDTnHnMnS";
		String extendedReducedTimePatternDuration = "PnYnMnDTnHnMnS";
		// Reduced Week
		String basicReducedWeekPatternDuration = "PnW";
		String extendedReducedWeekPatternDuration = "PnW";
		String pattern = basicCompleteTimePatternDuration;
		char [] compiledPattern;
		/*
		try {
			TimeSpanPrintContext context = new TimeSpanPrintContext(timeSpan, this);
			if (appendable instanceof StringBuilder) {
				printerParser.format(context, (StringBuilder) appendable);
			} else {
				// buffer output to avoid writing to appendable in case of error
				StringBuilder buf = new StringBuilder(32);
				printerParser.format(context, buf);
				appendable.append(buf);
			}
		}
		*/
	}

	public static void main(String[] args) {
		DateTimeFormatter f;
	}
}
