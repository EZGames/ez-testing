package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class WithNoMessage extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	protected WithNoMessage(ThrowableBaseChainableMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
	//***************************************************************************
	// Implementation methods
	//***************************************************************************	
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with no message");
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		return t.getMessage() == null || messageIsNameOfCause(t);
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with message \"")
			.appendText(t.getMessage())
			.appendText("\"");
	}
	
	//***************************************************************************
	// Private helper method
	//***************************************************************************
	private boolean messageIsNameOfCause(Throwable t)
	{
		Throwable cause = t.getCause();
		if(cause == null)
			return false;
		return cause.getClass().getName().equals(t.getMessage());
	}
}