package dk.xpreuss.xperimentering;

import dk.xpreuss.xperimentering.basex.Base64rfc4648;
import dk.xpreuss.xperimentering.basex.IBase64;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class Main {
	public static String encode(String source) {
		final int baseSize = 64;
		final String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		final byte[] encodeData = new byte[baseSize];
		for (int i = 0; i < 64; i++) {
			encodeData[i] = (byte) charSet.charAt(i);
		}
		if (encodeData.length != baseSize) {
			throw new IllegalStateException("The BaseSize should be " + baseSize + ", but is " + encodeData.length);
		}

		final int srcSize = source.getBytes().length;

		final int dstLength = (srcSize + 2) / 3 * 4 + srcSize / 72;
//		final int dstLength = Base64rfc4648.paddedBase64(srcSize);

		System.out.println("Source size: " + srcSize);
		System.out.println("DstLength: " + dstLength);
		System.out.println("Other  (Ceiling(4n/3)): " + Math.ceil((4.0 * srcSize) / 3));
		System.out.println("Other2 (floor((3 * (length - padding)) / 4): " + Math.floor(3 * (dstLength) / 4.0));

		int length = srcSize;
		byte[] dst = new byte[dstLength];
		byte[] src = source.getBytes();
		int start = 0;

		int x = 0;
		int dstIndex = 0;
		int state = 0;  // which char in pattern
		int old = 0;  // previous byte
		int len = 0;  // length decoded so far
		int max = length + start;
		for (int srcIndex = start; srcIndex < max; srcIndex++) {
			x = src[srcIndex];
			switch (++state) {
				case 1:
					dst[dstIndex++] = encodeData[(x >> 2) & 0x3f];
					break;
				case 2:
					dst[dstIndex++] = encodeData[((old << 4) & 0x30)
							| ((x >> 4) & 0xf)];
					break;
				case 3:
					dst[dstIndex++] = encodeData[((old << 2) & 0x3C)
							| ((x >> 6) & 0x3)];
					dst[dstIndex++] = encodeData[x & 0x3F];
					state = 0;
					break;
			}
			old = x;
			if (++len >= 72) {
				dst[dstIndex++] = (byte) '\n';
				len = 0;
			}
		}
		/*
		 * now clean up the end bytes
		 */

		switch (state) {
			case 1:
				dst[dstIndex++] = encodeData[(old << 4) & 0x30];
				dst[dstIndex++] = (byte) '=';
				dst[dstIndex++] = (byte) '=';
				break;
			case 2:
				dst[dstIndex++] = encodeData[(old << 2) & 0x3c];
				dst[dstIndex++] = (byte) '=';
				break;
		}
		return new String(dst);

		//return  "YWJjZGVmZw==";
	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		final int baseSize = 64;
		String source = "abcdefghijklmnopqrstuvwxy";
source="wxy";
		byte[] sourceBytes = source.getBytes();

		String encodedString = encode(source);
		final String expectedEncodedString = "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eQ==";
		System.out.println("Source:   " + source);
		System.out.println("Target:   " + encodedString);
		System.out.println("Expected: " + expectedEncodedString);
		System.out.println("Does it work ? " + (expectedEncodedString.equals(encodedString)));

		System.out.println("Hello World");
		for (int i = 0; i < 20; i++) {
			System.out.println("n= " + i + ",  Pad : " + Base64rfc4648.calculateOutLenght(i, false, false) + ", UnPad: " + Base64rfc4648.calculateOutLenght(i, true, false));
		}

		final String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

		byte [] encoded = encodeBlock(sourceBytes, 2);
		for (int i = 0; i < encoded.length; i++) {
			encoded[i] = (byte)charSet.charAt(encoded[i]);
		}
		System.out.println(new String(encoded));
	}

	private static byte[] encodeBlock(byte [] src, int len) {
		byte [] out = new byte[4];
		out[0] =(byte) (src[0]>>2);
		out[1]= (byte) (((src[0] & 0x03)<<4) | (src[1] >> 4));
		out[2]=(byte) (len > 0 ? (((src[1]  & 0x0F)<<2) | (src[2]  >> 6)) : '=');
		out[3]=(byte) (len > 1 ? (src[2]  & 0x3F) : '=');
		return out;
	}
}
