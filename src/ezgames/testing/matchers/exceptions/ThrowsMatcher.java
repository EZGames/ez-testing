package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;
import ezgames.testing.matchers.ChainableTypeSafeMatcher;

public abstract class ThrowsMatcher extends ChainableTypeSafeMatcher<ThrowingRunnable>
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	protected ThrowsMatcher(ThrowsMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
	//***************************************************************************
	// Chaining methods
	//***************************************************************************
	public ThrowsMatcher hasAMessage()
	{
		return new HasAMessage(this);
	}
	
	public ThrowsMatcher hasACause()
	{
		return new HasACause(this);
	}
	
	//***************************************************************************
	// Template methods
	//***************************************************************************
	@Override
	protected final boolean chainMatches(ThrowingRunnable item)
	{
		try
		{
			item.run();
			return false;
		}
		catch(Throwable t)
		{
			thrown = t;
			return throwMatches(t);
		}
	}
	
	@Override
	protected final void chainDescribeMismatch(ThrowingRunnable item, Description mismatchDescription)
	{
		throwDescribeMismatch(thrown, mismatchDescription);
	}
	
	//***************************************************************************
	// Abstract methods
	//***************************************************************************
	protected abstract boolean throwMatches(Throwable t);
	protected abstract void throwDescribeMismatch(Throwable t, Description mismatchDescription);
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private Throwable thrown;
}
