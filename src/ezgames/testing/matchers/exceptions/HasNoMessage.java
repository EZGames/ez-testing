package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class HasNoMessage extends ThrowsMatcher
{
	protected HasNoMessage(ThrowsMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
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
	
	private boolean messageIsNameOfCause(Throwable t)
	{
		Throwable cause = t.getCause();
		if(cause == null)
			return false;
		return cause.getClass().getName().equals(t.getMessage());
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		mismatchDescription.appendText("with message \"")
			.appendText(t.getMessage())
			.appendText("\"");
	}	
}