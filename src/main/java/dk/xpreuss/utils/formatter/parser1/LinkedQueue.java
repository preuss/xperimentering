package dk.xpreuss.utils.formatter.parser1;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
	transient int size = 0;
	transient int maxSize;

	/**
	 * Pointer to first node.
	 */
	transient LinkedQueue.Node<E> firstNode;

	/**
	 * Pointer to last node.
	 */
	transient LinkedQueue.Node<E> lastNode;

	/**
	 * Constructs an empty queue
	 */
	public LinkedQueue() {
	}

	public LinkedQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * Links e as first element.
	 */
	private void linkFirst(E element) {
		final LinkedQueue.Node<E> firstNode = this.firstNode;
		final LinkedQueue.Node<E> newNode = new LinkedQueue.Node<>(null, element, firstNode);
		this.firstNode = newNode;
		if (firstNode == null) {
			lastNode = newNode;
		} else {
			firstNode.prevNode = newNode;
		}
		size++;
	}

	/**
	 * Links e as last element.
	 */
	private void linkLast(E element) {
		final LinkedQueue.Node<E> lastNode = this.lastNode;
		final LinkedQueue.Node<E> newNode = new LinkedQueue.Node<>(lastNode, element, null);
		this.lastNode = newNode;
		if (lastNode == null) {
			firstNode = newNode;
		} else {
			lastNode.nextNode = newNode;
		}
		size++;
	}

	/**
	 * Unlinks non-null first node f.
	 */
	private E unlinkFirst(LinkedQueue.Node<E> firstNode) {
		// assert f == first && f != null;
		final E element = firstNode.item;
		final LinkedQueue.Node<E> next = firstNode.nextNode;
		firstNode.item = null;
		firstNode.nextNode = null; // help GC
		this.firstNode = next;
		if (next == null) {
			lastNode = null;
		} else {
			next.prevNode = null;
		}
		size--;
		return element;
	}

	/**
	 * Unlinks non-null last node l.
	 */
	private E unlinkLast(LinkedQueue.Node<E> lastNode) {
		// assert l == last && l != null;
		final E element = lastNode.item;
		final LinkedQueue.Node<E> prev = lastNode.prevNode;
		lastNode.item = null;
		lastNode.prevNode = null; // help GC
		this.lastNode = prev;
		if (prev == null) {
			firstNode = null;
		} else {
			prev.nextNode = null;
		}
		size--;
		return element;
	}


	@Override
	public void enqueue(E item) {
		if(isFull()) throw new FullQueueException();
		linkLast(item);
	}

	@Override
	public E dequeue() {
		final LinkedQueue.Node<E> firstNode = this.firstNode;
		if (firstNode == null) {
			throw new NoSuchElementException();
		}
		return unlinkFirst(firstNode);
	}

	@Override
	public E peek() {
		final LinkedQueue.Node<E> firstNode = this.firstNode;
		return (firstNode == null) ? null : firstNode.item;
	}

	@Override
	public E peekAt(int index) throws NoSuchElementException {
		if (index >= size()) {
			throw new NoSuchElementException();
		}
		LinkedQueue.Node<E> currentNode = firstNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.nextNode;
		}
		return currentNode.item;
	}

	@Override
	public List<E> peekRange(int count) throws IllegalStateException, NoSuchElementException {
		if (count < 0) throw new IllegalStateException();
		if (size >= count) {
			final LinkedList<E> peekList = new LinkedList<>();
			LinkedQueue.Node<E> currentNode = firstNode;
			for (int i = 0; i < count; i++) {
				peekList.add(currentNode.item);
				currentNode = currentNode.nextNode;
			}
			return peekList;
		} else throw new NoSuchElementException();
	}

	@Override
	public boolean isFull() {
		return this.maxSize > 0 && this.size >= this.maxSize;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public void clear() {
		this.firstNode = null;
		this.lastNode = null;
	}

	@Override
	public int size() {
		return size;
	}

	private static class Node<E> {
		E item;
		LinkedQueue.Node<E> nextNode;
		LinkedQueue.Node<E> prevNode;

		Node(LinkedQueue.Node<E> prevNode, E element, LinkedQueue.Node<E> nextNode) {
			this.item = element;
			this.nextNode = nextNode;
			this.prevNode = prevNode;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (size() == 0) return "";
		builder.append('{');
		LinkedQueue.Node<E> currentNode = firstNode;
		final String QUEUE_SPACER = " -> ";
		do {
			builder.append('\'').append(currentNode.item).append('\'').append(QUEUE_SPACER);
			currentNode = currentNode.nextNode;
		} while (currentNode != null);
		builder.delete(builder.length() - QUEUE_SPACER.length(), builder.length()); // Deletes the lase QUEUE_SPACER
		builder.append('}');
		return builder.toString();
	}
}
