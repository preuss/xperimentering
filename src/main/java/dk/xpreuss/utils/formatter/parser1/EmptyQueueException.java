package dk.xpreuss.utils.formatter.parser1;

public class EmptyQueueException extends IllegalStateException {
	public EmptyQueueException() {
	}

	public EmptyQueueException(String s) {
		super(s);
	}

	public EmptyQueueException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyQueueException(Throwable cause) {
		super(cause);
	}
}
