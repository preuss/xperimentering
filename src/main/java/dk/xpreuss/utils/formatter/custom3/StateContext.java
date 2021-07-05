package dk.xpreuss.utils.formatter.custom3;

public class StateContext {
	private State state;

	public StateContext() {
		state = new LowerCaseState();
	}

	/**
	 * Set the current state.
	 * Normally only called by classes implementing the State interface.
	 * @param newState the new state of this context
	 */
	void setState(State newState) {
		state = newState;
	}

	public void writeName(String name) {
		state.writeName(this, name);
	}

	public static void main(String[] args) {
		StateContext context = new StateContext();

		context.writeName("Monday");
		context.writeName("Tuesday");
		context.writeName("Wednesday");
		context.writeName("Thursday");
		context.writeName("Friday");
		context.writeName("Saturday");
		context.writeName("Sunday");
	}
}
