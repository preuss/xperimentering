package dk.xpreuss.utils.formatter.custom;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * c# format
 * -    {paramIndex[,alignment][:formatString]}
 * -    {paramName[,alignment][:formatString]}
 * Java MessageFormat
 * -    {argumentIndex[,formatType[,formatStyle]}
 * -    {index[,type[,subformat]}
 * Java printf
 * -    % [flags] [width] [.precision] conversion-character
 * <p>
 * Where paramIndex or argumentIndex is the int count from 0 of arguments.
 * Where paramName or argumentName is the named parameter.
 *
 * <p>
 * paramIndex = a number (#)
 * paramName = and alphanumeric
 * <p>
 * Only one type in a string, a param index number or named parameters.
 */
public class MessageNamedTag extends MessageTag<String> {
	public MessageNamedTag(String argumentName, String formatType, String formatStyle) {
		super(argumentName, formatType, formatStyle);
	}

	@Override
	protected String toValidatedArgumentName(String argumentName) throws IllegalArgumentException {
		return requireNonNullAlphanumericStartingWithAlpha(argumentName, "argument name");
	}

	@Override
	public StringBuilder format(Map namedArguments, List numberedArguments) {
//		System.out.println(this);

		StringBuilder retVal = new StringBuilder();
		if (getFormatter() == null) return retVal;

		final Object foundArgument;
		if (namedArguments.containsKey(getArgumentName())) {
			foundArgument = namedArguments.get(getArgumentName());
		} else {
			throw new IllegalArgumentException("Can not find argument for name " + getArgumentName());
		}

		retVal.append(getFormatter()
				.map(format -> format.format(foundArgument))
				.orElseGet(() -> Objects.toString(foundArgument))
		);

		return retVal;
	}

	private static String requireNonNullAlphanumericStartingWithAlpha(String argumentValue, String argumentNameForException) {
		Objects.requireNonNull(argumentValue);

		argumentValue = argumentValue.trim();

		if (argumentValue.length() == 0) {
			throw new IllegalArgumentException("The " + argumentNameForException + " is empty");
		}

		if (!Character.isJavaIdentifierStart(argumentValue.charAt(0))) {
			throw new IllegalArgumentException("The " + argumentNameForException + " starts with an illegal char: " + argumentValue);
		}
		for (int i = 1; i < argumentValue.length(); i++) {
			char c = argumentValue.charAt(i);
			if (!Character.isJavaIdentifierPart(c)) {
				System.out.println("Character: " + c);
				throw new IllegalArgumentException("The " + argumentNameForException + " contains an illegal char: " + argumentValue);
			}
		}
		return argumentValue;
	}
}
