package dk.xpreuss.utils.formatter.custom;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class MessageTag<T> implements MessageElement {
	private final Locale locale;

	private final T argumentName;
	private final String formatType;
	private final String formatStyle;

	private final Format formatter;

	protected MessageTag(T argumentName, String formatType, String formatStyle) {
		this.locale = Locale.getDefault(Locale.Category.FORMAT);

		this.argumentName = toValidatedArgumentName(argumentName);
		this.formatType = formatType;
		this.formatStyle = formatStyle;

		this.formatter = createAppropriateFormat(formatType, formatStyle, locale);
	}

	/**
	 * @param argumentName the argumentName before conversion
	 * @return the validated and converted argumentName or empty optional if not.
	 * @throws IllegalArgumentException
	 */
	protected abstract T toValidatedArgumentName(T argumentName) throws IllegalArgumentException;

	private static Format createAppropriateFormat(String formatType, String formatStyle, Locale locale) {
		Objects.requireNonNull(formatType);
		// TODO: Implement styles
		Format formatter;
		switch (formatType.trim().toLowerCase(Locale.ROOT)) {
			case "":
				// The null type or empty type.
				formatter = null;
				break;
			case "number":
//				formatter = NumberFormat.getInstance(locale);
				switch (formatStyle) {
					case "":
						formatter = NumberFormat.getInstance(locale);
						break;
					case "currency":
						formatter = NumberFormat.getCurrencyInstance(locale);
						break;
					case "percent":
						formatter = NumberFormat.getPercentInstance(locale);
						break;
					case "integer":
						formatter = NumberFormat.getIntegerInstance(locale);
						break;
					default:
						throw new UnknownError("I don't know yet what to do. TODO using format style {" + formatStyle + "}");
				}
				break;
			case "datetime":
			case "date":
			case "time":
//				formatter = DateFormat.getDateInstance(DateFormatStyle.DEFAULT.value(), locale);
				switch (formatStyle) {
					case "":
						formatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
						break;
					case "short":
						formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
						break;
					case "medium":
						formatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
						break;
					case "long":
						formatter = DateFormat.getDateInstance(DateFormat.LONG, locale);
						break;
					case "full":
						formatter = DateFormat.getDateInstance(DateFormat.FULL, locale);
						break;
					default:
						throw new UnknownError("I don't know yet what to do. TODO");
				}
				break;
			case "choice":
				formatter = null; // TODO: Un-implemented
				break;
			case "spellout":
				formatter = null; // TODO: Un-implemented
				break;
			case "ordinal":
				formatter = null; // TODO: Un-implemented
				break;
			case "duration":
				formatter = null; // TODO: Un-implemented
				break;
			default:
				throw new IllegalArgumentException("unknown format type: " + formatType);
		}
		return formatter;
	}


	final protected Locale getLocale() {
		return locale;
	}

	final protected T getArgumentName() {
		return argumentName;
	}

	final protected Optional<String> getFormatType() {
		return Optional.ofNullable(formatType);
	}

	final protected Optional<String> getFormatStyle() {
		return Optional.ofNullable(formatStyle);
	}

	final protected Optional<Format> getFormatter() {
		return Optional.ofNullable(formatter);
	}

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(argumentName);
		if (!(formatType.length() == 0 && formatStyle.length() == 0)) {
			sb.append(",").append(formatType);

			if (!(formatStyle.length() == 0)) {
				sb.append(",").append(formatStyle);
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "MessageTag{" +
				"argumentName=" + argumentName +
				", formatType='" + formatType + '\'' +
				", formatStyle='" + formatStyle + '\'' +
				", formatter=" + (formatter == null ? null : formatter.getClass().getSimpleName()) +
				'}';
	}
}
