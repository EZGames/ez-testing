package ezgames.testing.matchers.numbers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsBetween extends TypeSafeMatcher<Integer>
{
	//***************************************************************************
	// Static factory methods
	//***************************************************************************
	public static IsBetween between(int low, int high)
	{
		return new IsBetween(low, high);
	}
	
	//***************************************************************************
	// Other public static methods
	//***************************************************************************
	/**
	 * To be used with the second number in the static factory for more fluent use.<br>
	 * i.e. <code>assertThat(1, is(between(0, and(2));</code>
	 * @return
	 */
	public static int and(int num)
	{
		return num;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("was between ");
		description.appendValue(min);
		description.appendText(" and ");
		description.appendValue(max);
	}

	@Override
	protected boolean matchesSafely(Integer item)
	{
		return item >= min && item <= max;
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(Integer item, Description mismatchDescription) 
	{
		mismatchDescription.appendText(item + "was not between " + min + " and " + max);
   }
	
	//***************************************************************************
	// Private constructors
	//***************************************************************************
	private IsBetween(int low, int high)
	{
		min = (low < high ? low : high);
		max = (high > low ? high : low);
	}
	
	private int min;
	private int max;
}
