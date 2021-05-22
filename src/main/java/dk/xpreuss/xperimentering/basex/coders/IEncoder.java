package dk.xpreuss.xperimentering.basex.coders;

public interface IEncoder<TSource,UDestination> {
	UDestination encode(TSource src);
}
