package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class ThrowsA extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Static factory methods
	//***************************************************************************
	public static ThrowsA throwsA(Class<? extends Throwable> expectedExceptionType)
	{
		return new ThrowsA(expectedExceptionType);
	}
	
	public static ThrowsA throwsAn(Class<? extends Throwable> expectedExceptionType)
	{
		return throwsA(expectedExceptionType);
	}
	
	public static ThrowsA a(Class<? extends Throwable> expectedExceptionType)
	{
		return throwsA(expectedExceptionType);
	}
	
	public static ThrowsA an(Class<? extends Throwable> expectedExceptionType)
	{
		return throwsA(expectedExceptionType);
	}

	//***************************************************************************
	// Implementation methods
	//***************************************************************************
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
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private ThrowsA(Class<? extends Throwable> expectedExceptionType)
	{
		super(null);
		this.expectedExceptionType = expectedExceptionType;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private Class<? extends Throwable> expectedExceptionType;
}
