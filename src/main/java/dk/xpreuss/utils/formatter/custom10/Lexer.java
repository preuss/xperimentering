package dk.xpreuss.utils.formatter.custom10;

import dk.xpreuss.utils.formatter.StringCodepointsIterable;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Lexer {
	private IntStream codePointStream;

	public Lexer(IntStream codePointStream) {
		this.codePointStream = codePointStream;
	}

	public Lexer(String string) {
		this.codePointStream = string.codePoints();
	}

	public Lexer(char [] chars) {
		this.codePointStream = new String(chars).codePoints();
	}

	public Lexer(InputStreamReader reader) {

	}

	public static void main(String[] args) {
		String v = "\uD845\uDC90 Hello world æøå \uD845\uDC90.";

		System.out.println(new String(v.getBytes(StandardCharsets.US_ASCII)));
	}
}
