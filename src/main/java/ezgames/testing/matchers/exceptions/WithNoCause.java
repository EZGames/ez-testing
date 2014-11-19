package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class WithNoCause extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	protected WithNoCause(ThrowableBaseChainableMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
	//***************************************************************************
	// Implementation methods
	//***************************************************************************
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with no cause");
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		cause = t.getCause();
		return cause == null;
	}

	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with cause of type ")
			.appendText(cause.getClass().getName());
	}
	
	//***************************************************************************
	// Private field
	//***************************************************************************
	private Throwable cause;
}