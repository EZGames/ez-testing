package ezgames.testing.matchers.exceptions;

import static ezgames.testing.matchers.exceptions.ThrowsA.throwsA;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;

public class ThrowsTester
{
	//***************************************************************************
	// Constant values
	//***************************************************************************
	public static final String goodMessage = "message";
	public static final String badMessage = "wrong";
	public static final Exception goodCause = new NullPointerException();
	public static final Exception badCause = new IndexOutOfBoundsException();
	public static final Class<? extends Throwable> expectedException = RuntimeException.class;
	public static final Class<? extends Throwable> expectedCause = NullPointerException.class;
	
	//***************************************************************************
	// Different methods to throw different specified Exceptions
	//***************************************************************************
	public static void noThrow() { return; }	
	public static void goodThrow() { throw new RuntimeException(); }
	public static void goodThrowGoodCause() { throw new RuntimeException(goodCause);}
	public static void goodThrowBadCause() { throw new RuntimeException(badCause); }
	public static void goodThrowGoodMessage() { throw new RuntimeException(goodMessage); }
	public static void goodThrowGoodMessageGoodCause() { throw new RuntimeException(goodMessage, goodCause); }
	public static void goodThrowGoodMessageBadCause() { throw new RuntimeException(goodMessage, badCause); }
	public static void goodThrowBadMessage() { throw new RuntimeException(badMessage); }
	public static void goodThrowBadMessageGoodCause() { throw new RuntimeException(badMessage, goodCause); }
	public static void goodThrowBadMessageBadCause() { throw new RuntimeException(badMessage, badCause); }
	public static void badThrow() throws IOException { throw new IOException(); }
	public static void badThrowGoodCause() throws IOException { throw new IOException(goodCause); }
	public static void badThrowBadCause() throws IOException { throw new IOException(badCause); }
	public static void badThrowGoodMessage() throws IOException { throw new IOException(goodMessage); }
	public static void badThrowGoodMessageGoodCause() throws IOException { throw new IOException(goodMessage, goodCause); }
	public static void badThrowGoodMessageBadCause() throws IOException { throw new IOException(goodMessage, badCause); }
	public static void badThrowBadMessage() throws IOException { throw new IOException(badMessage); }
	public static void badThrowBadMessageGoodCause() throws IOException { throw new IOException(badMessage, goodCause); }
	public static void badThrowBadMessageBadCause() throws IOException { throw new IOException(badMessage, badCause); }
	
	/**
	 * Runs through each of the methods that throw Exceptions and makes sure that
	 * the {@code Matcher} matches when it should and doesn't when it shouldn't
	 * @param shouldMatch - array of all the boolean values of whether the given
	 * {@code Matcher} should match with the varying thrown exceptions
	 * @param matcher - {@code Matcher} to check the matches against
	 */
	public static void runTests(boolean[] shouldMatch, ThrowableBaseChainableMatcher matcher)
	{
		assertMatch(false, ThrowsTester::noThrow, matcher, 0);
		assertMatch(shouldMatch[0], ThrowsTester::goodThrow, matcher, 1);
		assertMatch(shouldMatch[1], ThrowsTester::goodThrowGoodCause, matcher, 2);
		assertMatch(shouldMatch[2], ThrowsTester::goodThrowBadCause, matcher, 3);
		assertMatch(shouldMatch[3], ThrowsTester::goodThrowGoodMessage, matcher, 4);
		assertMatch(shouldMatch[4], ThrowsTester::goodThrowGoodMessageGoodCause, matcher, 5);
		assertMatch(shouldMatch[5], ThrowsTester::goodThrowGoodMessageBadCause, matcher, 6);
		assertMatch(shouldMatch[6], ThrowsTester::goodThrowBadMessage, matcher, 7);
		assertMatch(shouldMatch[7], ThrowsTester::goodThrowBadMessageGoodCause, matcher, 8);
		assertMatch(shouldMatch[8], ThrowsTester::goodThrowBadMessageBadCause, matcher, 9);		
		assertMatch(false, ThrowsTester::badThrow, matcher, 10);
		assertMatch(false, ThrowsTester::badThrowGoodCause, matcher, 11);
		assertMatch(false, ThrowsTester::badThrowBadCause, matcher, 12);
		assertMatch(false, ThrowsTester::badThrowGoodMessage, matcher, 13);
		assertMatch(false, ThrowsTester::badThrowGoodMessageGoodCause, matcher, 14);
		assertMatch(false, ThrowsTester::badThrowGoodMessageBadCause, matcher, 15);
		assertMatch(false, ThrowsTester::badThrowBadMessage, matcher, 16);
		assertMatch(false, ThrowsTester::badThrowBadMessageGoodCause, matcher, 17);
		assertMatch(false, ThrowsTester::badThrowBadMessageBadCause, matcher, 18);
	}
	
	/**
	 * Checks whether the {@code Thrower} matches/doesn't match with the 
	 * {@code Matcher}. Throws an {@code AssertionError} if the given boolean is
	 * an incorrect indicator of whether the two will match. 
	 * @param shouldMatch - whether the given {@ code Thrower} will match with the
	 * {@code Matcher}
	 * @param thrower - a {@code Thrower} to test whether it throws what is expected
	 * @param matcher - a {@code Matcher} for asserting whether the {@code Thrower}
	 * throws what it should
	 * @param assertionNumber - a simple identifier for figuring out which call is
	 * the one that threw the {@code AssertionError}
	 */
	public static void assertMatch(boolean shouldMatch, Thrower thrower, ThrowableBaseChainableMatcher matcher, int assertionNumber)
	{
		if(shouldMatch != matcher.matches(thrower))
		{
			Description expected = new StringDescription();
			matcher.describeTo(expected);
			
			Description actual = new StringDescription();
			matcher.describeMismatch(thrower, actual);
			
			throw new AssertionError(String.format("Match failed: assertion #%d with Matcher:\n   Expected: %s\n   Actual: %s", assertionNumber, expected, actual));
		}
	}
	
	@Test
	public void testThrows()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException);
		runTests(new boolean[]{true, true, true, true, true, true, true, true, true}, matcher);
	}
	
	@Test
	public void testThrowsWithACause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withACause();
		runTests(new boolean[]{false, true, true, false, true, true, false, true, true}, matcher);
	}
	
	@Test
	public void testThrowsWithCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withCause(expectedCause);
		runTests(new boolean[]{false, true, false, false, true, false, false, true, false}, matcher);
	}
	
	@Test
	public void testThrowsWithNoCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withNoCause();
		runTests(new boolean[]{true, false, false, true, false, false, true, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithAMessage()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withAMessage();
		runTests(new boolean[]{false, false, false, true, true, true, true, true, true}, matcher);
	}
	
	@Test
	public void testThrowsWithAMessageWithACause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withAMessage().withACause();
		runTests(new boolean[]{false, false, false, false, true, true, false, true, true}, matcher);
	}
	
	@Test
	public void testThrowsWithAMessageWithCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withAMessage().withCause(expectedCause);
		runTests(new boolean[]{false, false, false, false, true, false, false, true, false}, matcher);
	}
	
	@Test
	public void testThrowsWithAMessageWithNoCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withAMessage().withNoCause();
		runTests(new boolean[]{false, false, false, true, false, false, true, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithMessage()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withMessage(goodMessage);
		runTests(new boolean[]{false, false, false, true, true, true, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithMessageWithACause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withMessage(goodMessage).withACause();
		runTests(new boolean[]{false, false, false, false, true, true, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithMessageWithCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withMessage(goodMessage).withCause(expectedCause);
		runTests(new boolean[]{false, false, false, false, true, false, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithMessageWithNoCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withMessage(goodMessage).withNoCause();
		runTests(new boolean[]{false, false, false, true, false, false, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithNoMessage()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withNoMessage();
		runTests(new boolean[]{true, true, true, false, false, false, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithNoMessageWithACause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withNoMessage().withACause();
		runTests(new boolean[]{false, true, true, false, false, false, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithNoMessageWithCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withNoMessage().withCause(expectedCause);
		runTests(new boolean[]{false, true, false, false, false, false, false, false, false}, matcher);
	}
	
	@Test
	public void testThrowsWithNoMessageWithNoCause()
	{
		ThrowableBaseChainableMatcher matcher = throwsA(expectedException).withNoMessage().withNoCause();
		runTests(new boolean[]{true, false, false, false, false, false, false, false, false}, matcher);
	}
	
	@Test
	public void testSucceedsWithSubclassException()
	{
		assertThat(ThrowsTester::throwSubclassException, throwsA(RuntimeException.class));
	}
	
	public static void throwSubclassException()
	{
		throw new NullPointerException();
	}
}
