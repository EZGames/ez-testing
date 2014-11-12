package ezgames.testing.matchers.exceptions;

import static org.hamcrest.core.Is.is;
import static ezgames.testing.matchers.exceptions.Throws.throwsAn;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Test;
import ezgames.testing.matchers.exceptions.ThrowingRunnable;

public class ThrowsTest
{
	private Class<? extends Throwable> expectedCause = NullPointerException.class;
	private Class<? extends Throwable> expectedException = IllegalArgumentException.class;
	private String message = "default message";
	private ThrowsMatcher<? extends Throwable> throwsMatcher = throwsAn(expectedException);
	private ThrowsMatcher<? extends Throwable> throwsAndHasNoMessageMatcher = throwsAn(expectedException).andHasNoMessage();
	private ThrowsMatcher<? extends Throwable> throwsAndHasNoCauseMatcher = throwsAn(expectedException).andHasNoCause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasTheMessageMatcher = throwsAn(expectedException).andHasMessage(message);
	private ThrowsMatcher<? extends Throwable> throwsAndHasTheCauseMatcher = throwsAn(expectedException).andHasCause(expectedCause);
	private ThrowsMatcher<? extends Throwable> throwsAndHasAMessageMatcher = throwsAn(expectedException).andHasAMessage();
	private ThrowsMatcher<? extends Throwable> throwsAndHasACauseMatcher = throwsAn(expectedException).andHasACause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasNoMessageAndNoCauseMatcher = throwsAn(expectedException).andHasNoMessage().andHasNoCause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasNoMessageAndTheCauseMatcher = throwsAn(expectedException).andHasNoMessage().andHasCause(expectedCause);
	private ThrowsMatcher<? extends Throwable> throwsAndHasNoMessageAndACauseMatcher = throwsAn(expectedException).andHasNoMessage().andHasACause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasTheMessageAndNoCauseMatcher = throwsAn(expectedException).andHasMessage(message).andHasNoCause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasTheMessageAndTheCauseMatcher = throwsAn(expectedException).andHasMessage(message).andHasCause(expectedCause);
	private ThrowsMatcher<? extends Throwable> throwsAndHasTheMessageAndACauseMatcher = throwsAn(expectedException).andHasMessage(message).andHasACause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasAMessageAndNoCauseMatcher = throwsAn(expectedException).andHasAMessage().andHasNoCause();
	private ThrowsMatcher<? extends Throwable> throwsAndHasAMessageAndTheCauseMatcher = throwsAn(expectedException).andHasAMessage().andHasCause(expectedCause);
	private ThrowsMatcher<? extends Throwable> throwsAndHasAMessageAndACauseMatcher = throwsAn(expectedException).andHasAMessage().andHasACause();
	
	//***************************************************************************
	// Where the assertions really happen
	//***************************************************************************
	private void usingThrowingRunnableThatDoesntThrowAnything(ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		ThrowingRunnable runnable = new ThrowingRunnable() { public void run() throws Throwable { } };
		
		using(runnable, matcher, whetherTheyShouldMatch);
	}
	
	private void usingThrowingRunnableWithNoMessageOrCause(ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		ThrowingRunnable runnable = new ThrowingRunnable() { public void run() throws Throwable { throw new IllegalArgumentException(); } };
		
		using(runnable, matcher, whetherTheyShouldMatch);
	}
	
	private void usingThrowingRunnableWithJustAMessage(ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		ThrowingRunnable runnable = new ThrowingRunnable() { public void run() throws Throwable { throw new IllegalArgumentException(message); } };
		
		using(runnable, matcher, whetherTheyShouldMatch);
	}
	
	private void usingThrowingRunnableWithJustACause(ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		ThrowingRunnable runnable = new ThrowingRunnable() { public void run() throws Throwable { throw new IllegalArgumentException(new NullPointerException()); } };
		
		using(runnable, matcher, whetherTheyShouldMatch);
	}
	
	private void usingThrowingRunnableWithAMessageAndACause(ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		ThrowingRunnable runnable = new ThrowingRunnable() { public void run() throws Throwable { throw new IllegalArgumentException(message, new NullPointerException()); } };
		
		using(runnable, matcher, whetherTheyShouldMatch);
	}
	
	private void using(ThrowingRunnable runnable, ThrowsMatcher<?> matcher, boolean whetherTheyShouldMatch)
	{
		boolean matches = matcher.matchesSafely(runnable);
		
		assertThat(matches, is(whetherTheyShouldMatch));
	}
	
	//***************************************************************************
	// Tests
	//***************************************************************************
	
	// ***** Testing throwsMatcher **********************************************
	@Test
	public void testThrowsMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsMatcher, true);
		usingThrowingRunnableWithJustAMessage(throwsMatcher, true);
		usingThrowingRunnableWithJustACause(throwsMatcher, true);
		usingThrowingRunnableWithAMessageAndACause(throwsMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasNoMessageMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasNoMessageMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasNoMessageMatcher, true);
		usingThrowingRunnableWithJustAMessage(throwsAndHasNoMessageMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasNoMessageMatcher, false); //causes create a message
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasNoMessageMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasNoCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasNoCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasNoCauseMatcher, true);
		usingThrowingRunnableWithJustAMessage(throwsAndHasNoCauseMatcher, true);
		usingThrowingRunnableWithJustACause(throwsAndHasNoCauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasNoCauseMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasTheMessageMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasTheMessageMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasTheMessageMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasTheMessageMatcher, true);
		usingThrowingRunnableWithJustACause(throwsAndHasTheMessageMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasTheMessageMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasTheCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasTheCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasTheCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasTheCauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasTheCauseMatcher, true);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasTheCauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasAMessageMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasAMessageMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasAMessageMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasAMessageMatcher, true);
		usingThrowingRunnableWithJustACause(throwsAndHasAMessageMatcher, true);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasAMessageMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasACauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasACauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasACauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasACauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasACauseMatcher, true);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasACauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasNoMessageAndNoCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasNoMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasNoMessageAndNoCauseMatcher, true);
		usingThrowingRunnableWithJustAMessage(throwsAndHasNoMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasNoMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasNoMessageAndNoCauseMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasNoMessageAndTheCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasNoMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasNoMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasNoMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasNoMessageAndTheCauseMatcher, false); //causes create a message
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasNoMessageAndTheCauseMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasNoMessageAndACauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasNoMessageAndACauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasNoMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasNoMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasNoMessageAndACauseMatcher, false); //causes create a message
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasNoMessageAndACauseMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasTheMessageAndNoCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasTheMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasTheMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasTheMessageAndNoCauseMatcher, true);
		usingThrowingRunnableWithJustACause(throwsAndHasTheMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasTheMessageAndNoCauseMatcher, false);
	}
	
	@Test
	public void testThrowsAndHasTheMessageAndTheCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasTheMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasTheMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasTheMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasTheMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasTheMessageAndTheCauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasTheMessageAndACauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasTheMessageAndACauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasTheMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasTheMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasTheMessageAndACauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasTheMessageAndACauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasAMessageAndACauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasAMessageAndACauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasAMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasAMessageAndACauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasAMessageAndACauseMatcher, true); //causes create a message
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasAMessageAndACauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasAMessageAndTheCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasAMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasAMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasAMessageAndTheCauseMatcher, false);
		usingThrowingRunnableWithJustACause(throwsAndHasAMessageAndTheCauseMatcher, true); //causes create a message
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasAMessageAndTheCauseMatcher, true);
	}
	
	@Test
	public void testThrowsAndHasAMessageAndNoCauseMatcher()
	{
		usingThrowingRunnableThatDoesntThrowAnything(throwsAndHasAMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithNoMessageOrCause(throwsAndHasAMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithJustAMessage(throwsAndHasAMessageAndNoCauseMatcher, true);
		usingThrowingRunnableWithJustACause(throwsAndHasAMessageAndNoCauseMatcher, false);
		usingThrowingRunnableWithAMessageAndACause(throwsAndHasAMessageAndNoCauseMatcher, false);
	}
}
