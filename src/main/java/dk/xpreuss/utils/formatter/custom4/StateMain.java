package dk.xpreuss.utils.formatter.custom4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StateMain {
	public static void main(String[] args) {
		System.out.println("Starting Custom4");
		final String strPattern = "Hello world'{hello}{x'x} END ME";

		FSM fsm = new FSM();

		State q0 = new State();
		fsm.setInitialState(q0);

		State q1 = new State();
		State q2 = new State();
		State q3 = new State();
		State q4 = new State();
		State q5 = new State();

		fsm.addState(0, q0);
		fsm.addState(1, q1);
		fsm.addState(2, q2);
		fsm.addState(3, q3);
		fsm.addState(4, q4);
		fsm.addState(5, q5);

		fsm.addFinalState(q4);
		fsm.addFinalState(q5);

		fsm.addTransition(q0, )

		List<String> patternList = fsm.parsePattern(strPattern);
		patternList.stream().forEach(System.out::println);

		State startState;
		State goalState;
		CharSequence stateChange;
		//sm.addTransition(startState, goalState, stateChange);



	}

}
