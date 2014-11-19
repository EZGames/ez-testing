package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * This matcher is used to assert that whether making a certain function call will
 * not throw a specified {@code Throwable} type. You can also assert that NO
 * {@code Throwable}s are thrown. 
 * 
 * @author jzimmerman
 */
public class DoesNotThrow extends TypeSafeMatcher<Thrower>
{
	//***************************************************************************
	// Static factory methods
	//***************************************************************************
	/**
	 * Asserts that a certain function call with not throw the given {@code Throwable}
	 * type
	 * @param exToCheck - the type of {@code Throwable} this asserts isn't thrown
	 * @return new Matcher to do the assertion
	 */
	public static DoesNotThrow doesNotThrowA(Class<? extends Throwable> exToCheck)
	{
		return new DoesNotThrow(exToCheck);
	}
	
	/**
	 * @see #doesNotThrowA()
	 */
	public static DoesNotThrow doesNotThrowAn(Class<? extends Throwable> exToCheck)
	{
		return doesNotThrowA(exToCheck);
	}
	
	/**
	 * Asserts that a certain function call does not throw an {@code Throwable}s
	 * @return new Matcher to do the assertion
	 */
	public static DoesNotThrow doesNotThrowAnything()
	{
		return new DoesNotThrow(null);
	}
	
	//***************************************************************************
	// Implementation methods
	//**************************************************************************
	@Override
	public void describeTo(Description description)
	{
		if(exToCheck == null)
		{
			description.appendText("did not throw anything");
		}
		else
		{
			description.appendText("did not throw a(n) ")
				.appendText(exToCheck.getName());
		}
	}
	
	@Override
	protected boolean matchesSafely(Thrower item)
	{
		if(exToCheck == null)
			return doesntThrowAnything(item);
		else
			return doesntThrowSpecifiedType(item);
	}
	
	@Override
	protected void describeMismatchSafely(Thrower item, Description mismatchDescription)
	{
		mismatchDescription.appendText("threw a(n) ")
			.appendText(thrownEx.getClass().getName());
	}
	
	//***************************************************************************
	// Private Constructor
	//***************************************************************************
	private DoesNotThrow(Class<? extends Throwable> exToCheck)
	{
		this.exToCheck = exToCheck;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final Class<? extends Throwable> exToCheck;
	private Throwable thrownEx;
	
	//***************************************************************************
	// Private helper methods
	//***************************************************************************
	private boolean doesntThrowAnything(Thrower item)
	{
		try
		{
			item.run();
			return true;
		}
		catch(Throwable t)
		{
			thrownEx = t;
			return false;
		}
	}
	
	private boolean doesntThrowSpecifiedType(Thrower item)
	{
		try
		{
			item.run();
			return true;
		}
		catch(Throwable t)
		{
			thrownEx = t;
			return !exToCheck.isInstance(t);
		}
	}
}
