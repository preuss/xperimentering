package dk.xpreuss.utils.formatter.parser4;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Utf8 implements Comparable<Utf8> {
	private final byte[] charValue;

	private Utf8(byte[] charValue) {
		this.charValue = charValue;
	}

	public static Utf8 of(Utf8 utf8) {
		return new Utf8(utf8.charValue);
	}

	public static Utf8 of(char character) {
		//return new Utf8(StringUtils.getBytes(Character.toString(character), StandardCharsets.UTF_8));
		return new Utf8(String.valueOf(character).getBytes(StandardCharsets.UTF_8));
	}

	public static Utf8 of(int codePoint) {
//		return new Utf8(Character.toString(codePoint).getBytes(StandardCharsets.UTF_8));
//		return new Utf8(codePointToUtf8(codePoint));
		return new Utf8(codePointToUtf8_4byte(codePoint));
//		return new Utf8(codePointToUtf8_6byte(codePoint));
	}

	public byte[] getByteValue() {
		return Arrays.copyOf(charValue, charValue.length);
	}

	public char asChar() {
		return asString().charAt(0);
	}

	public int asCodePoint() {
		return asString().codePointAt(0);
	}

	public String getName() {
		return Character.getName(asCodePoint());
	}

	public String asString() {
		return new String(getByteValue(), StandardCharsets.UTF_8);
	}

	public byte getByteSize() {
		return (byte) charValue.length;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Utf8.class.getSimpleName() + "[", "]")
				.add("charValue=" + Arrays.toString(charValue))
				.add("CodePointName: " + Character.getName(asCodePoint()))
				.toString();
	}

	@Override
	public int compareTo(Utf8 other) {
		if (this == Objects.requireNonNull(other)) {
			return 0;
		}
		return Arrays.compare(this.charValue, other.charValue);
	}

	private static byte[] codePointToUtf8_6byte(int codePoint) {
		if (codePoint < 0x80) {
			/* one byte */
			final byte[] buffer1 = new byte[1];
			buffer1[0] = (byte) codePoint;
			return buffer1;
		} else if (codePoint < 0x800) {
			/* two bytex */
			final byte[] buffer2 = new byte[2];
			buffer2[0] = (byte) (0xC0 | ((codePoint >>> 6) & 0x1F));
			buffer2[1] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer2;
		} else if (codePoint < 0x10000) {
			/* three bytes */
			final byte[] buffer3 = new byte[3];
			buffer3[0] = (byte) (0xE0 | ((codePoint >>> 12) & 0x0F));
			buffer3[1] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer3[2] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer3;
		} else if (codePoint < 0x200000) {
			/* four bytes */
			final byte[] buffer4 = new byte[4];
			buffer4[0] = (byte) (0xF0 | ((codePoint >>> 18) & 0x07));
			buffer4[1] = (byte) (0x80 | ((codePoint >>> 12) & 0x3F));
			buffer4[2] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer4[3] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer4;
		} else if (codePoint < 0x4000000) {
			/* five bytes */
			final byte[] buffer5 = new byte[5];
			buffer5[0] = (byte) (0xF8 | ((codePoint >>> 24) & 0x03));
			buffer5[1] = (byte) (0x80 | ((codePoint >>> 18) & 0x3F));
			buffer5[2] = (byte) (0x80 | ((codePoint >>> 12) & 0x3F));
			buffer5[3] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer5[4] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer5;
		} else /* if (codePoint <= 0x7FFFFFFF) */ {
			/* six bytes */
			final byte[] buffer6 = new byte[6];
			buffer6[0] = (byte) (0xFC | ((codePoint >>> 30) & 0x01));
			buffer6[1] = (byte) (0x80 | ((codePoint >>> 24) & 0x3F));
			buffer6[2] = (byte) (0x80 | ((codePoint >>> 18) & 0x3F));
			buffer6[3] = (byte) (0x80 | ((codePoint >>> 12) & 0x3F));
			buffer6[4] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer6[5] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer6;
		}
	}

	/**
	 * UTF-8 RFC3629
	 * @param codePoint 32 bit utf code point
	 * @return encoded byte array of UTF-8
	 */
	private static byte[] codePointToUtf8_4byte(int codePoint) {
		if (codePoint < 0 || codePoint > 0x10FFFF) {
			// Or use exception ?
			throw new IllegalArgumentException("Not a valid Unicode code point: 0x" + Integer.toHexString(codePoint).toUpperCase());
			/* */

			// error - use replacement character
			/*
			final byte[] buffer3 = new byte[3];
			buffer3[0] = (byte) 0xEF;
			buffer3[1] = (byte) 0xBF;
			buffer3[2] = (byte) 0xBD;
			return buffer3;
			 */
		} else if (codePoint < 0x80) {
			/* one byte */
			// Plain ASCII
			final byte[] buffer1 = new byte[1];
			buffer1[0] = (byte) codePoint;
			return buffer1;
		} else if (codePoint < 0x800) {
			/* two bytex */
			final byte[] buffer2 = new byte[2];
			buffer2[0] = (byte) (0xC0 | ((codePoint >>> 6) & 0x1F));
			buffer2[1] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer2;
		} else if (codePoint < 0x10000) {
			/* three bytes */
			final byte[] buffer3 = new byte[3];
			buffer3[0] = (byte) (0xE0 | ((codePoint >>> 12) & 0x0F));
			buffer3[1] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer3[2] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer3;
		} else /* if (codePoint < 0x110000) */ {
			/* four bytes */
			final byte[] buffer4 = new byte[4];
			buffer4[0] = (byte) (0xF0 | ((codePoint >>> 18) & 0x07));
			buffer4[1] = (byte) (0x80 | ((codePoint >>> 12) & 0x3F));
			buffer4[2] = (byte) (0x80 | ((codePoint >>> 6) & 0x3F));
			buffer4[3] = (byte) (0x80 | (codePoint & 0x3F));
			return buffer4;
		}
	}

	private static byte[] codePointToUtf8(int codePoint) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] cpBytes = new byte[6]; // IndexOutOfBounds for too large code points
		if (codePoint < 0) {
			throw new IllegalStateException("No negative code point allowed");
		} else if (codePoint < 0x80) {
			baos.write(codePoint);
		} else {
			int bi = 0;
			int lastPrefix = 0xC0;
			int lastMask = 0x1F;
			for (; ; ) {
				int b = 0x80 | (codePoint & 0x3F);
				cpBytes[bi] = (byte) b;
				++bi;
				codePoint >>= 6;
				if ((codePoint & ~lastMask) == 0) {
					cpBytes[bi] = (byte) (lastPrefix | codePoint);
					++bi;
					break;
				}
				lastPrefix = 0x80 | (lastPrefix >> 1);
				lastMask >>= 1;
			}
			while (bi > 0) {
				--bi;
				baos.write(cpBytes[bi]);
			}
		}
		return baos.toByteArray();
	}

	private static byte[] codePointStreamToUtf8bytes(IntStream codePoints) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] cpBytes = new byte[6]; // IndexOutOfBounds for too large code points
		codePoints.forEach((cp) -> {
			if (cp < 0) {
				throw new IllegalStateException("No negative code point allowed");
			} else if (cp < 0x80) {
				baos.write(cp);
			} else {
				int bi = 0;
				int lastPrefix = 0xC0;
				int lastMask = 0x1F;
				for (; ; ) {
					int b = 0x80 | (cp & 0x3F);
					cpBytes[bi] = (byte) b;
					++bi;
					cp >>= 6;
					if ((cp & ~lastMask) == 0) {
						cpBytes[bi] = (byte) (lastPrefix | cp);
						++bi;
						break;
					}
					lastPrefix = 0x80 | (lastPrefix >> 1);
					lastMask >>= 1;
				}
				while (bi > 0) {
					--bi;
					baos.write(cpBytes[bi]);
				}
			}
		});
		return baos.toByteArray();
	}

	public static void main(String[] args) {
		final String t = "Another\u2060yesæøå\uD834\uDD64\u0000";
		System.out.println(t.codePoints().count());

		encodeTestTime(t, 10);
		encodeTestTime(t, 10);
		encodeTestTime(t, 10);
		int MAX = 10_000_000;
		encodeTestTime(t, MAX);

/*
		System.out.println(Utf8.of(-1));
		System.out.println(codePointToUtf8_4byte(-2));
		System.out.println("-1 is: " + Integer.toHexString(-1));
		System.out.println(0x7FFFFFFF);
 */
	}

	public static void encodeTestTime(final String text, final long MAX_COUNT) {
		long start = System.nanoTime();
		for (int i = 0; i < MAX_COUNT; i++) {
			text.codePoints().sequential().mapToObj(Utf8::of).forEach(utf8 -> {
			});
		}
		long end = System.nanoTime();
		String timeFmt = DurationFormatUtils.formatDuration((end - start) / 1000000, "**HH:mm:ss.S**", true);
		System.out.println("Time: " + (end - start));
		System.out.println(timeFmt);
	}

}
