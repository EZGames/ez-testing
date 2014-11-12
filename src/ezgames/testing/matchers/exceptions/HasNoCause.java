package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class HasNoCause extends ThrowsMatcher
{
	protected HasNoCause(ThrowsMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
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
	
	private Throwable cause;
}