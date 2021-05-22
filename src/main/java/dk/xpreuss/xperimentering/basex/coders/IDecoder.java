package dk.xpreuss.xperimentering.basex.coders;

public interface IDecoder<TSource,UDestination> {
	UDestination decode(TSource src);
}
