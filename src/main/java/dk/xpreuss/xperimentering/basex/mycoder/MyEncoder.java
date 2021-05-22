package dk.xpreuss.xperimentering.basex.mycoder;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class MyEncoder {
	/**
	 * This array is a lookup table that translates 6-bit positive integer
	 * index values into their "Base64 Alphabet" equivalents as specified
	 * in "Table 1: The Base64 Alphabet" of RFC 2045 (and RFC 4648).
	 */
	private static final char[] toBase64 = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};


	/**
	 * It's the lookup table for "URL and Filename safe Base64" as specified
	 * in Table 2 of the RFC 4648, with the '+' and '/' changed to '-' and
	 * '_'. This table is used when BASE64_URL is specified.
	 */
	private static final char[] toBase64URL = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
	};

	public static void main(String[] args) throws InterruptedException {
		final String srcStr = "asdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrfasdfælkjasdfælkjasdflkjasdflkjasældkfjasældkfjaæsldkfhjæalsndrvæoislernvælsijrf";
		final byte[] src = srcStr.getBytes(StandardCharsets.UTF_8);
		int[] intArray = {1,2,3,4,5};
//		Arrays.stream(intArray).;

		boolean throwOOME = false;
		boolean isURL = false;
		boolean doPadding = true;
		int linemax = -1;
		byte[] newline = null;

		final int max = 1;
		long start, end;
		start = System.nanoTime();
		for (int i = 0; i < max; i++) {
			//Thread.sleep(2000);
			final byte[] encodedData = encode(src, throwOOME, isURL, doPadding, linemax, newline);
		}
		end = System.nanoTime();
		System.out.println("Time: " + ((double) (end - start)) / 1_000_000_000 + " sekunder");
	}

	public static byte[] encode(final byte[] src, final boolean throwOOME, final boolean isURL, final boolean doPadding, final int linemax, final byte[] newline) {
		final char[] base64TranslationTable = isURL ? toBase64URL : toBase64;
		final int len = encodedOutLength(src.length, true, doPadding, linemax, newline);          // dst array size
		final byte[] dst = new byte[len];
		System.out.println("src.length: " + src.length);
		System.out.println("src.length/4: " + src.length / 4);
		System.out.println("src.length%4: " + src.length % 4);
		final int ret = encode0(src, 0, src.length, dst, base64TranslationTable, doPadding, linemax, newline);
		if (ret != dst.length) {
			return Arrays.copyOf(dst, ret);
		}
		return dst;
	}

	private static int encodedOutLength(int srclen, boolean throwOOME, boolean doPadding, int linemax, byte[] newline) {
		int len = 0;
		try {
			if (doPadding) {
				len = Math.multiplyExact(4, (Math.addExact(srclen, 2) / 3));
			} else {
				int n = srclen % 3;
				len = Math.addExact(Math.multiplyExact(4, (srclen / 3)), (n == 0 ? 0 : n + 1));
			}
			if (linemax > 0) {                             // line separators
				len = Math.addExact(len, (len - 1) / linemax * newline.length);
			}
		} catch (ArithmeticException ex) {
			if (throwOOME) {
				throw new OutOfMemoryError("Encoded size is too large");
			} else {
				// let the caller know that encoded bytes length
				// is too large
				len = -1;
			}
		}
		return len;
	}

	private static void encodeBlock(byte[] src, int sp, int sl, byte[] dst, int dp, final char[] base64TranslationTable) {
		char[] base64 = base64TranslationTable;
		for (int sp0 = sp, dp0 = dp; sp0 < sl; ) {
			int bits = (src[sp0++] & 0xff) << 16 |
					(src[sp0++] & 0xff) << 8 |
					(src[sp0++] & 0xff);
			dst[dp0++] = (byte) base64[(bits >>> 18) & 0x3f];
			dst[dp0++] = (byte) base64[(bits >>> 12) & 0x3f];
			dst[dp0++] = (byte) base64[(bits >>> 6) & 0x3f];
			dst[dp0++] = (byte) base64[bits & 0x3f];
		}
	}

	private static int encode0(byte[] src, int off, int end, byte[] dst, final char[] base64TranslationTable, boolean doPadding, int linemax, byte[] newline) {
		char[] base64 = base64TranslationTable;
		int sp = off;
		int slen = (end - off) / 3 * 3;
		int sl = off + slen;
		if (linemax > 0 && slen > linemax / 4 * 3)
			slen = linemax / 4 * 3;
		int dp = 0;
		while (sp < sl) {
			int sl0 = Math.min(sp + slen, sl);
			encodeBlock(src, sp, sl0, dst, dp, base64);
			int dlen = (sl0 - sp) / 3 * 4;
			dp += dlen;
			sp = sl0;
			if (dlen == linemax && sp < end) {
				for (byte b : newline) {
					dst[dp++] = b;
				}
			}
		}
		if (sp < end) {               // 1 or 2 leftover bytes
			int b0 = src[sp++] & 0xff;
			dst[dp++] = (byte) base64[b0 >> 2];
			if (sp == end) {
				dst[dp++] = (byte) base64[(b0 << 4) & 0x3f];
				if (doPadding) {
					dst[dp++] = '=';
					dst[dp++] = '=';
				}
			} else {
				int b1 = src[sp++] & 0xff;
				dst[dp++] = (byte) base64[(b0 << 4) & 0x3f | (b1 >> 4)];
				dst[dp++] = (byte) base64[(b1 << 2) & 0x3f];
				if (doPadding) {
					dst[dp++] = '=';
				}
			}
		}
		return dp;
	}
}
