package dk.xpreuss.utils.formatter.parser1;

public class FullQueueException extends IllegalStateException {
	public FullQueueException() {
	}

	public FullQueueException(String s) {
		super(s);
	}

	public FullQueueException(String message, Throwable cause) {
		super(message, cause);
	}

	public FullQueueException(Throwable cause) {
		super(cause);
	}
}
