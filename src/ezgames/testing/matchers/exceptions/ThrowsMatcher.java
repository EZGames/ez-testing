package ezgames.testing.matchers.exceptions;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

abstract class ThrowsMatcher<X extends Throwable> extends TypeSafeMatcher<ThrowingRunnable>
{
	/**
	 * @return a new Matcher that decorates the current one to check whether the
	 * the thrown Exception has any message
	 */
	public final ThrowsMatcher<X> andHasAMessage()
	{
		return new HasAMessage<X>(this);
	}
	
	/**
	 * NOTE: If an exception has a cause, it WILL have a message.  If the message
	 * isn't set explicitly, exceptions implicitly add the qualified name of the
	 * cause class as the message.
	 * @return a new Matcher that decorates the current one to make sure the
	 * the thrown Exception has no message
	 */
	public final ThrowsMatcher<X> andHasNoMessage()
	{
		return new HasNoMessage<X>(this);
	}
	
	/**
	 * @return a new Matcher that decorates the current one to check whether the
	 * the thrown Exception has the given message
	 */
	public final ThrowsMatcher<X> andHasMessage(String message)
	{
		return new HasMessage<X>(this, message);
	}
	
	/**
	 * @return a new Matcher that decorates the current one to make sure the
	 * the thrown Exception has no cause
	 */
	public final ThrowsMatcher<X> andHasNoCause()
	{
		return new HasNoCause<X>(this);
	}
	
	/**
	 * @return a new Matcher that decorates the current one to check whether the
	 * the thrown Exception has any cause
	 */
	public final ThrowsMatcher<X> andHasACause()
	{
		return new HasACause<X>(this);
	}
	
	/**
	 * @return a new Matcher that decorates the current one to check whether the
	 * the thrown Exception has the given cause type
	 */
	public final ThrowsMatcher<X> andHasCause(Class<? extends Throwable> causeClass)
	{
		return new HasCause<X>(this, causeClass);
	}
	
	/**
	 * Until the innermost decorated class's {@code matchesSafely} method is
	 * called, this only returns null.  After the method is called, this returns 
	 * the {@link Throwable} that was caught in the in the {@code matchesSafely}
	 * method, or null if nothing was thrown.
	 * @return null, or the {@code Throwable} caught in the innermost class's
	 * {@code matchesSafely} method. 
	 */
	abstract protected Throwable getException();	
	@Override
	abstract public void describeTo(Description description);
	@Override
	abstract protected boolean matchesSafely(ThrowingRunnable item);	
	@Override
	abstract protected void describeMismatchSafely(ThrowingRunnable item, Description mismatchDescription);
}
