package net.jhorstmann.base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64InputStreamTest {

    private static byte[] randomBytes(int length) {
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = (byte)(Math.random() * 256 - 128);
        }
        return data;
    }

    private static int LENGTH = 12345678;
    private static int BUFFER_SIZE = 256;

    @Test
    public void testSingleChar() throws IOException {
        byte[] bytes = randomBytes(LENGTH);
        String base64 = new Base64StreamEncoder().encode(bytes);
        //System.out.println(base64);
        //Assert.assertArrayEquals(bytes, new Base64StreamDecoder().decode(base64));
        InputStream in = new Base64InputStream(new StringReader(base64));
        int ch, pos = 0;
        while ((ch = in.read()) != -1) {
            Assertions.assertTrue(pos < bytes.length);
            //System.out.println(pos + " " + bytes[pos] + " " + ch);
            Assertions.assertEquals(bytes[pos], (byte)ch);
            pos++;
        }
    }

    @Test
    public void testBlock() throws IOException {
        byte[] bytes = randomBytes(LENGTH);
        String base64 = new Base64StreamEncoder().encode(bytes);

        InputStream in = new Base64InputStream(new StringReader(base64));
        byte[] buf = new byte[BUFFER_SIZE];
        int len, pos = 0;
        while ((len = in.read(buf)) != -1) {
            //System.out.println(pos + " " + len + " " + bytes[pos] + " " + buf[0]);

            Assertions.assertTrue(pos < bytes.length);
            Assertions.assertTrue(pos+len <= bytes.length);

            byte[] expected = Arrays.copyOfRange(bytes, pos, pos+len);
            byte[] actual   = Arrays.copyOfRange(buf, 0, len);
            Assertions.assertArrayEquals(expected, actual);

            pos += len;
        }

    }

    @Test
    public void testMixed() throws IOException {
        byte[] bytes = randomBytes(LENGTH);
        String base64 = new Base64StreamEncoder().encode(bytes);

        InputStream in = new Base64InputStream(new StringReader(base64));
        byte[] buf = new byte[BUFFER_SIZE];
        int len, pos = 0;
        while ((len = in.read(buf)) != -1) {
            //System.out.println(pos + " " + len + " " + bytes[pos] + " " + buf[0]);

            Assertions.assertTrue(pos < bytes.length);
            Assertions.assertTrue(pos+len <= bytes.length);

            byte[] expected = Arrays.copyOfRange(bytes, pos, pos+len);
            byte[] actual   = Arrays.copyOfRange(buf, 0, len);
            Assertions.assertArrayEquals(expected, actual);

            pos += len;

            int count = (int)(Math.random()*BUFFER_SIZE/32);
            int ch;
            while (count-- > 0 && pos < bytes.length && ((ch = in.read()) != -1)) {
                Assertions.assertEquals(bytes[pos],(byte)ch);
                pos++;
            }
        }

    }

}
