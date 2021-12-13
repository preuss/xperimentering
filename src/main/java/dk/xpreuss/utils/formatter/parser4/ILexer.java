package dk.xpreuss.utils.formatter.parser4;

import java.util.Iterator;
import java.util.Optional;

public interface ILexer extends IEnumeratorable<IToken>, Iterable<IToken> {
	@Override
	default Iterator<IToken> iterator() {
		return Optional.of(this).map(outer -> new Iterator<IToken>() {
			@Override
			public boolean hasNext() {
				return outer.hasNext();
			}

			@Override
			public IToken next() {
				return outer.next();
			}
		}).get();
	}
}
