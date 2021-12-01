package dk.xpreuss.utils.formatter.custom6;

import static dk.xpreuss.utils.formatter.custom6.StringMessageState.TagEnd;
import static dk.xpreuss.utils.formatter.custom6.StringMessageState.TagStart;

public class StringMessageParser {
    public MessageElement parseElement(String message) {
        MessageElement element = null;

        StringMessageState currentState = StringMessageState.Text; // Start state

        for (char singleChar : message.toCharArray()) {
            currentState = calculateNextState(currentState, singleChar);
            System.out.println("Single Char: " + singleChar);
            switch (singleChar) {
                case '{':
                break;
            }
        }
        return element;
    }

    public StringMessageState calculateNextState(final StringMessageState currentState, final int currentChar) {
        StringMessageState nextState = null;
        switch (currentState) {
            case Text:
                switch (currentChar) {
                    case '{':
                        nextState = TagStart;
                        break;
                    case '}':
                        nextState = TagEnd;
                        break;
                }
                break;
        }
        return nextState;
    }
}

enum StringMessageState {
    Text, TextQuote, TagStart, TagIndexNamed, TagIndexNumbered, TagFormatType, TagFormatStyle, TagEnd;
}
