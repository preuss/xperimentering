package dk.xpreuss.xperimentering.basex.coders;

public interface IStringDecoder extends IDecoder<String, String> {
	String decode(String src);
}
