package dk.xpreuss.utils.formatter;

import com.ibm.icu.text.UCharacterIterator;

import java.text.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {paramIndex[,alignment][:formatString]}
 * {paramName[,alignment][:formatString]}
 * <p>
 * paramIndex = a number (#)
 * paramName = and alphanumeric
 * <p>
 * Only one type in a string, a param index number or named parameters.
 */
public class StringFormatter2 {
	private String pattern;


	public StringFormatter2(String pattern) {
		this.pattern = pattern;
	}

	public static void main(String[] args) {
		StringFormatter2 formatter = new StringFormatter2("Hello World{0} space after.");
		String output = formatter.parsePattern();

		Object[] arguments = new Object[]{", XXanotherXX "};
		StringBuffer result = new StringBuffer();
		FieldPosition pos = new FieldPosition(0);
		List<AttributedCharacterIterator> characterIterators = null;
		//StringBuffer buffer = formatter.subformat(arguments, result, pos, characterIterators);

		//String output ="";
		System.out.println("Output: '" + output + "'");
		//System.out.println("Buffer: " + buffer);

		System.out.printf("Hello World %f%%", 42.12345678901d);
		System.out.println();
		System.out.printf("Hello %s!%n", "World", 44);
		System.out.println();
		System.out.println("_");
		String rawText = "B:>>{1,,}<<_A_>>{0}<<_B>>{1,choice, 1<hello|5<'{3,choice,1<more than one|4<more than four}'}<<";
		String format = MessageFormat.format(rawText,
				"MyArgument", 6, "number", 0);
		System.out.println("Format: " + format);

	}
/*
	private StringBuffer subformat(Object[] arguments, StringBuffer result,
	                               FieldPosition fp, List<AttributedCharacterIterator> characterIterators) {
		// note: this implementation assumes a fast substring & index.
		// if this is not true, would be better to append chars one by one.
		int lastOffset = 0;
		int last = result.length();
		for (int i = 0; i <= maxOffset; ++i) {
			result.append(pattern, lastOffset, offsets[i]);
			lastOffset = offsets[i];
			int argumentNumber = argumentNumbers[i];
			if (arguments == null || argumentNumber >= arguments.length) {
				result.append('{').append(argumentNumber).append('}');
				continue;
			}
			// int argRecursion = ((recursionProtection >> (argumentNumber*2)) & 0x3);
			if (false) { // if (argRecursion == 3){
				// prevent loop!!!
				result.append('\uFFFD');
			} else {
				Object obj = arguments[argumentNumber];
				String arg = null;
				Format subFormatter = null;
				if (obj == null) {
					arg = "null";
				} else if (formats[i] != null) {
					subFormatter = formats[i];
					if (subFormatter instanceof ChoiceFormat) {
						arg = formats[i].format(obj);
						if (arg.indexOf('{') >= 0) {
							subFormatter = new MessageFormat(arg, locale);
							obj = arguments;
							arg = null;
						}
					}
				} else if (obj instanceof Number) {
					// format number if can
					subFormatter = NumberFormat.getInstance(locale);
				} else if (obj instanceof Date) {
					// format a Date if can
					subFormatter = DateFormat.getDateTimeInstance(
							DateFormat.SHORT, DateFormat.SHORT, locale);//fix
				} else if (obj instanceof String) {
					arg = (String) obj;

				} else {
					arg = obj.toString();
					if (arg == null) arg = "null";
				}

				// At this point we are in two states, either subFormatter
				// is non-null indicating we should format obj using it,
				// or arg is non-null and we should use it as the value.

				if (characterIterators != null) {
					// If characterIterators is non-null, it indicates we need
					// to get the CharacterIterator from the child formatter.
					if (last != result.length()) {
						characterIterators.add(
								createAttributedCharacterIterator(result.substring
										(last)));
						last = result.length();
					}
					if (subFormatter != null) {
						AttributedCharacterIterator subIterator =
								subFormatter.formatToCharacterIterator(obj);

						append(result, subIterator);
						if (last != result.length()) {
							characterIterators.add(
									createAttributedCharacterIterator(
											subIterator, MessageFormat.Field.ARGUMENT,
											Integer.valueOf(argumentNumber)));
							last = result.length();
						}
						arg = null;
					}
					if (arg != null && !arg.isEmpty()) {
						result.append(arg);
						characterIterators.add(
								createAttributedCharacterIterator(
										arg, MessageFormat.Field.ARGUMENT,
										Integer.valueOf(argumentNumber)));
						last = result.length();
					}
				} else {
					if (subFormatter != null) {
						arg = subFormatter.format(obj);
					}
					last = result.length();
					result.append(arg);
					if (i == 0 && fp != null && MessageFormat.Field.ARGUMENT.equals(
							fp.getFieldAttribute())) {
						fp.setBeginIndex(last);
						fp.setEndIndex(result.length());
					}
					last = result.length();
				}
			}
		}
		result.append(pattern, lastOffset, pattern.length());
		if (characterIterators != null && last != result.length()) {
			characterIterators.add(createAttributedCharacterIterator(
					result.substring(last)));
		}
		return result;
	}
*/

	int maxOffset = -1;

	private String parsePattern() {
		StringBuilder segments[] = new StringBuilder[SegmentState.values().length];

		segments[SegmentState.RAW.ordinal()] = new StringBuilder();

		SegmentState part = SegmentState.RAW;
		int formatNumber = 0;
		boolean inQuote = false;
		int braceStack = 0;

		maxOffset = -1;

		for (int i = 0; i < pattern.length(); ++i) {
			char ch = pattern.charAt(i);
			SegmentState state = SegmentState.RAW;
			if (SegmentState.RAW == part) {
				if (ch == '\'') {
					if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '\'') {
						segments[part.ordinal()].append(ch);  // handle doubles
						++i;
					} else {
						inQuote = !inQuote;
					}
				} else if (ch == '{' && !inQuote) {
					part = SegmentState.INDEX;
					if (segments[SegmentState.INDEX.ordinal()] == null) {
						segments[SegmentState.INDEX.ordinal()] = new StringBuilder();
					}
				} else {
					segments[part.ordinal()].append(ch);
				}
			} else {
				if (inQuote) {
					segments[part.ordinal()].append(ch);
					if (ch == '\'') {
						inQuote = false;
					}
				} else {
					switch (ch) {
						case ',':
							if (part.ordinal() < SegmentState.MODIFIER.ordinal()) {
								SegmentState nextSegmentState = SegmentState.values()[part.ordinal() + 1];
								part = nextSegmentState;
								if (segments[part.ordinal()] == null) {
									segments[part.ordinal()] = new StringBuilder();
								}
							} else {
								segments[part.ordinal()].append(ch);
							}
							break;
						case '{':
							++braceStack;
							segments[part.ordinal()].append(ch);
							break;
						case '}':
							if (braceStack == 0) {
								part = SegmentState.RAW;
								makeFormat(i, formatNumber, segments);
								formatNumber++;
								// throw away other segments
								segments[SegmentState.INDEX.ordinal()] = null;
								segments[SegmentState.TYPE.ordinal()] = null;
								segments[SegmentState.MODIFIER.ordinal()] = null;
							} else {
								--braceStack;
								segments[part.ordinal()].append(ch);
							}
							break;
						case ' ':
							// Skip any leading space chars for SegmentState.TYPE.
							if (part != SegmentState.TYPE || segments[SegmentState.TYPE.ordinal()].length() > 0) {
								segments[part.ordinal()].append(ch);
							}
							break;
						case '\'':
							inQuote = true;
							// fall through, so we keep quotes in other parts
						default:
							segments[part.ordinal()].append(ch);
							break;
					}
				}
			}
		}
		if (braceStack == 0 && part != SegmentState.RAW) {
			maxOffset = -1;
			throw new IllegalArgumentException("Unmatched braces in the pattern.");
		}
		for (int i = 0; i < segments.length; i++) {
			StringBuilder segment = segments[i];
			System.out.println("Segment[" + i + "] == " + ((segment != null) ? segment.toString() : null));
		}
		return segments[0].toString();
	}

	private Locale locale;
	private static final int INITIAL_FORMATS = 10;
	private Format[] formats = new Format[INITIAL_FORMATS];
	private int[] offsets = new int[INITIAL_FORMATS];
	private int[] argumentNumbers = new int[INITIAL_FORMATS];

	private void makeFormat(int position, int offsetNumber, StringBuilder[] textSegments) {
		String[] segments = new String[textSegments.length];
		for (int i = 0; i < textSegments.length; i++) {
			StringBuilder oneseg = textSegments[i];
			segments[i] = (oneseg != null) ? oneseg.toString() : "";
		}

		// get the argument number
		int argumentNumber;
		try {
			argumentNumber = Integer.parseInt(segments[SegmentState.INDEX.ordinal()]); // always unlocalized!
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("can't parse argument number: "
					+ segments[SegmentState.INDEX.ordinal()], e);
		}
		if (argumentNumber < 0) {
			throw new IllegalArgumentException("negative argument number: "
					+ argumentNumber);
		}

		// resize format information arrays if necessary
		if (offsetNumber >= formats.length) {
			int newLength = formats.length * 2;
			Format[] newFormats = new Format[newLength];
			int[] newOffsets = new int[newLength];
			int[] newArgumentNumbers = new int[newLength];
			System.arraycopy(formats, 0, newFormats, 0, maxOffset + 1);
			System.arraycopy(offsets, 0, newOffsets, 0, maxOffset + 1);
			System.arraycopy(argumentNumbers, 0, newArgumentNumbers, 0, maxOffset + 1);
			formats = newFormats;
			offsets = newOffsets;
			argumentNumbers = newArgumentNumbers;
		}
		int oldMaxOffset = maxOffset;
		maxOffset = offsetNumber;
		offsets[offsetNumber] = segments[SegmentState.RAW.ordinal()].length();
		argumentNumbers[offsetNumber] = argumentNumber;

		// now get the format
		Format newFormat = null;
		if (!segments[SegmentState.TYPE.ordinal()].isEmpty()) {
			KeywordType type = findKeywordType(segments[SegmentState.TYPE.ordinal()], KeywordType.values());
			switch (type) {
				case NULL:
					// Type "" is allowed. e.g., "{0,}", "{0,,}", and "{0,,#}"
					// are treated as "{0}".
					break;

				case NUMBER:
					switch (findKeywordModifier(segments[SegmentState.MODIFIER.ordinal()], KeywordModifier.values())) {
						case DEFAULT:
							newFormat = NumberFormat.getInstance(locale);
							break;
						case CURRENCY:
							newFormat = NumberFormat.getCurrencyInstance(locale);
							break;
						case PERCENT:
							newFormat = NumberFormat.getPercentInstance(locale);
							break;
						case INTEGER:
							newFormat = NumberFormat.getIntegerInstance(locale);
							break;
						default: // DecimalFormat pattern
							try {
								newFormat = new DecimalFormat(segments[SegmentState.MODIFIER.ordinal()],
										DecimalFormatSymbols.getInstance(locale));
							} catch (IllegalArgumentException e) {
								maxOffset = oldMaxOffset;
								throw e;
							}
							break;
					}
					break;

				case DATE:
				case TIME:
					DateTimeModifier mod = findDateTimeModifier(segments[SegmentState.MODIFIER.ordinal()], DateTimeModifier.values());
					if (mod != null) {
						int dateTimeModifierTranslated = DateTimeModifier.DEFAULT == mod ? DateTimeModifier.MEDIUM.ordinal() : mod.ordinal();
						if (type == KeywordType.DATE) {
							newFormat = DateFormat.getDateInstance(dateTimeModifierTranslated, locale);
						} else {
							newFormat = DateFormat.getTimeInstance(dateTimeModifierTranslated, locale);
						}
					} else {
						// SimpleDateFormat pattern
						try {
							newFormat = new SimpleDateFormat(segments[SegmentState.MODIFIER.ordinal()], locale);
						} catch (IllegalArgumentException e) {
							maxOffset = oldMaxOffset;
							throw e;
						}
					}
					break;

				case CHOICE:
					try {
						// ChoiceFormat pattern
						newFormat = new ChoiceFormat(segments[SegmentState.MODIFIER.ordinal()]);
					} catch (Exception e) {
						maxOffset = oldMaxOffset;
						throw new IllegalArgumentException("Choice Pattern incorrect: " + segments[SegmentState.MODIFIER.ordinal()], e);
					}
					break;

				default:
					maxOffset = oldMaxOffset;
					throw new IllegalArgumentException("unknown format type: " + segments[SegmentState.TYPE.ordinal()]);
			}
		}
		formats[offsetNumber] = newFormat;
	}

	private static final KeywordType findKeywordType(String s, final KeywordType[] list) {
		for (int i = 0; i < list.length; ++i) {
			if (s.equals(list[i]))
				return KeywordType.values()[i];
		}

		// Try trimmed lowercase.
		String ls = s.trim().toLowerCase(Locale.ROOT);
		if (ls != s) {
			for (int i = 0; i < list.length; ++i) {
				if (ls.equals(list[i]))
					return KeywordType.values()[i];
			}
		}
		return null;
	}

	private static final KeywordModifier findKeywordModifier(String s, final KeywordModifier[] list) {
		for (int i = 0; i < list.length; ++i) {
			if (s.equals(list[i]))
				return KeywordModifier.values()[i];
		}

		// Try trimmed lowercase.
		String ls = s.trim().toLowerCase(Locale.ROOT);
		if (ls != s) {
			for (int i = 0; i < list.length; ++i) {
				if (ls.equals(list[i]))
					return KeywordModifier.values()[i];
			}
		}
		return null;
	}

	private static final DateTimeModifier findDateTimeModifier(String s, final DateTimeModifier[] list) {
		for (int i = 0; i < list.length; ++i) {
			if (s.equals(list[i]))
				return DateTimeModifier.values()[i];
		}

		// Try trimmed lowercase.
		String ls = s.trim().toLowerCase(Locale.ROOT);
		if (ls != s) {
			for (int i = 0; i < list.length; ++i) {
				if (ls.equals(list[i]))
					return DateTimeModifier.values()[i];
			}
		}
		return null;
	}

	public enum SegmentState {
		RAW, INDEX, TYPE, MODIFIER
	}

	public enum KeywordType {
		NULL, NUMBER, DATE, TIME, CHOICE, CUSTOM
	}

	public enum KeywordModifier {
		DEFAULT, CURRENCY, PERCENT, INTEGER
	}

	public enum DateTimeModifier {
		FULL, LONG, MEDIUM, SHORT, DEFAULT
	}

	private StringFormatterX.Style style = StringFormatterX.Styles.DEFAULT;

	public static interface Style {
		public char getStartCharacter();
		public char getOpenBracket();
		public char getCloseBracket();
		public char getEscapeCharacter();
	}

	public static enum Styles implements StringFormatterX.Style {
		/**
		 * The default style (<code>#{var} `#{escaped}</code>)
		 */
		DEFAULT('#', '{', '}', '`'),

		/**
		 * Dollars style (<code>${var} `${escaped}</code>)
		 */
		DOLLARS('$', '{', '}', '`');

		private final char START_CHARACTER;
		private final char OPEN_BRACKET;
		private final char CLOSE_BRACKET;
		private final char ESCAPE_CHARACTER;

		private Styles(char START_CHARACTER, char OPEN_BRACKET, char CLOSE_BRACKET, char ESCAPE_CHARACTER) {
			this.START_CHARACTER = START_CHARACTER;
			this.OPEN_BRACKET = OPEN_BRACKET;
			this.CLOSE_BRACKET = CLOSE_BRACKET;
			this.ESCAPE_CHARACTER = ESCAPE_CHARACTER;
		}

		public char getStartCharacter() {
			return START_CHARACTER;
		}

		public char getOpenBracket() {
			return OPEN_BRACKET;
		}

		public char getCloseBracket() {
			return CLOSE_BRACKET;
		}

		public char getEscapeCharacter() {
			return ESCAPE_CHARACTER;
		}

	}

	private void append(StringBuffer result, CharacterIterator iterator) {
		if (iterator.first() != CharacterIterator.DONE) {
			char aChar;

			result.append(iterator.first());
			while ((aChar = iterator.next()) != CharacterIterator.DONE) {
				result.append(aChar);
			}
		}
	}

	AttributedCharacterIterator createAttributedCharacterIterator(String s) {
		AttributedString as = new AttributedString(s);

		return as.getIterator();
	}

	AttributedCharacterIterator createAttributedCharacterIterator(AttributedCharacterIterator[] iterators) {
		/*AttributedString as = new AttributedString(iterators);

		return as.getIterator();*/
		return null;
	}


	AttributedCharacterIterator createAttributedCharacterIterator(String string, AttributedCharacterIterator.Attribute key, Object value) {
		AttributedString as = new AttributedString(string);

		as.addAttribute(key, value);
		return as.getIterator();
	}
}
