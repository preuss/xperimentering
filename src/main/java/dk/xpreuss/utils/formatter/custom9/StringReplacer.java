package dk.xpreuss.utils.formatter.custom9;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StringReplacer {
	public static void main(String[] args) {
		StringReplacer replacer = new StringReplacer();
		Map<String, Object> map = new HashMap<>();
		map.put("age", 45);
		map.put("name", "Jesper Preuss");
		String formattedString = replacer.replace("Age: {age}, Name: {name}", map);
		System.out.println(formattedString);
	}

	private String replace(String rawString, Map<String, Object> translationDictionary) {
		String formattedString = rawString;
		String[] keys = translationDictionary.keySet().toArray(new String[0]);
		for(int i=0;i<keys.length;i++) {
			String key = keys[i];
			formattedString = formattedString.replaceAll("{"+key+"}", translationDictionary.get(key).toString());
		}
		return formattedString;
	}

	public String format(String empty) {
		return "";
	}
}
