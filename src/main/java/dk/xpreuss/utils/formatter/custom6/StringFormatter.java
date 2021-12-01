package dk.xpreuss.utils.formatter.custom6;

import java.util.LinkedList;
import java.util.List;

public class StringFormatter {
    private String rawMessage;
    private List<MessageElement> messageElementList;
    public StringFormatter(String messageToFormat) {
        this.rawMessage = messageToFormat;
        this.messageElementList = parseRawMessage(messageToFormat);
    }

    private int currentTokenIndex = -1;
    private List<MessageElement> parseRawMessage(String messageToFormat) {
        List<MessageElement> messageElementList = new LinkedList<>();
        while(nextToken()) {
	        char currentChar = rawMessage.charAt(currentTokenIndex);

        }
        messageElementList.add(new MessageElement(0));
        return messageElementList;
    }
    private boolean nextToken() {

	    if (++currentTokenIndex > rawMessage.length()) {
		    currentTokenIndex = -1;
	    }
	    return currentTokenIndex >= 0;
    }

    public String formatWith(String[] arguments) {
        StringBuffer retBuf = new StringBuffer(messageElementList.size());
        for(MessageElement messageElement : messageElementList) {
            String argument = arguments[messageElement.getNumberedIndex()];
            retBuf.append(messageElement.formatWith(argument));
        }
        return retBuf.toString();
    }
}
