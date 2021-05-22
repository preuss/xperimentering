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
public class Base64EncoderTest {

	Base64Encoder encoder;
	final static String DECODED = "Hello, world!";
	final static String ENCODED = "SGVsbG8sIHdvcmxkIQ==";

	public Base64EncoderTest() {
		encoder = new Base64Encoder();
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
		System.out.println("** Base64EncoderTest -> Contructors");
		Base64Encoder obj = new Base64Encoder();
		assertEquals(Base64Encoder.BUFFER_SIZE, obj.getSize());
		obj = new Base64Encoder(3600);
		assertEquals(3600, obj.getSize());
	}

	/**
	 * Test of setSize method, of class Base64Encoder.
	 */
	@Test
	public void testEncode_SetSize() {
		System.out.println("** Base64EncoderTest -> setSize(int)");
		encoder.setSize(3600);
		assertEquals(3600, encoder.getSize());
	}

	/**
	 * Test of getSize method, of class Base64Encoder.
	 */
	@Test
	public void testGetSize() {
		System.out.println("** Base64EncoderTest -> getSize()");
		assertEquals(encoder.getSize(), Base64Encoder.BUFFER_SIZE);
	}

	/**
	 * Test of encode method, of class Base64Encoder.
	 */
	@Test
	public void testEncode_String() {
		System.out.println("** Base64EncoderTest -> encode(String)");
		byte[] bytes = encoder.encode(DECODED);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, ENCODED);
	}

	/**
	 * Test of encode method, of class Base64Encoder.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEncode_InputStream() throws Exception {
		System.out.println("** Base64EncoderTest -> encode(InputStream)");
		ByteArrayInputStream stream = new ByteArrayInputStream(DECODED.getBytes());
		byte[] bytes = encoder.encode(stream);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, ENCODED);
	}

	/**
	 * Test of decode method, of class Base64Encoder.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEncode_InputStream_int() throws Exception {
		System.out.println("** Base64EncoderTest -> encode(InputStream, int)");
		ByteArrayInputStream stream = new ByteArrayInputStream(DECODED.getBytes());
		byte[] bytes = encoder.encode(stream, 512);
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, ENCODED);
	}

	/**
	 * Test of encode method, of class Base64Encoder.
	 */
	@Test
	public void testEncode_byteArr() {
		System.out.println("** Base64EncoderTest -> encode(byte[])");
		byte[] bytes = encoder.encode(DECODED.getBytes());
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, ENCODED);
	}

	/**
	 * Test of encode method, of class Base64Encoder.
	 */
	@Test
	public void testEncode_charArr() {
		System.out.println("** Base64EncoderTest -> encode(char[])");
		byte[] bytes = encoder.encode(DECODED.toCharArray());
		String result = new String(bytes);
		System.out.println("Result: '" + result + "'");
		assertEquals(result, ENCODED);
	}

}