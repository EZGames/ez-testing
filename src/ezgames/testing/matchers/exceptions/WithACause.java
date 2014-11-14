package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class WithACause extends ThrowableBaseChainableMatcher
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	WithACause(ThrowableBaseChainableMatcher obj)
	{
		super(obj);
	}
	
	//***************************************************************************
	// Implementation methods
	//***************************************************************************
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with a cause");
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		return t.getCause() != null;
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with no cause");
	}
	
}
