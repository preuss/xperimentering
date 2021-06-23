package dk.xpreuss.utils.formatter.custom;

import com.ibm.icu.util.Freezable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Message implements IMessage {
	private final String messagePattern;
	private final Map namedArguments;
	private final List numberedArguments;
	private List<MessageElement> messages;

	private Message(String messagePattern, Map namedArguments, List numberedArguments, List<MessageElement> messages) {
		this.messagePattern = Objects.requireNonNull(messagePattern);
		this.namedArguments = namedArguments == null ? null : Collections.unmodifiableMap(new HashMap<>(namedArguments));
		this.numberedArguments = numberedArguments == null ? null : Collections.unmodifiableList(new ArrayList(numberedArguments));

		this.messages = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(messages)));
	}

	public static Message from(String pattern) {
		return from(pattern, null, (List) null);
	}

	public static Message from(String pattern, Object... numberedArguments) {
		return from(pattern, null, numberedArguments);
	}

	public static Message from(String pattern, List numberedArguments) {
		return from(pattern, null, numberedArguments);
	}

	public static Message from(String pattern, Map namedArguments) {
		return from(pattern, namedArguments, (List) null);
	}

	public static Message from(String pattern, Map namedArguments, Object... numberedArguments) {
		return from(pattern, namedArguments, Arrays.asList(numberedArguments));
	}

	public static Message from(String pattern, Map namedArguments, List numberedArguments) {
		List<MessageElement> parsedMessageList = parseMessagePattern(pattern);
		return new Message(pattern, namedArguments, numberedArguments, parsedMessageList);
	}

	private static List<MessageElement> parseMessagePattern(String messagePattern) {
		List<MessageElement> messageList = new LinkedList<>();
		StringBuilder currentChars = new StringBuilder();
		StringBuilder currentFormatArgument = new StringBuilder();
		StringBuilder currentFormatType = new StringBuilder();
		StringBuilder currentFormatStyle = new StringBuilder();

		boolean inQuote = false;
		int inTag = 0;
		int inTagPart = 0;
		for (int i = 0; i < messagePattern.length(); i++) {
			int currentChar = messagePattern.charAt(i);
			if (inQuote) {
				currentChars.append((char) currentChar);
				inQuote = !inQuote;
			} else {
				int[] array = {currentChar};
				String currentCharAsString = new String(array, 0, 1);
				switch (currentChar) {
					case '\'':
						inQuote = !inQuote;
						break;
					case ',':
						if (inTag > 0) {
							// Next tag part
							inTagPart++;
						}
						break;
					case '{':
						if (currentChars.length() > 0) {
							messageList.add(new MessageText(currentChars.toString()));
							currentChars.setLength(0);
						}

						++inTag;
						break;
					case '}':
						final String currentFormatArgumentStr = currentFormatArgument.toString();
						final String currentFormatTypeStr = currentFormatType.toString();
						final String currentFormatStyleStr = currentFormatStyle.toString();
						// Taste if argument is number or name
						if (currentFormatArgument == null || currentFormatArgument.length() == 0) {
							// Using MessageNamedTag to format
							// TODO: Change MessageNamedTog to include a requireValidArgumentName, to be called here.
//							System.out.println("'" + currentFormatArgument + "' -> " + i);
							new MessageNamedTag(currentFormatArgumentStr, currentFormatTypeStr, currentFormatStyleStr);
						}
						try {
							// TODO: Change MessageIndexTag to include a requireValidArgumentindex, to be called here.
							// TODO: Perhaps move to parent MessageTag ???
							int argumentIndex = Integer.parseInt(currentFormatArgumentStr.trim());
							messageList.add(new MessageNumberedTag(argumentIndex, currentFormatTypeStr, currentFormatStyleStr));
						} catch (NumberFormatException e) {
							messageList.add(new MessageNamedTag(currentFormatArgumentStr, currentFormatTypeStr, currentFormatStyleStr));
						}

						// Reset
						currentFormatArgument.setLength(0);
						currentFormatType.setLength(0);
						currentFormatStyle.setLength(0);
						--inTag;
						inTagPart = 0;

						currentChars.setLength(0);
						break;
					default:
						if (inTag == 0) {
							currentChars.append((char) currentChar);
						} else if (inTag > 0) {
							switch (inTagPart) {
								case 0:
									currentFormatArgument.append((char) currentChar);
									break;
								case 1:
									currentFormatType.append((char) currentChar);
									break;
								case 2:
									currentFormatStyle.append((char) currentChar);
									break;
								default:
									throw new IllegalArgumentException("Too many commas in the message");
							}
						}
						break;
				}
			}
		}
		if (inTag > 0) {
			// TODO: Still in a tag.
//			System.out.println("Still in inTag(" + inTag + ")!");
		} else if (inQuote) {
			// TODO: Still in quote ???
//			System.out.println("Still in Quote!");
		} else if (currentChars.length() > 0) {
			messageList.add(new MessageText(currentChars.toString()));
			currentChars.setLength(0);
		}
		return messageList;
	}

	public Message withPattern(String pattern) {
		return from(pattern, this.namedArguments, this.numberedArguments);
	}

	public Message withNamedArguments(Map namedArguments) {
		return new Message(this.messagePattern, namedArguments, this.numberedArguments, this.messages);
	}

	public Message withNumberedArguments(Object... numberedArguments) {
		return new Message(this.messagePattern, this.namedArguments, Arrays.asList(numberedArguments), this.messages);
	}

	public Message withNumberedArguments(List numberedArguments) {
		return new Message(this.messagePattern, this.namedArguments, numberedArguments, this.messages);
	}

	public String format() {
		return format(this.namedArguments, this.numberedArguments);
	}

	public String format(Map namedArguments) {
		return format(namedArguments, this.numberedArguments);
	}

	public String format(List numberedArguments) {
		return format(this.namedArguments, numberedArguments);
	}

	public String format(Map namedArguments, List numberedArguments) {
//		for (int i = 0; i < messages.size(); i++) {
//			MessageElement messageElement = messages.get(i);
//			System.out.println(i + " . MessageElement ToString: " + messageElement);
//			System.out.println(i + " . MessageElement Format:   " + messageElement.format(this.namedArguments, this.numberedArguments));
//		}
		return this.messages.stream().map(messageElement -> messageElement.format(this.namedArguments, this.numberedArguments))
				.collect(StringBuilder::new,
						(StringBuilder container, StringBuilder formattedElement) -> container.append(formattedElement),
						StringBuilder::append
				)
				.toString();
	}

	public String toMessagePattern() {
		return this.messages == null ? null : this.messages.stream().map(MessageElement::getText).reduce("", (s1, s2) -> s1 + s2);
	}

	public String getMessagePattern() {
		return this.messagePattern;
	}


	@Override
	public String getText() {
		StringBuilder builder = new StringBuilder(messages.size() * 10);
		messages.stream().forEach(message -> builder.append(message.getText()));
		return builder.toString();
	}

}
