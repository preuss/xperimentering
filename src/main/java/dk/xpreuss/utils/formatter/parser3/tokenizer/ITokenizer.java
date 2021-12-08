package dk.xpreuss.utils.formatter.parser3.tokenizer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;

import java.io.Closeable;
import java.util.List;

public interface ITokenizer extends Closeable {
	int needMin();
	int needMax();
	boolean has(List<CodePoint> peekCodePoints);
	TokenizedToken toTokenType(List<CodePoint> codePoints);

	default void close() {}
}
