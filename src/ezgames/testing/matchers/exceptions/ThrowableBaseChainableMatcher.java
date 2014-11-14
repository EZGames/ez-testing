package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;
import ezgames.testing.matchers.ChainableTypeSafeMatcher;

public abstract class ThrowableBaseChainableMatcher extends ChainableTypeSafeMatcher<Thrower>
{
	//***************************************************************************
	// Constructor
	//***************************************************************************
	protected ThrowableBaseChainableMatcher(ThrowableBaseChainableMatcher decoratedMatcher)
	{
		super(decoratedMatcher);
	}
	
	//***************************************************************************
	// Chaining methods
	//***************************************************************************
	/**
	 * Chains on a matcher that asserts that the thrown object has a message.
	 * NOTE: When you add a cause to an Exception without setting a message, it
	 * sets the message to be the fully-qualified name of the Exception type of
	 * the cause. This matcher counts such messages as having no message
	 */
	public ThrowableBaseChainableMatcher withAMessage()
	{
		return new WithAMessage(this);
	}
	
	/**
	 * Chains on a matcher that asserts that the thrown object has a cause
	 */
	public ThrowableBaseChainableMatcher withACause()
	{
		return new WithACause(this);
	}
	
	/**
	 * Chains on a matcher that asserts that the thrown object has the given message
	 * @param expectedMessage - the message that the thrown object is expected
	 * to have
	 */
	public ThrowableBaseChainableMatcher withMessage(String expectedMessage)
	{
		return new WithMessage(this, expectedMessage);
	}
	
	/**
	 * Chains on a matcher that asserts that the thrown object has a cause of the
	 * given type
	 * @param expectedCauseType - the type of cause that the thrown object is 
	 * expected to have
	 */
	public ThrowableBaseChainableMatcher withCause(Class<? extends Throwable> expectedCauseType)
	{
		return new WithCause(this, expectedCauseType);
	}
	
	/**
	 * Chains on a matcher that asserts that the thrown object has no message
	 * NOTE: When you add a cause to an Exception without setting a message, it
	 * sets the message to be the fully-qualified name of the Exception type of
	 * the cause. This matcher counts such messages as having no message
	 */
	public ThrowableBaseChainableMatcher withNoMessage()
	{
		return new WithNoMessage(this);
	}
	
	/**
	 * Chains on a matcher that asserts that the thrown object has no cause
	 */
	public ThrowableBaseChainableMatcher withNoCause()
	{
		return new WithNoCause(this);
	}
	
	//***************************************************************************
	// Template methods
	//***************************************************************************
	@Override
	protected final boolean chainMatches(Thrower item)
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
	protected final void chainDescribeMismatch(Thrower item, Description mismatchDescription)
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
