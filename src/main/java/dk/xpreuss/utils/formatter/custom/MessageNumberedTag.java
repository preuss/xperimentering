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
public class MessageNumberedTag extends MessageTag<Integer> {
	public MessageNumberedTag(int argumentNumber, String formatType, String formatStyle) {
		super(argumentNumber, formatType, formatStyle);
	}

	@Override
	public StringBuilder format(Map namedArguments, List numberedArguments) {
//		System.out.println(this);

		StringBuilder retVal = new StringBuilder();

		final int argumentNumber = getArgumentName();

		final Object foundObjectArgument;
		if (numberedArguments.size() > argumentNumber) {
			foundObjectArgument = numberedArguments.get(argumentNumber);
		} else if (namedArguments.containsKey(argumentNumber)) {
			foundObjectArgument = namedArguments.get(argumentNumber);
		} else if (namedArguments.containsKey(String.valueOf(argumentNumber))) {
			foundObjectArgument = namedArguments.get(String.valueOf(argumentNumber));
		} else {
			throw new IllegalArgumentException("Can not find argument for number " + argumentNumber);
		}

		retVal.append(getFormatter()
				.map(format -> format.format(foundObjectArgument))
				.orElseGet(() -> Objects.toString(foundObjectArgument))
		);
		return retVal;
	}

	private static int parseToInteger(String argumentName) {
		return Integer.parseInt(argumentName.trim());
	}

	private static int requireNonNegativeInt(int argumentNumber, String argumentNameForException) {
		if (argumentNumber < 0) {
			throw new IllegalArgumentException("negative " + argumentNameForException + ": " + argumentNumber);
		}
		return argumentNumber;
	}

	@Override
	protected Integer toValidatedArgumentName(Integer argumentNumber) throws IllegalArgumentException {
		Objects.requireNonNull(argumentNumber);
		return requireNonNegativeInt(argumentNumber, "argument number");
	}
}
