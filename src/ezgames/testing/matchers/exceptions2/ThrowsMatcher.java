package ezgames.testing.matchers.exceptions2;

import ezgames.testing.matchers.ChainableTypeSafeMatcher;
import ezgames.testing.matchers.exceptions.ThrowingRunnable;

public abstract class ThrowsMatcher extends ChainableTypeSafeMatcher<ThrowingRunnable>
{
	protected ThrowsMatcher(ThrowsMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
			
	public ThrowsMatcher hasAMessage()
	{
		return new HasAMessage(this);
	}
	
	public ThrowsMatcher hasACause()
	{
		return new HasACause(this);
	}	
}
