package dk.xpreuss.xperimentering.basex.speedc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.IntStream;

public class SpeedCDecoder {
	private static final byte[] BASE64_INDEX = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 63, 62, 62, 63, 52, 53, 54, 55,
			56, 57, 58, 59, 60, 61, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6,
			7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0,
			0, 0, 0, 63, 0, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

	public static void main(String[] args) {
//		final String encodedText = "aGVsbG8="; // hello
//		final String encodedText = "aGVsbG/m+OW9pySA"; // helloæøå½§$€
		final String encodedText = "aGVsbG/DpsO4w6XCvcKnJOKCrA=="; // helloæøå½§$€

		String output = decode(encodedText.getBytes());
		for (String s : Charset.availableCharsets().keySet()) {
			System.out.println("Key: " + s);
		}
		System.out.println("=>" + output + "<= MY decoding");
		System.out.println("->" + new String(Base64.getDecoder().decode(encodedText.getBytes())) + "<- JAVA's own");

	}

	/**
	 * Base64 Decoder
	 * Base64 encoding/decoding (RFC1341)
	 * Copyright (c) 2021, Jesper Preuss jesperpreuss@yahoo.dk
	 *
	 * @param src encoded Data to be decoded in bytes
	 * @return the decoded data to bytes, you have to convert to string yourself.
	 */
	public static String decode(final byte[] src) {
		final int srcLength = src.length;
		final boolean pad = srcLength > 0 && ((srcLength % 4) > 0 || src[srcLength - 1] == '=');
		final int outLength = ((srcLength + 3) / 4 - (pad ? 1 : 0)) * 4;
		final int strLength = outLength / 4 * 3 + (pad ? 1 : 0);
		final int[] out = new int[strLength + 1];

		for (int i = 0, j = 0; i < outLength; i += 4) {
			int n = BASE64_INDEX[src[i]] << 18 | BASE64_INDEX[src[i + 1]] << 12 | BASE64_INDEX[src[i + 2]] << 6 | BASE64_INDEX[src[i + 3]];
			out[j++] = n >> 16;
			out[j++] = n >> 8 & 0b1111_1111;
			out[j++] = n & 0b1111_1111;
		}
		if (pad) {
			int n = BASE64_INDEX[src[outLength]] << 18 | BASE64_INDEX[src[outLength + 1]] << 12;
			out[strLength - 1] = n >> 16;

			if (srcLength > (outLength + 2) && src[outLength + 2] != '=') {
				n |= BASE64_INDEX[src[outLength + 2]] << 6;
				out[strLength] = n >> 8 & 0b1111_1111;
			}

		}

		byte [] newOut = new byte[out.length-1];
		for(int i =0;i<newOut.length;i++) {
			newOut[i] = (byte) out[i];
		}

		return new String(newOut);
	}

}
