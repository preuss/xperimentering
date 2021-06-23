package dk.xpreuss.utils.formatter.custom;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MessageText implements MessageElement {
	private String text;

	public MessageText(String text) {
		this.text = Objects.requireNonNull(text);
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "MessageText{" +
				"text=" + text +
				'}';
	}

	@Override
	public StringBuilder format(Map namedArguments, List numberedArguments) {
		return new StringBuilder(text);
	}
}
