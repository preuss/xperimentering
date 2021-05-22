/*
 * Web service library for the Batchelor batch job queue.
 * Copyright (C) 2009 by Anders Lövgren and the Computing Department at BMC,
 * Uppsala University.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Send questions, suggestions, bugs or comments to:
 * Anders Lövgren (andlov@nowise.se)
 *
 * For more info: https://github.com/nowisesys/base64code/
 */
package se.nowise.codecs.base64;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anders Lövgren (Nowise Systems/BMC-IT, Uppsala University)
 */
public class Base64DecoderTest {

	Base64Decoder decoder;
	final static String DECODED = "Hello, world!";
	final static String ENCODED = "SGVsbG8sIHdvcmxkIQ==";

	public Base64DecoderTest() {
		decoder = new Base64Decoder();
	}

	@BeforeAll
	public static void setUpClass() throws Exception {
	}

	@AfterAll
	public static void tearDownClass() throws Exception {
	}

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	/**
	 * Test constructors.
	 */
	@Test
	public void testEncode_Constructors() {
		System.out.println("** Base64DecoderTest -> Contructors");
		Base64Decoder obj = new Base64Decoder();
		assertEquals(Base64Decoder.BUFFER_SIZE, obj.getSize());
		obj = new Base64Decoder(3600);
		assertEquals(3600, obj.getSize());
	}

	/**
	 * Test of setSize method, of class Base64Decoder.
	 */
	@Test
	public void testSetSize() {
		System.out.println("** Base64DecoderTest -> setSize(int)");
		decoder.setSize(3600);
		assertEquals(3600, decoder.getSize());
	}

	/**
	 * Test of getSize method, of class Base64Decoder.
	 */
	@Test
	public void testGetSize() {
		System.out.println("** Base64DecoderTest -> getSize()");
		assertEquals(decoder.getSize(), Base64Decoder.BUFFER_SIZE);
	}

	/**
	 * Test of decode method, of class Base64Decoder.
	 */
	@Test
	public void testDecode_String() {
		System.out.println("** Base64DecoderTest -> decode(String)");
		byte[] bytes = decoder.decode(ENCODED);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, DECODED);
	}

	/**
	 * Test of decode method, of class Base64Decoder.
	 *
	 * @throws Exception
	 */
	@Test
	public void testDecode_InputStream() throws Exception {
		System.out.println("** Base64DecoderTest -> decode(InputStream)");
		ByteArrayInputStream stream = new ByteArrayInputStream(ENCODED.getBytes());
		byte[] bytes = decoder.decode(stream);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, DECODED);
	}

	/**
	 * Test of decode method, of class Base64Decoder.
	 *
	 * @throws Exception
	 */
	@Test
	public void testDecode_InputStream_int() throws Exception {
		System.out.println("** Base64DecoderTest -> decode(InputStream, int)");
		ByteArrayInputStream stream = new ByteArrayInputStream(ENCODED.getBytes());
		byte[] bytes = decoder.decode(stream, 512);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, DECODED);
	}

	/**
	 * Test of decode method, of class Base64Decoder.
	 */
	@Test
	public void testDecode_byteArr() {
		System.out.println("** Base64DecoderTest -> decode(byte[])");
		byte[] bytes = decoder.decode(ENCODED.getBytes());
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, DECODED);
	}

	/**
	 * Test of decode method, of class Base64Decoder.
	 */
	@Test
	public void testDecode_charArr() {
		System.out.println("** Base64DecoderTest -> decode(char[])");
		byte[] bytes = decoder.decode(ENCODED.toCharArray());
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, DECODED);
	}
}
