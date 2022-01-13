package dk.xpreuss.utils.formatter.parser5.lexer;

import dk.xpreuss.utils.formatter.parser5.scanner.IScanner;
import dk.xpreuss.utils.formatter.parser5.scanner.StringScanner;
import dk.xpreuss.utils.formatter.parser5.tokens.*;
import dk.xpreuss.utils.formatter.parser5.unicode.CodePoint;
import dk.xpreuss.utils.formatter.parser5.unicode.CodePointSequence;
import dk.xpreuss.utils.formatter.parser5.unicode.UString;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

public class StringMessageLexer implements ILexer {
	private IScanner scanner;
	private IToken currentToken;

	public StringMessageLexer(String replaceableMessage) {
		this(new StringScanner(replaceableMessage));
	}

	public StringMessageLexer(IScanner scanner) {
		this.scanner = Objects.requireNonNull(scanner);
	}

	@Override
	public boolean hasNext() {
		return scanner.hasNext();
	}

	@Override
	public IToken next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		if (isNewLine(scanner)) {
			return parseNewLine(scanner);
		}
		if (isControlCharacter(scanner)) {
			return parseControlCharacter(scanner);
		}
		if (isText(scanner)) {
			return paseText(scanner);
		}
		if (isWhiteSpace(scanner)) {
			return parseWhiteSpace(scanner);
		}
		throw new IllegalArgumentException();
	}

	private boolean isWhiteSpace(IScanner scanner) {
		return scanner.peek().map(codePoint -> Character.isWhitespace(codePoint.getValue())).orElse(false);
	}

	private IToken parseWhiteSpace(IScanner scanner) {
		CodePoint next = scanner.next();
		return Token.builder()
				.type(TokenType.WHITE_SPACE)
				.value(new UString(next.toString()))
				.position(scanner.getPosition())
				.build();
	}

	private boolean isText(IScanner scanner) {
		return scanner.peek().map(codePoint -> Character.isAlphabetic(codePoint.getValue())).orElse(false);
	}

	private IToken paseText(IScanner scanner) {
		CodePoint next = scanner.next();
		return Token.builder()
				.type(TokenType.FREE_TEXT)
				.value(new UString(next.toString()))
				.position(scanner.getPosition())
				.build();
	}

	private boolean isControlCharacter(IScanner scanner) {
		return scanner.peek().map(codePoint -> ControlCharacterTokenSubType.hasCodePoint(codePoint.getValue())).orElseGet(() -> false);
	}

	private IToken parseControlCharacter(IScanner scanner) {
		CodePoint codePoint = scanner.next();
		return Token.builder()
				.codePointCount(1)
				.type(TokenType.WHITE_SPACE)
				.subType(ControlCharacterTokenSubType.translateFromCodePoint(codePoint.getValue()))
				.value(new UString(codePoint.toString()))
				.position(scanner.getPosition())
				.build();
	}

	private static final List<Triple<Integer, String, String>> spaceCharacters =
			Collections.unmodifiableList(
					Arrays.asList(
							Triple.of(32, " ", "Space"),
							Triple.of(160, "NBSP", "Non-breaking space")
					)
			);

	private boolean isNewLine(IScanner scanner) {
		return NewLineTokenSubType.isNewLine(scanner.peekCount(NewLineTokenSubType.biggestStyleLength()));
	}

	private IToken parseNewLine(IScanner scanner) {
		CodePointSequence peekSequenze = scanner.peekCount(NewLineTokenSubType.biggestStyleLength());
		if (peekSequenze.length() > 0) {
			List<NewLineTokenSubType> types =
					Arrays.stream(NewLineTokenSubType.values()).sorted(Comparator.comparingInt(o -> o.getValue().length())).toList();
			NewLineTokenSubType parsedType = NewLineTokenSubType.parseNewLine(peekSequenze);


			// We have only used peek, now we remove the real used length.
			CodePointSequence nextSequenze = scanner.nextCount(parsedType.getValue().length());

			return Token.builder()
					.type(TokenType.NEW_LINE)
					.subType(parsedType)
					.value(nextSequenze)
					.position(scanner.getPosition())
					.codePointCount(nextSequenze.length())
					.build();
		}
		return null;
	}
}
