package dk.xpreuss.utils.formatter.parser3;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class OptionalResultTest {
	public static final String testString = "Hello World!";
	@Mock
	Exception error;
	@InjectMocks
	OptionalResult<String, Exception> optionalResult;

	@BeforeMethod
	public void setUp() {
		//MockitoAnnotations.initMocks(this);
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testEmpty() {
		OptionalResult<String, Exception> result = OptionalResult.empty();
		Assert.assertFalse(result.hasResult());
		Assert.assertFalse(result.hasError());
	}

	@Test
	public void testIsResultPresent() {
		OptionalResult<String, Exception> optionalResult = OptionalResult.ofResult("A result");
		boolean result = optionalResult.isResultPresent();
		Assert.assertTrue(result);
	}

	@Test
	public void testHasResult() {
		boolean result = optionalResult.hasResult();
		Assert.assertTrue(result);
	}

	@Test
	public void testOfResult() {
		OptionalResult<String, Exception> result = OptionalResult.ofResult(testString);
		Assert.assertTrue(result.hasResult());
		Assert.assertEquals(result.getResult(), testString);
	}

	@Test
	public void testOfNullableResult() {
		OptionalResult<String, Exception> result = OptionalResult.ofNullableResult(testString);
		Assert.assertEquals(result, new OptionalResult<String, Exception>(testString, new Exception()));
	}

	@Test
	public void testGetError() {
		Exception exception = new Exception();
		OptionalResult<String, Exception> optionalResult = OptionalResult.ofError(exception);
		Exception result = optionalResult.getError();
		Assert.assertEquals(result, exception);
	}

	@Test
	public void testIsErrorPresent() {
		boolean result = optionalResult.isErrorPresent();
		Assert.assertTrue(result);
	}

	@Test
	public void testHasError() {
		boolean result = optionalResult.hasError();
		Assert.assertTrue(result);
	}

	@Test
	public void testOfError() {
		OptionalResult<String, Exception> result = OptionalResult.ofError(new Exception());
		Assert.assertEquals(result, new OptionalResult<String, Exception>(null, new Exception()));
	}

	@Test
	public void testEquals() {
		OptionalResult<String, Exception> optionalResult = OptionalResult.ofResult(testString);
		boolean result = optionalResult.equals("obj");
		Assert.assertFalse(result);
	}

	@Test
	public void testHashCode() {
		OptionalResult<String, Exception> optionalResult = OptionalResult.ofResult(testString);
		int result = optionalResult.hashCode();
		Assert.assertEquals(result, 22679876);
	}

	@Test
	public void testToString() {
		OptionalResult<String, Exception> optionalResult = OptionalResult.ofResult(testString);
		String result = optionalResult.toString();
		Assert.assertEquals(result, "OptionalResult[Hello World!]");
	}

	@Test
	public void testMain() {
		OptionalResult.main(new String[]{"args"});
	}
}