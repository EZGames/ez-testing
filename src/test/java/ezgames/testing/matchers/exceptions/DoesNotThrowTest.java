package ezgames.testing.matchers.exceptions;

import static ezgames.testing.matchers.exceptions.DoesNotThrow.*;
import java.io.IOException;
import org.hamcrest.Matcher;
import org.junit.Test;

public class DoesNotThrowTest
{
	public static void assertMatch(String errMessage, Thrower thrower, Matcher<Thrower> matcher)
	{
		if(!matcher.matches(thrower))
		{
			throw new AssertionError("Failed to match: " + errMessage);
		}
	}
	
	public static void assertMismatch(String errMessage, Thrower thrower, Matcher<Thrower> matcher)
	{
		if(matcher.matches(thrower))
		{
			throw new AssertionError("Failed to not match" + errMessage);
		}
	}
	
	public static void throwsAnExToAvoid() { throw new RuntimeException(); }	
	public static void throwsAnOkayEx() throws IOException { throw new IOException(); }	
	public static void throwsNothing() { }
	
	public static final Class<? extends Throwable> exToAvoid = RuntimeException.class; 
	
	@Test
	public void testDoesNotThrowA1()
	{
		assertMismatch("Throwing an exception to avoid; testing that it's not thrown", DoesNotThrowTest::throwsAnExToAvoid, doesNotThrowAn(exToAvoid));
	}
	
	@Test
	public void testDoesNotThrowA2()
	{
		assertMatch("Throwing an okay exception; testing that another isn't thrown", DoesNotThrowTest::throwsAnOkayEx, doesNotThrowAn(exToAvoid));
	}
	
	@Test
	public void testDoesNotThrowA3()
	{
		assertMatch("Throwing nothing, testing that another isn't thrown", DoesNotThrowTest::throwsNothing, doesNotThrowAn(exToAvoid));
	}
	
	@Test
	public void testDoesNotThrowAnything1()
	{
		assertMismatch("Throwing an exception to avoid; testing that nothing is thrown", DoesNotThrowTest::throwsAnExToAvoid, doesNotThrowAnything());
	}
	
	@Test
	public void testDoesNotThrowAnythign2()
	{
		assertMismatch("Throwing the 'okay' exception; testing that nothing is thrown", DoesNotThrowTest::throwsAnOkayEx, doesNotThrowAnything());
	}
	
	@Test
	public void testDoesNotThrowAnything3()
	{
		assertMatch("Throwing nothing; testing that nothing is thrown", DoesNotThrowTest::throwsNothing, doesNotThrowAnything());
	}
}
