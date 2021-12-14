package dk.xpreuss.utils.formatter.parser4;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

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
		return new Utf8(Character.toString(codePoint).getBytes(StandardCharsets.UTF_8));
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

	public static void main(String[] args) {
		String t = "Another\u2060yesæøå\uD834\uDD64\u0000";
		System.out.println(t.codePoints().count());
		t.codePoints().sequential().mapToObj(Utf8::of).forEach(System.out::println);
	}
}
