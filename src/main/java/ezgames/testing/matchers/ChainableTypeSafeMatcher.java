package ezgames.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

//TEST
/**
 * This Hamcrest Matcher is used to create Matchers that can be chained similarly
 * to AssertJ assertions. It uses the decorator pattern to do so.
 * <p>
 * In order to use this class, you create an abstract class that extends from
 * this one that doesn't implement any of these methods, but lists all the methods
 * required to do the different decorations.</p>
 * <p>
 * For example, if you want to design a chaining set of matchers for testing a
 * {@code Person} class that has {@code name} and {@code age} properties, this
 * abstract class would look something like this:</p>
 * <pre><code>
 * public abstract class ChainablePersonMatcher extends ChainableTypeSafeMatcher&lt;Person&gt; {
 * 
 *    protected ChainablePersonMatcher(ChainablePersonMatcher toBeDecorated) {
 *       super(toBeDecorated);
 *    }
 *    
 *    public ChainablePersonMatcher hasName(String expectedName) {
 *       return new NameChainablePersonMatcher(this, expectedName);
 *    }
 *    
 *    public ChainablePersonMatcher hasAge(int expectedAge) {
 *       return new AgeChainablePersonMatcher(this, expectedAge);
 *    }
 * }
 * </code></pre>
 * <p>
 * After that, you design your individual Matchers, {@code NameChainablePersonMatcher}
 * and {@code AgeChainablePersonMatcher}, to extend from this abstract class and
 * implement {@code chainDescribeTo()}, {@code chainMatches()}, and optionally
 * {@code chainDescribeMismatch()} the same way you normally would do {@code describeTo()},
 * {@code matchesSafely()}, and {@code describeMismatchSafely()}. Don't forget the
 * factory methods, though.</p>
 * <p>
 * Once that's done, you can use the set of Matchers as follows:<br>
 * {@code assertThat(person, hasName("Bob").hasAge(32));}</p>
 * <p>
 * This does break the decorator pattern a little, since it doesn't have a base
 * object to decorate. You could set one up, though. To do so, you would have to
 * make it so that the base matcher is the only one with a factory method, and
 * that it passes {@code null} into the {@code super} constructor. </p>
 * <p>
 * For more information on how to use this, check out the <a href="">blog post</a>
 * that led to its development.</p>
 * @param <T> the same T as in {@link org.hamcrest.TypeSafeMatcher TypeSafeMatcher}&lt;T&gt;
 * @author Jacob Zimmerman - sad2project
 */
public abstract class ChainableTypeSafeMatcher<T> extends TypeSafeMatcher<T>
{
	//***************************************************************************
	// Protected constructor
	//***************************************************************************
	protected ChainableTypeSafeMatcher(ChainableTypeSafeMatcher<? super T> decoratedMatcher)
	{
		this.decoratedMatcher = decoratedMatcher;
		this.thisMatches = true;
	}

	//***************************************************************************
	// Methods to implement
	//***************************************************************************
	/**
	 * Implemented the same as {@code describeTo()} would be, but this is a method 
	 * called by {@code ChainableTypeSafeMatcher}'s {@code describeTo()}
	 * @see #describeTo() 
	 */
	public abstract void chainDescribeTo(Description description);
	
	/**
	 * Implemented the same as {@code matchesSafely()} would be, but this is a method 
	 * called by {@code ChainableTypeSafeMatcher}'s {@code matchesSafely()}
	 * @see #matchesSafely()
	 */
	protected abstract boolean chainMatches(T item);
	
	/**
	 * Implemented the same as {@code describeMismatchSafely()} would be, but this
	 * is a method call by ChainableTypeSafeMatcher's describeMismatchSafely()
	 * <p>
	 * <b>Extension Note:</b>
	 * There is no need to try to implement a check of whether this specific
	 * decorated matcher made a match or not. When {@code describeMismatch()} is 
	 * called, it doesn't automatically call {@code chainDescribeMismatch()}. If
	 * {@code chainMatches()} returned {@code true}, then {@code describeMismatch()}
	 * will actually call {@code chainDescribeTo()}, not this method.</p>
	 * @see #describeMismatchSafely() 
	 */
	protected void chainDescribeMismatch(T item, Description mismatchDescription)
	{
		mismatchDescription.appendText("was ")
			.appendValue(item);
	}

	//***************************************************************************
	// Template methods
	//***************************************************************************
	/**
	 * Generates a description of the object. The description may be part of a 
	 * description of a larger object of which this is just a component, so it 
	 * should be worded appropriately.
	 * <p>
	 * <b>Extension Note:</b>
	 * This method calls the decorated matcher's {@code describeTo()} method (if 
	 * there is a decorated matcher) then calls the overridden 
	 * {@code chainDescribeTo()} to get the description for the implementation.</p>
	 */
	@Override
	public final void describeTo(Description description)
	{
		if(decoratedMatcher != null)
		{
			decoratedMatcher.describeTo(description);
			description.appendText("\n              ");
		}
		chainDescribeTo(description);
	}
	
	/**
	 * Evaluates the matcher for argument item.<p>
	 * The item will already have been checked for the specific type and will 
	 * never be null.</p>
	 * <p>
	 * <b>Extension Note:</b>
	 * This method calls {@code chainMatches()} then calls the decorated matcher's
	 * {@code matches()} method (if there is a decorated matcher).</p>
	 */
	@Override
	protected final boolean matchesSafely(T item)
	{
		thisMatches = chainMatches(item);
		
		if(decoratedMatcher == null || decoratedMatcher.matchesSafely(item))
			return thisMatches;
		else
			return false;
	}
	
	/**
	 * Generate a description of why the matcher has not accepted the item. The 
	 * description will be part of a larger description of why a matching failed,
	 * so it should be concise. This method assumes that matches(item) is false in
	 * at least one point of the decoration chain, but will not check this. It will,
	 * however check the result of the last call to {@code chainMatches()} (defaulted
	 * to {@code true} if it was never called). The item will already have been 
	 * checked for the specific type and will never be null.
	 * <p>
	 * <b>Extension Note:</b>
	 * This method is called if <i>any</i> of the matchers in the decoration chain
	 * fails to match. Therefore, when this method is called, it calls the decorated
	 * matcher's {@code describeMismatch()} then make a call to one of the
	 * overridden description methods. If {@code chainMatches()} returned 
	 * {@code true}, then this will call {@code chainDescribeTo()}. Otherwise, it
	 * calls {@code chainDescribeMismatch()}.</p>
	 */
	@Override
	protected final void describeMismatchSafely(T item, Description mismatchDescription)
	{
		//if applicable, we want to get the match/mismatch descriptions of all the
		// decorated matchers, too
		if(decoratedMatcher != null)
		{
			decoratedMatcher.describeMismatchSafely(item, mismatchDescription);
			mismatchDescription.appendText("\n             ");
		}
		
		if(thisMatches)
			chainDescribeTo(mismatchDescription); //explicitly state that this one matched
		else
			chainDescribeMismatch(item, mismatchDescription);		
		
		
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ChainableTypeSafeMatcher<? super T> decoratedMatcher;
	private boolean thisMatches;
}