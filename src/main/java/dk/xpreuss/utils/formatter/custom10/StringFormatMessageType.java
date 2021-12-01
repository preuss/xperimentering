package dk.xpreuss.utils.formatter.custom10;

/**
 * {paramIndex[,alignment][:formatString]}
 * {paramName[,alignment][:formatString]}
 *
 * Numbered index, from 0
 * #{paramIndex[,alignment][:formatString]}
 *
 * Named index, alphanumeric starting with an alpha
 * ${paramName[,alignment][:formatString]}
 *
 * <p>
 * paramIndex = a number (#)
 * paramName = and alphanumeric
 * <p>
 * Only one type in a string, a param index number or named parameters.
 */

public enum StringFormatMessageType {
	FREE_TEXT,
	WHITE_SPACE,
	ESCAPE,
	ESCAPE_START_MESSAGE, // Escape start message
	MESSAGE_START, // Chosen Start tag of message, example { or ${ or #{
	MESSAGE_END,
	MESSAGE_PARAM,
	MESSAGE_PARAM_INDEX_NAMED,
	MESSAGE_PARAM_INDEX_NUMBERED,
	MESSAGE_FORMAT_TYPE,
	MESSAGE_FORMAT_STYLE
}
/*
			MESSAGE_PARAM_INDEX, MESSAGE_PARAM_NAME,
			MESSAGE_PARAM_ALIGNMENT, MESSAGE_PARAM_FORMAT,
			// Any error returns this token
			ERROR,
			// When completed (EOF), return this token
			END_OF_SOURCE
*/