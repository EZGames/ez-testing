package ezgames.testing.assertions;

import java.util.function.Function;
import java.util.function.Predicate;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import ezgames.testing.matchers.LambdaMatcher;

/**
 * {@code LambdaAssert} is used to create one-off Matchers using lambdas in the
 * assert statement. You could also create {@code LambdaMatcher}s and use those,
 * but this is supposed to be a little more fluent.</p>
 * For example, you can write<br>
 * <code>assertThat("cat", "does not contain \"dog\"", item -> !item.contains("dog"));</code><br>
 * instead of<br>
 * <code>assertThat("cat", new LambdaMatcher<>("does not contain \"dog\"", item -> !item.contains("dog")));</code>
 * 
 * @see LambdaMatcher
 * 
 * @author Jacob Zimmerman - sad2project
 */
public class LambdaAssert
{
	public static <T> void assertThat(T actual, String description, Predicate<? super T> matcher)
	{
		Matcher<T> lambdaMatcher = new LambdaMatcher<T>(description, matcher);
		MatcherAssert.assertThat(actual, lambdaMatcher);
	}
	
	public static <T> void assertThat(T actual, String description, Predicate<? super T> matcher, Function<T, String> mismatchDescription)
	{
		Matcher<T> lambdaMatcher = new LambdaMatcher<>(description, matcher, mismatchDescription);
		MatcherAssert.assertThat(actual, lambdaMatcher);
	}
}