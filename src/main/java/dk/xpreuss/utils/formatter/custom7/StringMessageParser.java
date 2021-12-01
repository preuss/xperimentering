package dk.xpreuss.utils.formatter.custom7;

import java.util.*;

public class StringMessageParser {
	private final StringMessageState initialState;
	private final Map<StringMessageState, StringMessageStateAction> stateActions;

	StringMessageParser(StringMessageState initialState, Map<StringMessageState, StringMessageStateAction> stateActions) {
		this.initialState = initialState;
		this.stateActions = stateActions;
	}

	public static StringMessageParserBuilder builder() {
		return new StringMessageParserBuilder();
	}
}

class StringMessageParserBuilder {
	private StringMessageState intitialState;
	private Map<StringMessageState, StringMessageStateAction> stateActions;

	public StringMessageParserBuilder() {
		stateActions = new HashMap<>();
	}

	public void setIntitialState(StringMessageState intitialState) {
		this.intitialState = intitialState;
	}

	public void put(StringMessageState state, StringMessageStateAction action) {
		stateActions.put(state, action);
	}

	public StringMessageParser build() {
		Objects.requireNonNull(intitialState);
		StringMessageParser parser = new StringMessageParser(intitialState, new HashMap<>(stateActions));
		return parser;
	}
}
