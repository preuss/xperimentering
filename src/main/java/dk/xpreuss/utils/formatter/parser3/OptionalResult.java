package dk.xpreuss.utils.formatter.parser3;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public final class OptionalResult<T, E> {
	private static final OptionalResult<?, ?> EMPTY = new OptionalResult<>();

	final private T result;
	final private E error;

	/**
	 * Constructs an empty instance.
	 *
	 * @implNote Generally only one empty instance, {@link OptionalResult#EMPTY},
	 * should exist per VM.
	 */
	public OptionalResult() {
		result = null;
		error = null;
	}

	/**
	 * Returns an empty {@code OptionalResult} instance. No value or error is present for this
	 * OptionalResult.
	 *
	 * @param <T> Type of the non-existent result value
	 * @param <E> Type of the non-existent error value
	 * @return an empty {@code OptionalResult}
	 * @apiNote Though it may be tempting to do so, avoid testing if an object
	 * is empty by comparing with {@code ==} against instances returned by
	 * {@code OptionResult.empty()}. There is no guarantee that it is a singleton.
	 * Instead, use {@link #isResultPresent()}.
	 */
	public static <T, E> OptionalResult<T, E> empty() {
		@SuppressWarnings("unchecked")
		OptionalResult<T, E> t = (OptionalResult<T, E>) EMPTY;
		return t;
	}

	public OptionalResult(T result, E error) {
		this.result = result;
		this.error = error;
	}

	public T getResult() {
		return result;
	}

	public boolean isResultPresent() {
		return hasResult();
	}

	public boolean hasResult() {
		return result != null;
	}

	public static <T, E> OptionalResult<T, E> ofResult(T result) {
		Objects.requireNonNull(result);
		return new OptionalResult<>(result, null);
	}

	public static <T, E> OptionalResult<T, E> ofNullableResult(T result) {
		return result == null ? new OptionalResult<>(null, null) :
				new OptionalResult<>(result, null);
	}

	public E getError() {
		if (error == null) {
			throw new NoSuchElementException("No error present");
		}
		return error;
	}

	public boolean isErrorPresent() {
		return hasError();
	}

	public boolean hasError() {
		return error != null;
	}

	public static <T, E> OptionalResult<T, E> ofError(E error) {
		return new OptionalResult<>(null, Objects.requireNonNull(error));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OptionalResult<?, ?> other)) {
			return false;
		}

		return Objects.equals(result, other.result) && Objects.equals(error, other.error);
	}

	/**
	 * Returns the hash code value of the present value, if any, or 0 (zero) if
	 * <p>
	 * no value is present.
	 *
	 * @return hash code value of the present value or 0 if no value is present
	 */
	@Override
	public int hashCode() {
		return Objects.hash(result, error);
	}

	/**
	 * Returns a non-empty string representation of this Optional suitable for
	 * <p>
	 * debugging. The exact presentation format is unspecified and may vary
	 * <p>
	 * between implementations and versions.
	 *
	 * @return the string representation of this instance
	 * @implSpec If a value is present the result must include its string
	 * <p>
	 * representation in the result. Empty and present Optionals must be
	 * <p>
	 * unambiguously differentiable.
	 */
	@Override
	public String toString() {
		return result != null
				? String.format("OptionalResult[%s]", result)
				:
				(this == EMPTY
						? "OptionalResult.empty"
						: String.format("OptionalResult.error[%s]", error)
				);
	}


	public static void main(String[] args) {
		OptionalResult<String, Exception> result = OptionalResult.ofResult("55");
		System.out.println(result);

		System.out.println("Of  + Result, %Error");
		result = OptionalResult.ofResult("Result");
		System.out.println(result);

		System.out.println("Of  % Result, %Error");
		result = OptionalResult.empty();
		System.out.println(result);

		System.out.println("Of  % Result, +Error");
		result = OptionalResult.ofError(new Exception("Error"));
		System.out.println(result);

		System.out.println(OptionalResult.ofResult(new Exception("Hello")));
		System.out.println(OptionalResult.ofNullableResult(null));
		System.out.println(OptionalResult.ofError(new Exception("Hello")));
		System.out.println(OptionalResult.empty());
	}
}
