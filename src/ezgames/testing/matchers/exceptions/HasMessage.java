package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

class HasMessage<X extends Throwable> extends ThrowsMatcher<X>
{
	public HasMessage(ThrowsMatcher<X> toBeDecorated, String message)
	{
		decoratedMatcher = toBeDecorated;
		this.message = message;
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
			matches = decoratedMatcher.matchesSafely(item) && message.equals(getException().getMessage());
		}
		catch(NullPointerException ex)
		{
			//happens if the expected message given is null
			matches = false;
		}

		return matches;
	}
	
	@Override
	public void describeTo(Description description)
	{
		decoratedMatcher.describeTo(description);
		
		description.appendText("\nexception message: ")
				.appendText(message);		
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
			mismatchDescription.appendText("\nexception message: ")
					.appendText(getException().getMessage());
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ThrowsMatcher<X> decoratedMatcher;
	private boolean matches;
	private String message;
}
