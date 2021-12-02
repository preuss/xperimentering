package dk.xpreuss.utils.formatter.parser1;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StringFormatSourceScanner {
	private String source;
	private List<CodePoint> codePoints;

	private int indexPointer;
	private CodePoint currentChar;

	public StringFormatSourceScanner(String source) {
		this.source = source;
		this.codePoints = source.codePoints().mapToObj(CodePoint::wrap).toList();
	}

	public static void main(String[] args) {
		String source = "Hello World!";
		StringFormatSourceScanner scan = new StringFormatSourceScanner(source);
		CodePoint next;
		while ((next = scan.next()) != null) {

			System.out.println("Next: " + next);
			CodePoint peek = scan.peek();
			System.out.println("Peek: " + peek);
		}
	}

	public CodePoint next() {
		return getCodePointAt(indexPointer++);
	}

	private CodePoint getCodePointAt(int indexPointer) {
		return indexPointer < codePoints.size() ? codePoints.get(indexPointer) : null;
	}

	public CodePoint peek() {
		return getCodePointAt(indexPointer + 1);
	}
}
