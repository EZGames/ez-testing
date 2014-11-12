package ezgames.testing.matchers.exceptions2;

import org.hamcrest.Description;
import ezgames.testing.matchers.exceptions.ThrowingRunnable;

public class HasACause extends ThrowsMatcher
{
	
	HasACause(ThrowsMatcher obj)
	{
		super(obj);
	}
	
	@Override
	public void chainDescribeTo(Description description)
	{
		description.appendText("with a cause");
	}
	
	@Override
	protected boolean chainMatches(ThrowingRunnable item)
	{
		try
		{
			item.run();
			return false;
		}
		catch(Throwable t)
		{
			return t.getCause() != null;
		}
	}
	
	@Override
	protected void chainDescribeMismatch(ThrowingRunnable item, Description mismatchDescription)
	{
		mismatchDescription.appendText("with no cause");
	}
	
}
