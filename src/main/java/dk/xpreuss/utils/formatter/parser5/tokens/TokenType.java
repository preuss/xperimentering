package dk.xpreuss.utils.formatter.parser5.tokens;

public enum TokenType {
	FREE_TEXT,
	WHITE_SPACE,
	NEW_LINE,
	ESCAPE,
	ESCAPE_START_MESSAGE,
	START_MESSAGE, END_MESSAGE,
	START_NUMBER_MESSAGE,
	MESSAGE_IDENTIFIER,
	MESSAGE_PARAM,
	// Message operators
	MESSAGE_PARAM_INDEX, MESSAGE_PARAM_NAME,
	MESSAGE_PARAM_ALIGNMENT, MESSAGE_PARAM_FORMAT,
	// Any error returns this token
	ERROR,
	// When completed (EOF), return this token
	END_OF_SOURCE;
}

