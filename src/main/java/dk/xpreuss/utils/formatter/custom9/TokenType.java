package dk.xpreuss.utils.formatter.custom9;

/**
 * {paramIndex[,alignment][:formatString]}
 * {paramName[,alignment][:formatString]}
 * <p>
 * paramIndex = a number (#)
 * paramName = and alphanumeric
 * <p>
 * Only one type in a string, a param index number or named parameters.
 */

public enum TokenType {
	FREE_TEXT,
	WHITE_SPACE,
	ESCAPE_START_MESSAGE,
	MESSAGE_PARAM,
	// Message operators
	MESSAGE_PARAM_INDEX, MESSAGE_PARAM_NAME,
	MESSAGE_PARAM_ALIGNMENT, MESSAGE_PARAM_FORMAT,
	// Any error returns this token
	ERROR,
	// When completed (EOF), return this token
	END_OF_SOURCE
}

