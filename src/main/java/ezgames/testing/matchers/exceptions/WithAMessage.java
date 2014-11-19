package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class WithAMessage extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	WithAMessage(ThrowableBaseChainableMatcher obj)
	{
		super(obj);
	}
	
	//***************************************************************************
	// Implementation methods
	//***************************************************************************
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with a message");
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		String message = t.getMessage();
		return message != null && !message.isEmpty() && messageIsntNameOfCause(t);
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with no message");
	}
	
	//***************************************************************************
	// Private helper method
	//***************************************************************************
	private boolean messageIsntNameOfCause(Throwable t)
	{
		Throwable cause = t.getCause();
		if(cause == null)
			return true;
		return !cause.getClass().getName().equals(t.getMessage());
	}	
}