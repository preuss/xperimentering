package dk.xpreuss.utils.formatter.parser3;

import dk.xpreuss.utils.formatter.parser1.CodePoint;

import java.util.List;

/**
 * @author preuss¤Overwatch
 */
public interface IScanner {
	/**
	 * Tells if there is any left chars.
	 *
	 * @return true if there is more chars to be had.
	 */
	boolean hasNext();
	/**
	 * Tells if End Of File, meaning no more chars.
	 *
	 * @return true if it is empty, and no more chars.
	 */
	boolean isEof();
	/**
	 * Retrieves and removes the head of the list of the stream of chars, or returns null if empty.
	 *
	 * @return the next head CodePoint in this list of chars.
	 */
	CodePoint next();
	/**
	 * Retrieves, but does not remove, the head of the stream.
	 *
	 * @return the next head CodePoint in this list of chars.
	 */
	CodePoint peek();
	/**
	 * Retrieves and removes from the head of the list of stream of chars, or returns an empty list if empty.
	 * Only returns up to count of choosen nextCount. Only returns the available count always size of nextCount or less.
	 *
	 * @param nextCount the number of CodePoints to return
	 * @return the list of codePoints specified in nextCount
	 */
	List<CodePoint> readCodePoint(int nextCount);
	/**
	 * Retrieves, but does not remove, the head of the stream.
	 * Only returns up to count of choosen nextCount. Only returns the available count always size of nextCount or less.
	 *
	 * @param peekCount the number of CodePoints to return
	 * @return the list of codePoints specified in nextCount
	 */
	List<CodePoint> peekCodePoint(int peekCount);

	/**
	 * This is the index of the current char. Zero indexed.
	 * -1 specifies not called next() yet.
	 * >=0 specifies current chars pointer.
	 * Where 0 is the first chars, after first call of next().
	 * Pointer will not change if more calls to next() than chars exists, where next() returns null.
	 * @return the pointer index of current char
	 */
	int getPointer();
}
