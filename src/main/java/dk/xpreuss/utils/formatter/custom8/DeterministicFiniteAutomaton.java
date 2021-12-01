package dk.xpreuss.utils.formatter.custom8;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

// From https://github.com/epicdelia/Theoretical-Foundations-Project/blob/master/Theo/DFANew/src/DFA.java
public class DeterministicFiniteAutomaton {
	private final Set<State> states;
	private final Set<Token> alphabet;
	private final Set<Transition> transitions;

	/*
	 * Constructs a new DFA.
	 *
	 * @param states          The set of states which the DFA may be in.
	 * @param inputAlphabet   The set of inputs which may be supplied to the DFA.
	 * @param transitions     A list of transitions between states on inputs.
	 */
	public DeterministicFiniteAutomaton() {
		this.states = new HashSet<>();
		this.alphabet = new HashSet<>();
		this.transitions = new HashSet<>();
	}

	public void addToAlphabet(Token symbol) {
		alphabet.add(symbol);
	}

	public void removeFromAlphabet(Token symbol) {
		alphabet.remove(symbol);
	}

	public void addState(State state) {
		states.add(state);
	}

	public void removeState(State state) {
		states.remove(state);
	}

	public void addTransition(Transition transition) throws IllegalArgumentException {
		// no 2nd outputs for same input + symbol
		if (transitions.stream()
				.noneMatch(t ->
						t.getInputState().equals(transition.getInputState())
								&& t.getSymbol().equals(transition.getSymbol())
				)
		) {
			transitions.add(transition);
		} else {
			throw new IllegalArgumentException("No 2nd outputs for same input+symbol");
		}
	}

	public void removeTransition(Transition transition) {
		transitions.remove(transition);
	}

	public Set<State> getAcceptStates() {
		return states.stream().filter(state -> state.isAcceptState())
				.collect(Collectors.toSet());
	}

	public State calculateFinalState(State state, Queue<Token> symbol) throws IllegalStateException, IllegalArgumentException {
		if (symbol.isEmpty() && state.isAcceptState()) {
			return state;
		}
		if (!alphabet.contains(symbol.peek())) {
			throw new IllegalArgumentException();
		}
		Optional<State> nextState = getNextState(state, symbol.poll());
		if (nextState.isPresent()) {
			return calculateFinalState(nextState.get(), symbol);
		}
		throw new IllegalStateException();
	}

	private Optional<State> getNextState(State state, Token alphabet) {
		return
				transitions.stream()
						.filter(t -> t.getInputState().equals(state) &&
								t.getSymbol().equals(alphabet))
						.map(t -> t.getOutputState()).findFirst();
	}

	public static void main(String[] args) {
		DeterministicFiniteAutomaton dfa = new DeterministicFiniteAutomaton();

		Token textToken = new Token("t");
		Token startToken = new Token("s");
		Token endToken = new Token("e");

		dfa.addToAlphabet(textToken);
		dfa.addToAlphabet(startToken);
		dfa.addToAlphabet(endToken);

		List<Transition> startTrans = new LinkedList<>();
		State start = new State(startTrans, true);
		start.setName("Start State");
		dfa.addState(start);

		List<Transition> textTrans = new LinkedList<>();
		State text = new State(textTrans, true);
		text.setName("Text State");
		dfa.addState(text);


		List<Transition> endTrans = new LinkedList<>();
		State end = new State(startTrans, false);
		end.setName("End State");
		dfa.addState(end);

		startTrans.add(new Transition(start, startToken, start));
		startTrans.add(new Transition(start, endToken, end));
		textTrans.add(new Transition(text, textToken, text));
		textTrans.add(new Transition(text, endToken, end));
		endTrans.add(new Transition(end, startToken, start));

		startTrans.forEach(dfa::addTransition);
		textTrans.forEach(dfa::addTransition);
		endTrans.forEach(dfa::addTransition);

		LinkedList<Token> inputList = new LinkedList<>();
		inputList.add(startToken);
		inputList.add(textToken);
		inputList.add(endToken);
		State s = dfa.calculateFinalState(start, inputList);


	}
}
