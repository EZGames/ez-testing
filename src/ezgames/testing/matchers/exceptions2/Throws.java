package ezgames.testing.matchers.exceptions2;

import org.hamcrest.Description;
import ezgames.testing.matchers.exceptions.ThrowingRunnable;

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
	protected boolean chainMatches(ThrowingRunnable item)
	{
		try
		{
			item.run();
			return false;
		}
		catch(Throwable t)
		{
			thrown = t;
			return expectedExceptionType.isInstance(t);
		}
	}
	
	@Override
	protected void chainDescribeMismatch(ThrowingRunnable item, Description mismatchDescription)
	{
		if(null == thrown)
		{
			mismatchDescription.appendText("No exception thrown");
			return;
		}
		else
		{
			mismatchDescription.appendText("threw ")
				.appendText(thrown.getClass().getName());
		}
	}
	
	private Class<? extends Throwable> expectedExceptionType;
	private Throwable thrown;
}
