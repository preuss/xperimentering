package dk.xpreuss.xperimentering.basex.coders;

public interface IStringEncoder extends IEncoder<String, String> {
	String encode(String src);
}
