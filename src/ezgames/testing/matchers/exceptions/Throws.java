package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;
import org.hamcrest.Factory;

/**
 * {@code Throws} is a matcher that checks whether the given {@link Throwable}
 * type is thrown within the block of code given in the first part of the
 * {@code assertThat()} method.
 * @param <X> the Throwable type being checked for
 */
public class Throws<X extends Throwable> extends ThrowsMatcher<X>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	/**
	 * Static factory method for creating a {@code Throws} Matcher.
	 * @param ex - a {@code Throwable} class that the {@code ThrowingRunnable}
	 * matched up with this Matcher is expected to throw
	 * @return a new {@code Throws} object
	 */
	@Factory
	public static <X extends Throwable> Throws<X> throwsA(Class<X> ex)
	{
		return new Throws<X>(ex);
	}
	
	/**
	 * Static factory method for creating a {@code Throws} Matcher.
	 * @param ex - a {@code Throwable} class that the {@code ThrowingRunnable}
	 * matched up with this Matcher is expected to throw
	 * @return a new {@code Throws} object
	 */
	@Factory
	public static <X extends Throwable> Throws<X> throwsAn(Class<X> ex)
	{
		return throwsA(ex);
	}
	
	/**
	 * Static factory method for creating a {@code Throws} Matcher.
	 * <p>
	 * Can be used with a normal import (rather than a static one):</p>
	 * {@code assertThat(() -> methodThatThrows(), Throws.a(NullPointerException.class));
	 * @param ex - a {@code Throwable} class that the {@code ThrowingRunnable}
	 * matched up with this Matcher is expected to throw
	 * @return a new {@code Throws} object
	 */
	@Factory
	public static <X extends Throwable> Throws<X> a(Class<X> ex)
	{
		return throwsA(ex);
	}
	
	/**
	 * Static factory method for creating a {@code Throws} Matcher.
	 * <p>
	 * Can be used with a normal import (rather than a static one):</p>
	 * {@code assertThat(() -> methodThatThrows(), Throws.an(IllegalStateException.class));
	 * @param ex - a {@code Throwable} class that the {@code ThrowingRunnable}
	 * matched up with this Matcher is expected to throw
	 * @return a new {@code Throws} object
	 */
	@Factory
	public static <X extends Throwable> Throws<X> an(Class<X> ex)
	{
		return throwsA(ex);
	}
	
	//**************************************************************************
	// Matcher methods
	//**************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("threw a ")
			.appendText(exType.getSimpleName())
			.appendText(", as expected.");
	}

	@Override
	protected boolean matchesSafely(ThrowingRunnable item)
	{
		try
		{
			item.run();
		}
		catch(Throwable ex)
		{
			actualException = ex;
			if(exType.isInstance(ex))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	@Override
	protected void describeMismatchSafely(ThrowingRunnable item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("threw a ")
		   .appendText(actualException.getClass().getName())
		   .appendText(" instead of a ")
		   .appendText(exType.getName());
   }
	
	@Override
	protected Throwable getException()
	{
		return actualException;
	}
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private Class<X> exType;
	private Throwable actualException;
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private Throws(Class<X> ex)
	{
		exType = ex;
	}	
}
