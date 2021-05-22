package dk.xpreuss.xperimentering;

import java.io.IOException;
import java.util.Collection;

public interface IByteWriter {
	IByteWriter append(byte b) throws IOException;

	/**
	 * Appends a portion of an array of characters.
	 *
	 * @param bytes Array of characters
	 * @throws IOException               If an I/O error occurs
	 */
	IByteWriter append(byte[] bytes) throws IOException;

	/**
	 * Appends a portion of an array of characters.
	 *
	 * @param bytes Array of characters
	 * @param start Index of the first byte in the array
	 * @param end   Index of the last byte in the array
	 * @throws IndexOutOfBoundsException If {@code start} or {@code end} are negative, {@code start}
	 *                                   is greater than {@code end}, or {@code end} is greater than
	 *                                   {@code bytes.size}
	 * @throws IOException               If an I/O error occurs
	 */
	IByteWriter append(byte[] bytes, int start, int end) throws IOException;

	/**
	 * Appends the specified byte sequence to this writer.
	 *
	 * @param bytes The bytes to append.  If {@code bytes} is
	 *              {@code null}, then it will throw NullPointerException.
	 * @return This writer
	 * @throws IOException          If an I/O error occurs
	 * @throws NullPointerException If bytes any bytes in collection is null.
	 * @since 1.0
	 */
	IByteWriter append(Collection<Byte> bytes) throws IOException;

	/**
	 * Appends 4 bytes from this int. The bytes to be appended is contained in
	 * the 32 bits of the given integer value.
	 *
	 * @param bytes int specifying 4 bytes to be appended
	 * @throws IOException If an I/O error occurs
	 * @since 1.0
	 */
	IByteWriter append(int bytes) throws IOException;
}
