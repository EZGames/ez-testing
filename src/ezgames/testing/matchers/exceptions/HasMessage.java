package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;

public class HasMessage extends ThrowsMatcher
{
	
	protected HasMessage(ThrowsMatcher decoratedMatcher, String expectedMessage)
	{
		super(decoratedMatcher);
		this.expectedMessage = expectedMessage;
	}

	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with message \"")
			.appendText(expectedMessage)
			.appendText("\"");
	}
	
	@Override
	protected boolean throwMatches(Throwable t)
	{
		return expectedMessage.equals(t.getMessage());
	}
	
	@Override
	protected void throwDescribeMismatch(Throwable t, Description mismatchDescription)
	{
		if(t.getMessage() == null)
		{
			mismatchDescription.appendText("with no message");
		}
		else
		{
			mismatchDescription.appendText("with message \"")
				.appendText(t.getMessage())
				.appendText("\"");
		}
	}
	
	private String expectedMessage;
}