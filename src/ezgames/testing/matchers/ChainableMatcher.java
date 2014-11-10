package ezgames.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


//DOC TEST
/**
 * This Hamcrest Matcher is used to create Matchers that can be chained similarly
 * to AssertJ assertions.
 * <p>
 * 
 * @param <T> the same T as in {@link org.hamcrest.TypeSafeMatcher TypeSafeMatcher}&lt;T&gt;
 */
public abstract class ChainableMatcher<T> extends TypeSafeMatcher<T>
{
	//***************************************************************************
	// Protected constructor
	//***************************************************************************
	protected ChainableMatcher(ChainableMatcher<? super T> decoratedMatcher)
	{
		this.decoratedMatcher = decoratedMatcher;
		this.thisMatches = true;
	}

	//***************************************************************************
	// Methods to implement
	//***************************************************************************
	@Override
	public abstract void describeTo(Description description);
	
	/**
	 * Implemented the same as matchesSafely() would be, but this is a method 
	 * called by ChainingMatcher's matchesSafely()
	 * @see #matchesSafely()
	 */
	protected abstract boolean chainMatches(T item);
	
	/**
	 * Implemented the same as describeMismatchSafely() would be, but this is a
	 * method call by ChainingMatcher's
	 * @see #describeMismatchSafely() 
	 */
	protected abstract void chainDescribeMismatch(T item, Description mismatchDescription);

	//***************************************************************************
	// Template methods
	//***************************************************************************
	@Override
	protected final boolean matchesSafely(T item)
	{
		thisMatches = chainMatches(item);
		
		if(decoratedMatcher != null && decoratedMatcher.matchesSafely(item))
			return thisMatches;
		else
			return false;
	}
	
	@Override
	protected final void describeMismatchSafely(T item, Description mismatchDescription)
	{
		if(thisMatches)
			describeTo(mismatchDescription); //explicitly state that this one matched
		else
			chainDescribeMismatch(item, mismatchDescription);
		
		mismatchDescription.appendText("\n");
		
		//if applicable, we want to get the match/mismatch descriptions of all the
		// decorated matchers, too
		if(decoratedMatcher != null)
			decoratedMatcher.describeMismatchSafely(item, mismatchDescription);
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ChainableMatcher<? super T> decoratedMatcher;
	private boolean thisMatches;
}
