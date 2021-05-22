package dk.xpreuss.utils.timers.clock;

public class TimeSpanFormat {
	public enum FormatPattern {
		NONE, MINIMUM, FULL;
	}

	//
	//  Format
	//
	//  Actions: Main method called from TimeSpan.ToString
	//
	static String format(TimeSpan value, FormatPattern formatPattern) {
		StringBuilder sb = new StringBuilder();
		long days = Math.abs(value.getDays());
		long hours = Math.abs(value.getHours());
		long minutes = Math.abs(value.getMinutes());
		long seconds = Math.abs(value.getSeconds());
		long secondFractions = Math.abs(value.getNanoAdjustment());

		// Pattern Full == [-]dd,HH:mm:ss.fffffffff
		// Pattern Minimum = [-][d,]HH:mm:ss[.fffffffff]
		final String positiveOrNegative = value.getTotalTimeSeconds() < 0 ? "-" : "";
		final String dayHourSep = ",";
		final String hourMinuteSep = ":";
		final String minuteSecondSep = ":";
		final String secondFractionSep = ",";

		sb.append(positiveOrNegative);
		if (formatPattern == FormatPattern.FULL || days != 0) {
			sb.append(days);
			sb.append(dayHourSep);
		}
		sb.append(String.format("%02d", hours));
		sb.append(hourMinuteSep);
		sb.append(String.format("%02d", minutes));
		sb.append(minuteSecondSep);
		sb.append(String.format("%02d", seconds));
		if (formatPattern == FormatPattern.MINIMUM) {
			int effectiveDigits = 9;
			while (effectiveDigits > 0) {
				if (secondFractions % 10 == 0) {
					secondFractions = secondFractions / 10;
					effectiveDigits--;
				} else {
					break;
				}
			}
			if (effectiveDigits > 0) {
				sb.append(secondFractionSep);
				sb.append(String.format("%0" + (effectiveDigits) + "d", secondFractions));
			}
		} else if (formatPattern == FormatPattern.FULL || secondFractions != 0) {
			sb.append(secondFractionSep);
			sb.append(String.format("%09d", secondFractions));
		}
		return sb.toString();
	}
}
