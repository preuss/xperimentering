package dk.xpreuss.utils.formatter.parser4;

import java.util.Iterator;

public interface ILexer extends IEnumeratorable<IToken>, Iterable<IToken> {
	@Override
	default Iterator<IToken> iterator() {
		return new Iterator<IToken>() {
			@Override
			public boolean hasNext() {
				return this.hasNext();
			}

			@Override
			public IToken next() {
				return this.next();
			}
		};
	}
}
