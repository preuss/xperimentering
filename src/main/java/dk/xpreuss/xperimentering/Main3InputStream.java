package dk.xpreuss.xperimentering;

import net.jhorstmann.base64.Base64OutputStream;

import java.io.*;

public class Main3InputStream {
	public static void main(String[] args) throws FileNotFoundException {
		OutputStream os = new Base64OutputStream(new OutputStreamWriter(new BufferedOutputStream(new PrintStream(new File("")))));
		Writer w;
	}
}
