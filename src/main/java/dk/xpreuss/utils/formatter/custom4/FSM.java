package dk.xpreuss.utils.formatter.custom4;

import java.util.*;

public class FSM {
	private State initialState;
	private Map<Integer, State> states = new HashMap(1);
	private Set<State> finalStates = new HashSet<>(1);

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public void addState(int i, State state) {
		states.put(i, state);
	}

	public void addFinalState(State finalState) {
		this.finalStates.add(finalState);
	}

	public List<String> parsePattern(String strPattern) {
		List<String> patternList = new ArrayList<>();

		State currentState = initialState;
		final int[] codePoints = strPattern.codePoints().toArray();
		for(int i=0;i<codePoints.length;i++) {
			int currentCodePointSymbol = codePoints[i];
			currentState = currentState.nextState(currentCodePointSymbol);
		}
		System.out.println("FINISHED");


		return patternList;
	}
}
