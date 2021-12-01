package dk.xpreuss.utils.formatter.custom8;

public class Transition {
	private final State inputState;
	private final Token symbol;
	private final State outputState;

	public Transition(State startState, Token symbol, State newState) {
		this.inputState = startState;
		this.symbol = symbol;
		this.outputState = newState;
	}

	public State getInputState() {
		return inputState;
	}

	public Token getSymbol() {
		return symbol;
	}

	public State getOutputState() {
		return outputState;
	}
}
