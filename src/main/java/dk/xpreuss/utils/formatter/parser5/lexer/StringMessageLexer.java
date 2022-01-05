package dk.xpreuss.utils.formatter.parser5.lexer;

import dk.xpreuss.utils.formatter.parser3.tokentypes.ControlCharacterTokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.NewLineTokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser5.scanner.IScanner;
import dk.xpreuss.utils.formatter.parser5.scanner.StringScanner;
import dk.xpreuss.utils.formatter.parser5.tokens.IToken;
import dk.xpreuss.utils.formatter.parser5.tokens.Token;
import dk.xpreuss.utils.formatter.parser5.unicode.CodePoint;
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

		CodePoint currentCodePoint = null;
		while (this.scanner.hasNext()) {
			currentCodePoint = scanner.next();

			if (isControlCharacter(currentCodePoint)) {
				return parseControlCharacter(currentCodePoint);
			} else if (isNewLine(currentCodePoint)) {
				//scanner.peek()
			}
			throw new IllegalArgumentException();
		}

		throw new IllegalArgumentException();
	}

	private IToken parseControlCharacter(final CodePoint cp) {
		return Token.builder()
				.codePointCount(cp.getValue())
				.type(TokenType.WHITE_SPACE)
				.subType(ControlCharacterTokenSubType.translateFromCodePoint(cp.getValue()))
				.build();
	}

	private boolean isControlCharacter(final CodePoint cp) {
		return ControlCharacterTokenSubType.hasCodePoint(cp.getValue());
	}

	private static final List<Triple<Integer, String, String>> spaceCharacters =
			Collections.unmodifiableList(
					Arrays.asList(
							Triple.of(32, " ", "Space"),
							Triple.of(160, "NBSP", "Non-breaking space")
					)
			);

	private boolean isNewLine(CodePoint cp) {
		return NewLineTokenSubType.isNewLine(cp.getValue());
	}

	private IToken parseNewLine(CodePoint cp) {
		/*
		return spaceCharacters.stream().parallel().filter(whiteSpaceConst -> whiteSpaceConst.getLeft() == cp.getValue())
				.map(whiteSpaceConst -> Token.builder().type(TokenType.WHITE_SPACE).subType(NewLineTokenSubType.))
				.findAny().orElseGet(() -> null);

		 */
		return null;
	}
}
