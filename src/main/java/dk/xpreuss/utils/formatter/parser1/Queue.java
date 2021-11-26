package dk.xpreuss.utils.formatter.parser1;

import java.util.List;
import java.util.NoSuchElementException;

public interface Queue<E> {
	/**
	 * Add a new element to queue, to the tail.
	 * @param item the element to be added
	 * @throws FullQueueException if the queue does not have more room
	 */
	void enqueue(E item) throws FullQueueException;

	/**
	 * Removes from the head, and returns this element.
	 * @return element
	 * @throws NoSuchElementException if this Queue is empty
	 */
	E dequeue() throws NoSuchElementException;

	/**
	 * Retrieves, but does not remove, the head of this queue, or throws exception if this queue is empty.
	 *
	 * @return The object at the beginning of the Queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	E peek() throws NoSuchElementException;
	/**
	 * Retrieves, but does not remove, the index count of this queue, or returns null there is no element on this index.
	 *
	 * @param index of the object to be peeked, count from zero (0)
	 * @return The object at the index of the Queue
	 * @throws NoSuchElementException if the is not element on this index.
	 */
	E peekAt(int index) throws NoSuchElementException;

	/**
	 * Retrieves, but does not remove, a list of elements from this queue size of count.
	 * But if the count is greater than queue size, throws exception.
	 * @param count
	 * @return List of elements size of count
	 * @throws NoSuchElementException if too large count bigger than size of queue.
	 */
	List<E> peekRange(int count) throws NoSuchElementException;

	/**
	 * If this queue is full, and there is no room for more elements.
	 * @return boolean true if this queue is full
	 */
	boolean isFull();

	/**
	 * Tells if this queue is size zero (0) and empty.
	 * @return true is empty
	 */
	boolean isEmpty();

	/**
	 * Empties the queue, to size zero (0)
	 */
	void clear();

	/**
	 * The size of this queue.
	 * @return the size of this queue
	 */
	int size();
}
