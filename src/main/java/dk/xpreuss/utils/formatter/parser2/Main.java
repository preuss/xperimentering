package dk.xpreuss.utils.formatter.parser2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) throws IOException {
		String str = "Hello World!\r\nChristmas...!";
		Scanner s = new Scanner(str);
		System.out.println(s.peekSequence(666));
		while (!s.eof()) {
			char cur = s.next();
			if (isNewLine(cur)) {
				System.out.println(newLine(cur, s::peek));
				s.next(); // Consume next, because we have peeked.
			} else {
				System.out.println(cur);
			}
		}

		char []ww = new char[] {'a', 'b'};
		Readable reader = new BufferedReader(new StringReader(str));
		final CharBuffer buf = CharBuffer.wrap(ww) ;
		buf.limit()
		System.out.println("BUFFER: " + buf);
		System.out.println(reader.read(buf));
		//buf.rewind();
		System.out.println("Buffer: -->" + buf + "<--");
		System.out.println(reader.read(buf));
		//buf.rewind().position(1);
		buf.rewind().position(1);
		System.out.println("Buffer: -->" + buf.toString() + "<--");
	}

	public static boolean isNewLine(char currentChar) {
		return '\n' == currentChar || '\r' == currentChar;
	}

	public static NewLineType newLine(char currentChar, Supplier<Optional<Character>> peekCharFunc) {
		if('\n' == currentChar) return NewLineType.POSIX_STYLE;
		if('\r' == currentChar) {
			Optional<Character> optionalPeekChar = peekCharFunc.get();
			return optionalPeekChar
					.filter(peekChar -> '\n' == peekChar)
					.map(peekChar -> NewLineType.WIDOWS_STYLE)
					.orElse(NewLineType.MAC_STYLE);
		}
		throw new IllegalStateException();
	}
}

class Scanner {
	private final String sourceStr;

	private int indexPosition;
	private Character last;

	Scanner(String sourceStr) {
		this.sourceStr = sourceStr;
	}

	public char next() {
		if(indexPosition < sourceStr.length()) {
			return this.last = sourceStr.charAt(indexPosition++);
		} else		{
			throw new IllegalStateException();
		}
	}

	public boolean eof() {
		return !(indexPosition < sourceStr.length());
	}

	public char last() {
		if(this.last != null) {
			return this.last;
		} else {
			throw new IllegalStateException();
		}
	}

	public Optional<Character> peek() {
		if ((indexPosition) < sourceStr.length()) {
			return Optional.of(sourceStr.charAt(indexPosition));
		} else {
			return Optional.empty();
		}
	}

	public CharSequence peekSequence(int peekMaxSize) {
		final int max = Integer.min(indexPosition+peekMaxSize, sourceStr.length());
		StringBuilder stringBuilder = new StringBuilder(max-indexPosition);
		for(int i=indexPosition;i<max; i++) {
			stringBuilder.append(sourceStr.charAt(i));
		}
		return stringBuilder.toString();
	}

	public boolean isPeek(char charToCheck) {
		if ((indexPosition) < sourceStr.length()) {
			return sourceStr.charAt(indexPosition) == charToCheck;
		} else {
			return false;
		}
	}
}

enum NewLineType {
	WIDOWS_STYLE("\r\n"),
	POSIX_STYLE("\n"),
	MAC_STYLE("\r");

	private final CharSequence value;

	NewLineType(CharSequence retunValue) {
		this.value = retunValue;
	}

	public CharSequence getValue() {
		return value;
	}

	public static int biggestStyleLength() {
		return Arrays.stream(NewLineType.values())
				.map(newLineType -> newLineType.value.length())
				.reduce(Integer::max)
				.orElseThrow();
	}
}
