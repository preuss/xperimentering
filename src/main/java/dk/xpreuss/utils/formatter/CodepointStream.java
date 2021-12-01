package dk.xpreuss.utils.formatter;

import java.io.*;

public class CodepointStream extends Reader implements Closeable, AutoCloseable {
	private Reader reader;

	public CodepointStream(Reader reader) {
		this.reader = reader;
	}

	@Override
	public int read() throws IOException {
		synchronized (lock) {
			int unit0 = reader.read();
			if (unit0 < 0)
				return unit0; // EOF

			if (!Character.isHighSurrogate((char) unit0))
				return unit0;

			int unit1 = reader.read();
			if (unit1 < 0) {
				return unit1; // EOF
			}

			if (!Character.isLowSurrogate((char) unit1)) {
				throw new UTFDataFormatException("Invalid surrogate pair");
			}

			return Character.toCodePoint((char) unit0, (char) unit1);
		}
	}

	public String readAsString() throws IOException {
		return Character.toString(read());
	}

	@Override
	public boolean ready() throws IOException {
		synchronized (lock) {
			return reader.ready();
		}
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		throw new UnsupportedOperationException("Non unicode codepoints not supported");
	}

	@Override
	public void close() throws IOException {
		if (reader != null) {
			reader.close();
			reader = null;
		}
	}

	public static String whatUnicode(int codePoint) {
		String format = String.format("Code %x is `%s', %s.",
				codePoint,
				Character.getName(codePoint),
				new String(Character.toChars(codePoint)));
		return format;
	}

	public static void main(String[] args) throws IOException {
		try (CodepointStream cs = new CodepointStream(new StringReader("Hello world æøå. keyboard ⌨. pizza \uD83C\uDF55."))) {
			System.out.println("Helo");
			int codePoint = cs.read();
			while (codePoint != -1) {
				System.out.println(CodepointStream.whatUnicode(codePoint));

				codePoint = cs.read();
			}
		}
	}
}