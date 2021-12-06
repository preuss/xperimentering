package dk.xpreuss.utils.formatter.parser3;

public interface ILexer {
	Token readNext();
	Token peekNext();
}
