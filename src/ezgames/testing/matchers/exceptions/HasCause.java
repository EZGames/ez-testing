package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class HasCause extends ThrowsMatcher
{
	
	protected HasCause(ThrowsMatcher decoratedMatcher, Class<? extends Throwable> expectedCause)
	{
		super(decoratedMatcher);
		this.expectedCause = expectedCause;
	}

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
	
	private Class<?> expectedCause;
}