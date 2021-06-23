package dk.xpreuss.utils.formatter.custom;

import java.util.Formattable;
import java.util.Formatter;

public class Main55 {
	public static void main(String[] args) {
		final Formattable f = new Formattable() {
			@Override
			public void formatTo(final Formatter formatter, final int flags, final int width, final int precision) {
				final StringBuilder sb = new StringBuilder();
				sb.append(flags);
				sb.append(":");
				sb.append(width);
				sb.append(":");
				sb.append(precision);
				formatter.format("%s", sb);
			}
		};
		final String result = String.format("%.3s", f);
		System.out.println("Result   : " + result);
		String expected = "0:-1:3";
		System.out.println("Expected : " + expected);
	}
}
