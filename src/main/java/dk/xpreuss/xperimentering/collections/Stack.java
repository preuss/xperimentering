package dk.xpreuss.xperimentering.collections;

/**
 *
 * @param <T>
 */
public interface Stack<T> {
	/**
	 * create and empty stack
	 * @param item the item to add on top
	 */
	void push(T item);
	/**
	 * Removes the top on stack and returns it
	 * @return item
	 */
	T pop();
	/**
	 * Tells if this stack is empty
	 * @return boolean is this stack is empty
	 */
	boolean isEmpty();
	/**
	 * Tells the size of stack, and returns 0 if empty.
	 * @return int size of stack
	 */
	int size();
}
