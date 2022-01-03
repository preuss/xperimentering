package dk.xpreuss.utils.formatter.parser3.tokentypes;

import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.StringJoiner;

public enum ControlCharacterTokenSubType implements TokenSubType {
	NULL(0, "NULL", "Null character"),
	SOH(1, "SOH", "Start of Header"),
	STX(2, "STX", "Start of Text"),
	ETX(3, "ETX", "End of Text"),
	EOT(4, "EOT", "End of Transmission"),
	ENQ(5, "ENQ", "Enquiry"),
	ACK(6, "ACK", "Acknowledgement"),
	BEL(7, "BEL", "Bell"),
	BS(8, "BS", "Backspace"),
	HT(9, "HT", "Horizontal Tab"),
	LF(10, "LF", "Line feed"),
	VT(11, "VT", "Vertical Tab"),
	FF(12, "FF", "Form feed"),
	CR(13, "CR", "Carriage return"),
	SO(14, "SO", "Shift Out"),
	SI(15, "SI", "Shift In"),
	DLE(16, "DLE", "Data link escape"),
	DC1(17, "DC1", "Device control 1"),
	DC2(18, "DC2", "Device control 2"),
	DC3(19, "DC3", "Device control 3"),
	DC4(20, "DC4", "Device control 4"),
	NAK(21, "NAK", "NAK Negative-acknowledge"),
	SYN(22, "SYN", "Synchronous idle"),
	ETB(23, "ETB", "End of trans. block"),
	CAN(24, "CAN", "Cancel"),
	EM(25, "EM", "End of medium"),
	SUB(26, "SUB", "Substitute"),
	ESC(27, "ESC", "Escape"),
	FS(28, "FS", "File separator"),
	GS(29, "GS", "Group separator"),
	RS(30, "RS", "Record separator"),
	US(31, "US", "Unit separator"),
	DEL(127, "DEL", "Delete"),
	PAD(128, "PAD", "Padding character"),
	HOP(129, "HOP", "High Octet Preset"),
	BPH(130, "BPH", "Break Permitted Here"),
	NBH(131, "NBH", "No Break Here"),
	IND(132, "IND", "Index"),
	NEL(133, "NEL", "Next Line"),
	SSA(134, "SSA", "Start of Selected Area"),
	ESA(135, "ESA", "End of Selected Area"),
	HTS(136, "HTS", "Character Tabulation Set"),
	HTJ(137, "HTJ", "character Tabulation with Justification"),
	VTS(138, "VTS", "Line Tabulation Set"),
	PLD(139, "PLD", "Partial Line Forward"),
	PLU(140, "PLU", "Partial Line Backward"),
	RI(141, "RI", "Reverse Line Feed"),
	SS2(142, "SS2", "Single-Shift Two"),
	SS3(143, "SS3", "Single-Shift Three"),
	DCS(144, "DCS", "Device Control String"),
	PU1(145, "PU1", "Private Use 1"),
	PU2(146, "PU2", "Private Use 2"),
	STS(147, "STS", "Set Transmit State"),
	CCH(148, "CCH", "Cancel character"),
	MW(149, "MW", "Message Waiting"),
	SPA(150, "SPA", "Start of Protected Area"),
	EPA(151, "EPA", "End of Protected Area"),
	SOS(152, "SOS", "Start of String"),
	STC(153, "STC", "Single Graphic Character Introducer"),
	SCI(154, "SCI", "Single Character intro Introducer"),
	CSI(155, "CSI", "Control Sequence Introducer"),
	ST(156, "ST", "String Terminator"),
	OSC(157, "OSC", "Operating System Command"),
	PM(158, "PM", "Private Message"),
	APC(159, "APC", "Application Program Command");

	private final int decCodePoint;
	private final String charName;
	private final String description;

	private ControlCharacterTokenSubType(int decCodePoint, String charName, String description) {
		this.decCodePoint = decCodePoint;
		this.charName = charName;
		this.description = description;
	}

	public int getDecCodePoint() {
		return decCodePoint;
	}

	public String getCharName() {
		return charName;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ControlCharacterTokenSubType.class.getSimpleName() + "[", "]")
				.add("decCodePoint=" + decCodePoint)
				.add("charName='" + charName + "'")
				.add("description='" + description + "'")
				.toString();
	}
}
