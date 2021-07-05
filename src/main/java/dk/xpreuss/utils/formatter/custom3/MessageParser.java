package dk.xpreuss.utils.formatter.custom3;

import java.util.ArrayList;
import java.util.List;

public class MessageParser {
	public MessageParser() {
	}

	public void parse(String messagePattern) {
		System.out.println("--- STARTING ---");
		List<String> foundPatter = new ArrayList<>();



		for (int i = 0; i < foundPatter.size(); i++) {
			String s = foundPatter.get(i);
			System.out.println("Pattern(" + i + "): " + s);
		}
		System.out.println("--- ENDING ---");
	}

	public static void main(String[] args) {
		MessageParser mp = new MessageParser();
		mp.parse("hello world");
	}
}
