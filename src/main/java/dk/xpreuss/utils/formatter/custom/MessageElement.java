package dk.xpreuss.utils.formatter.custom;

import java.util.List;
import java.util.Map;

public interface MessageElement extends IMessage {
	StringBuilder format(Map namedArguments, List numberedArguments);
	String getText();
}
