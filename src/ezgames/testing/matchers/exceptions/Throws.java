package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class Throws extends ThrowsMatcher
{
	public static Throws throwsA(Class<? extends Throwable> expectedExceptionType)
	{
		return new Throws(expectedExceptionType);
	}
	
	protected Throws(Class<? extends Throwable> expectedExceptionType)
	{
		super(null);
		this.expectedExceptionType = expectedExceptionType;
	}

	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("threw ")
			.appendText(expectedExceptionType.getName());
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		return expectedExceptionType.isInstance(t);
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		if(t == null)
		{
			mismatchDescription.appendText("No exception thrown");
			return;
		}
		else
		{
			mismatchDescription.appendText("threw ")
				.appendText(t.getClass().getName());
		}
	}
	
	private Class<? extends Throwable> expectedExceptionType;
}
