package dk.xpreuss.utils.formatter;

import java.util.Iterator;

public class StringCodepointsIterable implements Iterable<String> {
	public class StringCodepointsIterator implements Iterator<String> {
		private int index = 0;

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return index < StringCodepointsIterable.this.string.length();
		}

		@Override
		public String next() {
			int codePoint = StringCodepointsIterable.this.string.codePointAt(index);
			index += Character.charCount(codePoint);
			return new String(Character.toChars(codePoint));
		}
	}

	private final String string;

	public StringCodepointsIterable(final String string) {
		this.string = string;
	}

	@Override
	public Iterator<String> iterator() {
		return new StringCodepointsIterator();
	}
}

