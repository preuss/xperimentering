package dk.xpreuss.utils.formatter.parser4;

import dk.xpreuss.utils.formatter.parser3.Token;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenSubType;
import dk.xpreuss.utils.formatter.parser3.tokentypes.TokenType;
import dk.xpreuss.utils.formatter.parser4.scanner.CodePointSequence;
import dk.xpreuss.utils.preuss.StringJoiner;

import java.util.Objects;

public interface IToken {
	TokenType getType();
	TokenSubType getSubType();
	CodePointSequence getValue();
	int getPosition();
	default int getCodePointCount() {
		return getValue().length();
	}
}
