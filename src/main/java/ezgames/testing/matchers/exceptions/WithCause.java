package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class WithCause extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	protected WithCause(ThrowableBaseChainableMatcher decoratedMatcher, Class<? extends Throwable> expectedCause)
	{
		super(decoratedMatcher);
		this.expectedCause = expectedCause;
	}

	//***************************************************************************
	// Implementation methods
	//***************************************************************************
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with cause of")
			.appendText(expectedCause.getName());
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		return expectedCause.isInstance(t.getCause());
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with cause of ")
			.appendText(t.getClass().getName());
	}
	
	//***************************************************************************
	// Private field
	//***************************************************************************
	private Class<?> expectedCause;
}