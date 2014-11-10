package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

class HasCause<X extends Throwable> extends ThrowsMatcher<X>
{	
	public HasCause(ThrowsMatcher<X> toBeDecorated, Class<? extends Throwable> cause)
	{
		decoratedMatcher = toBeDecorated;
		expectedCause = cause;
	}
	
	//***************************************************************************
	// Matcher Methods
	//***************************************************************************
	@Override
	protected Throwable getException()
	{
		return decoratedMatcher.getException();
	}
	
	@Override
	protected boolean matchesSafely(ThrowingRunnable item)
	{
		try
		{
			matches = decoratedMatcher.matchesSafely(item) && getException().getCause().getClass().isAssignableFrom(expectedCause);
		}
		catch(NullPointerException ex)
		{
			matches = false;
		}

		return matches;
	}
	
	@Override
	public void describeTo(Description description)
	{
		decoratedMatcher.describeTo(description);
		
		description.appendText("\nexception cause: ")
				.appendText(expectedCause.getName());	
	}
	
	@Override
	protected void describeMismatchSafely(ThrowingRunnable item, Description mismatchDescription)
	{
		if(matches)
		{
			decoratedMatcher.describeMismatchSafely(item, mismatchDescription);
		}
		else
		{
			decoratedMatcher.describeTo(mismatchDescription);
			mismatchDescription.appendText("\nexception cause: ")
					.appendText(getException().getCause().getClass().getName());
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ThrowsMatcher<X> decoratedMatcher;
	private boolean matches;
	private Class<? extends Throwable> expectedCause;
}
