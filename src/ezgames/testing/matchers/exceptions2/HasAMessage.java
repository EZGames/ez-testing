package ezgames.testing.matchers.exceptions2;

import org.hamcrest.Description;
import ezgames.testing.matchers.exceptions.ThrowingRunnable;

public class HasAMessage extends ThrowsMatcher
{
	HasAMessage(ThrowsMatcher obj)
	{
		super(obj);
	}
	
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with a message");
	}
	
	@Override
	protected boolean chainMatches(ThrowingRunnable item)
	{
		try
		{
			item.run();
			return false;
		}
		catch (Throwable t)
		{
			String message = t.getMessage();
			return message != null && !message.isEmpty() && messageIsntNameOfCause(t);
		}
	}
	
	private boolean messageIsntNameOfCause(Throwable t)
	{
		Throwable cause = t.getCause();
		if(cause == null)
			return true;
		return !cause.getClass().getName().equals(t.getMessage());
	}
	
	@Override
	protected void chainDescribeMismatch(ThrowingRunnable item, Description mismatchDescription)
	{
		mismatchDescription.appendText("with no message");
	}
	
}