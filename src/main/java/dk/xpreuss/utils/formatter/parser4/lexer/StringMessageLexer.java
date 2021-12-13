package dk.xpreuss.utils.formatter.parser4.lexer;

import dk.xpreuss.utils.formatter.parser1.CodePoint;
import dk.xpreuss.utils.formatter.parser4.IToken;
import dk.xpreuss.utils.formatter.parser4.scanner.StringScanner;
import dk.xpreuss.utils.formatter.parser4.ILexer;
import dk.xpreuss.utils.formatter.parser4.IScanner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class StringMessageLexer implements ILexer {
	private IScanner scanner;
	private IToken currentToken;

	public StringMessageLexer(String replaceableMessage) {
		this.scanner = new StringScanner(replaceableMessage);
	}

	@Override
	public boolean hasNext() {
		CodePoint currentCp;
		while (scanner.hasNext()) {
			currentCp = scanner.next();
			if (isNewLine(currentCp)) {

			}
		}
		return false;
	}

	@Override
	public IToken next() {
		throw new NoSuchElementException();
	}

	private boolean isControlCharacter(CodePoint cp) {
		final List<Triple<Integer, String, String>> controlCharacters = Collections.unmodifiableList(
				Arrays.asList(
						Triple.of(0, "NULL", "Null character"),
						Triple.of(1, "SOH", "Start of Header"),
						Triple.of(2, "STX", "Start of Text"),
						Triple.of(3, "ETX", "End of Text"),
						Triple.of(4, "EOT", "End of Transmission"),
						Triple.of(5, "ENQ", "Enquiry"),
						Triple.of(6, "ACK", "Acknowledgement"),
						Triple.of(7, "BEL", "Bell"),
						Triple.of(8, "BS", "Backspace"),
						Triple.of(9, "HT", "Horizontal Tab"),
						Triple.of(10, "LF", "Line feed"),
						Triple.of(11, "VT", "Vertical Tab"),
						Triple.of(12, "FF", "Form feed"),
						Triple.of(13, "CR", "Carriage return"),
						Triple.of(14, "SO", "Shift Out"),
						Triple.of(15, "SI", "Shift In"),
						Triple.of(16, "DLE", "Data link escape"),
						Triple.of(17, "DC1", "Device control 1"),
						Triple.of(18, "DC2", "Device control 2"),
						Triple.of(19, "DC3", "Device control 3"),
						Triple.of(20, "DC4", "Device control 4"),
						Triple.of(21, "NAK", "NAK Negative-acknowledge"),
						Triple.of(22, "SYN", "Synchronous idle"),
						Triple.of(23, "ETB", "End of trans. block"),
						Triple.of(24, "CAN", "Cancel"),
						Triple.of(25, "EM", "End of medium"),
						Triple.of(26, "SUB", "Substitute"),
						Triple.of(27, "ESC", "Escape"),
						Triple.of(28, "FS", "File separator"),
						Triple.of(29, "GS", "Group separator"),
						Triple.of(30, "RS", "Record separator"),
						Triple.of(31, "US", "Unit separator"),
						Triple.of(127, "DEL", "Delete"),
						Triple.of(128, "PAD", "Padding character"),
						Triple.of(129, "HOP", "High Octet Preset"),
						Triple.of(130, "BPH", "Break Permitted Here"),
						Triple.of(131, "NBH", "No Break Here"),
						Triple.of(132, "IND", "Index"),
						Triple.of(133, "NEL", "Next Line"),
						Triple.of(134, "SSA", "Start of Selected Area"),
						Triple.of(135, "ESA", "End of Selected Area"),
						Triple.of(136, "HTS", "Character Tabulation Set"),
						Triple.of(137, "HTJ", "character Tabulation with Justification"),
						Triple.of(138, "VTS", "Line Tabulation Set"),
						Triple.of(139, "PLD", "Partial Line Forward"),
						Triple.of(140, "PLU", "Partial Line Backward"),
						Triple.of(141, "RI", "Reverse Line Feed"),
						Triple.of(142, "SS2", "Single-Shift Two"),
						Triple.of(143, "SS3", "Single-Shift Three"),
						Triple.of(144, "DCS", "Device Control String"),
						Triple.of(145, "PU1", "Private Use 1"),
						Triple.of(146, "PU2", "Private Use 2"),
						Triple.of(147, "STS", "Set Transmit State"),
						Triple.of(148, "CCH", "Cancel character"),
						Triple.of(149, "MW", "Message Waiting"),
						Triple.of(150, "SPA", "Start of Protected Area"),
						Triple.of(151, "EPA", "End of Protected Area"),
						Triple.of(152, "SOS", "Start of String"),
						Triple.of(153, "STC", "Single Graphic Character Introducer"),
						Triple.of(154, "SCI", "Single Character intro Introducer"),
						Triple.of(155, "CSI", "Control Sequence Introducer"),
						Triple.of(156, "ST", "String Terminator"),
						Triple.of(157, "OSC", "Operating System Command"),
						Triple.of(158, "PM", "Private Message"),
						Triple.of(159, "APC", "Application Program Command")
				)
		);
		return controlCharacters.stream().parallel().anyMatch(triple -> cp.getValue() == triple.getLeft());
	}

	private boolean isWhiteSpace(CodePoint cp) {
		final List<Triple<Integer, String, String>> controlCharacters = Collections.unmodifiableList(
				Arrays.asList(
						Triple.of(32, " ", "Space"),
						Triple.of(160, "NBSP", "Non-breaking space")
				)
		);
		return false;
	}

	private boolean isNewLine(CodePoint cp) {
		return true;
	}
}
