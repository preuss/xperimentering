package dk.xpreuss.utils.formatter.parser5.unicode;


import dk.xpreuss.utils.formatter.parser1.CodePoint;

import java.nio.CharBuffer;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * A {@code CodePointSequence} is a readable sequence of {@code codePoint} values. This
 * interface provides uniform, read-only access to many different kinds of
 * {@code codePoint} sequences.
 * A {@code codePoint} value represents a character in the <i>Basic
 * Multilingual Plane (BMP)</i> or a surrogate. Refer to <a
 * href="Character.html#unicode">Unicode Character Representation</a> for details.
 *
 * <p> This interface does not refine the general contracts of the {@link
 * Object#equals(Object) equals} and {@link
 * Object#hashCode() hashCode} methods. The result of testing two objects
 * that implement {@code CodePointSequence} for equality is therefore, in general, undefined.
 * Each object may be implemented by a different class, and there
 * is no guarantee that each class will be capable of testing its instances
 * for equality with those of the other.  It is therefore inappropriate to use
 * arbitrary {@code CodePointSequence} instances as elements in a set or as keys in
 * a map. </p>
 *
 * @author preuss¤Overwatch
 */

public interface CodePointSequence extends Comparable<CodePointSequence> {

	/**
	 * Returns the length of this character sequence.  The length is the number
	 * of 32-bit {@code codePoint}s in the sequence.
	 *
	 * @return the number of {@code codePoint}s in this sequence
	 */
	int length();

	/**
	 * Returns the {@code codePoint} value at the specified index.  An index ranges from zero
	 * to {@code length() - 1}.  The first {@code codePoint} value of the sequence is at
	 * index zero, the next at index one, and so on, as for array
	 * indexing.
	 *
	 * <p>If the {@code codePoint} value specified by the index is a
	 * <a href="{@docRoot}/java.base/java/lang/Character.html#unicode">surrogate</a>, the surrogate
	 * value is returned.
	 *
	 * @param index the index of the {@code codePoint} value to be returned
	 * @return the specified {@code codePoint} value
	 * @throws IndexOutOfBoundsException if the {@code index} argument is negative or not less than
	 *                                   {@code length()}
	 */
	CodePoint codePointAt(int index);

	/**
	 * Returns {@code true} if this character sequence is empty.
	 *
	 * @return {@code true} if {@link #length()} is {@code 0}, otherwise
	 * {@code false}
	 * @implSpec The default implementation returns the result of calling {@code length() == 0}.
	 * @since 15
	 */
	default boolean isEmpty() {
		return this.length() == 0;
	}

	/**
	 * Returns a {@code CodePointSequence} that is a subsequence of this sequence.
	 * The subsequence starts with the {@code codePoint} value at the specified index and
	 * ends with the {@code codePoint} value at index {@code end - 1}.  The length
	 * (in {@code codePoint}s) of the
	 * returned sequence is {@code end - start}, so if {@code start == end}
	 * then an empty sequence is returned.
	 *
	 * @param start the start index, inclusive
	 * @param end   the end index, exclusive
	 * @return the specified subsequence
	 * @throws IndexOutOfBoundsException if {@code start} or {@code end} are negative,
	 *                                   if {@code end} is greater than {@code length()},
	 *                                   or if {@code start} is greater than {@code end}
	 */
	CodePointSequence subSequence(int start, int end);

	/**
	 * Returns a string containing the characters in this sequence in the same
	 * order as this sequence.  The length of the string will be the length of
	 * this sequence.
	 *
	 * @return a string consisting of exactly this sequence of characters
	 */
	String toString();

	/**
	 * Returns a stream of {@code int} zero-extending the {@code codePoint} values
	 * from this sequence.  Any char which maps to a <a
	 * href="{@docRoot}/java.base/java/lang/Character.html#unicode">surrogate code
	 * point</a> is passed through uninterpreted.
	 *
	 * <p>The stream binds to this sequence when the terminal stream operation
	 * commences (specifically, for mutable sequences the spliterator for the
	 * stream is <a href="../util/Spliterator.html#binding"><em>late-binding</em></a>).
	 * If the sequence is modified during that operation then the result is
	 * undefined.
	 *
	 * @return an IntStream of codePoint values from this sequence
	 * @since 1.8
	 */
	public default IntStream codePoints() {
		class CharIterator implements PrimitiveIterator.OfInt {
			int cur = 0;

			public boolean hasNext() {
				return cur < length();
			}

			public int nextInt() {
				if (hasNext()) {
					return codePointAt(cur++).getValue();
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void forEachRemaining(IntConsumer block) {
				for (; cur < length(); cur++) {
					block.accept(codePointAt(cur).getValue());
				}
			}
		}

		return StreamSupport.intStream(() ->
						Spliterators.spliterator(
								new CharIterator(),
								length(),
								Spliterator.ORDERED),
				Spliterator.SUBSIZED | Spliterator.SIZED | Spliterator.ORDERED,
				false);
	}

	@Override
	default int compareTo(CodePointSequence other) {
		if (this == Objects.requireNonNull(other)) {
			return 0;
		}
		for (int i = 0, len = Math.min(this.length(), other.length()); i < len; i++) {
			CodePoint a = this.codePointAt(i);
			CodePoint b = other.codePointAt(i);
			if (a.equals(b)) {
				return a.compareTo(b);
			}
		}

		return this.length() - this.length();
	}

	public static String toString(CodePointSequence cps) {
		CharBuffer cb = CharBuffer.allocate(cps.length());
		for (int i = 0; i < cps.length(); i++) {
			cb.append(Character.toString(cps.codePointAt(i).getValue()));
		}
		return cb.rewind().toString();
	}
}
