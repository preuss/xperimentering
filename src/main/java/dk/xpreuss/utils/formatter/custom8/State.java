package dk.xpreuss.utils.formatter.custom8;

import java.util.List;
import java.util.Objects;

public class State {
	private final List<Transition> transitions;
	private final boolean acceptState; // And end state
	private String name;

	public State(List<Transition> transitions, boolean acceptState) {
		this.transitions = transitions;
		this.acceptState = acceptState;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public boolean isAcceptState() {
		return acceptState;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		State state = (State) o;
		return acceptState == state.acceptState && Objects.equals(transitions, state.transitions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(transitions, acceptState);
	}

	public void setName(String name) {
		this.name = name;
	}
}
