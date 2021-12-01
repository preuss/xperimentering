package dk.xpreuss.utils.formatter.custom9;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class StringReplacerTest {
	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll executed static");
	}

	@AfterAll
	static void tear() {
		System.out.println("@AfterAll executed");
	}

	@BeforeEach
	void setupThis() {
		System.out.println("@BeforeEach executed");
	}

	@Test
	public void emptyTest() {
		final String expected = "";

		final String empty = "";
		StringReplacer replacer = new StringReplacer();
		final String actual = replacer.format(empty);

		Assertions.assertEquals(expected, actual);
	}
}
