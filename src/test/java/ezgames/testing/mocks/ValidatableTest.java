package ezgames.testing.mocks;

import static org.junit.Assert.*;
import static org.hamcrest.core.StringContains.containsString;
import static ezgames.testing.mocking.Validates.*;
import org.junit.Test;
import ezgames.testing.mocking.Validatable;

public class ValidatableTest
{
	@Test
	public void shouldFail()
	{
		MockValidatable obj = new MockValidatable(false);
		
		assertThat(obj, doesNotValidate());
	}
	
	@Test
	public void shouldPass()
	{
		MockValidatable obj = new MockValidatable(true);
		
		assertThat(obj, validates());
	}
	
	@Test
	public void shouldGetErrorMessage()
	{
		MockValidatable obj = new MockValidatable(false);
		obj.makeInvalid();
		
		try
		{
			assertThat(obj, validates());
			fail("assertion was supposed to fail");
		}
		catch(AssertionError err)
		{
			System.out.println(err.getMessage());
			assertThat(err.getMessage(), containsString(MockValidatable.ERROR_MESSAGE));
		}
	}
}

class MockValidatable implements Validatable
{
	static final String ERROR_MESSAGE = "made invalid";
	
	MockValidatable(boolean startsValid) { isValid = startsValid; }
	
	public void makeValid() { isValid = true; }
	
	public void makeInvalid() { isValid = false; errorMessage = ERROR_MESSAGE; }
	
	public boolean validate() { return isValid; }

	public String errorMessage() { return errorMessage; }
	
	private boolean isValid;
	private String errorMessage;
}
