package ezgames.testing.matchers;

import java.util.function.Function;
import java.util.function.Predicate;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
//TEST
/**
 * {@code LambdaMatcher} is a class for quickly making a one-off Matcher using
 * lambdas instead of anonymous classes. Also, this class is used internally by
 * {@code LambdaAssert} to allow for more fluent creation of assertions and matchers
 * using lambdas.
 * <p>
 * This class extends {@code TypeSafeMatcher}. There is no {@code BaseMatcher}
 * version.</p>
 * @author Jacob Zimmerman - sad2project
 * @see ezgames.testing.assertions.LambdaAssert LambdaAssert
 * @param <T>
 */
public class LambdaMatcher<T> extends TypeSafeMatcher<T>
{
	/**
	 * Constructor for building a matcher out of lambdas.
	 * @param description - the description appended in {@code describeTo()]
	 * @param matcher - the {@code Predicate} used to determine whether the item
	 * matches with the matcher or not
	 * @param mismatchDescription - the {@code Function} used to create the output
	 * to be appended in {@code describeMismatch()}
	 */
	public LambdaMatcher(String description, Predicate<? super T> matcher, Function<T, String> mismatchDescription)
	{
		this.description = description;
		this.matcher = matcher;
		this.mismatchDescription = mismatchDescription;
	}
	
	/**
	 * Constructor for building a matcher out of lambdas with a default mismatch
	 * description of {@code "was " + item.toString()}
	 * @param description - the description appended in {@code describeTo()}
	 * @param matcher - the {@code Predicate} used to determine whether the item
	 * matches with the matcher or not
	 */
	public LambdaMatcher(String description, Predicate<? super T> matcher)
	{
		this(description, matcher, item -> "was " + item.toString());
	}

	@Override
	public void describeTo(Description description)
	{
		description.appendText(this.description);
	}

	@Override
	protected boolean matchesSafely(T item)
	{
		return matcher.test(item);
	}
	
	@Override
	protected void describeMismatchSafely(T item, Description mismatchDescription)
	{
		mismatchDescription.appendText(this.mismatchDescription.apply(item));
	}
	
	private final String description;
	private final Predicate<? super T> matcher;
	private final Function<T, String> mismatchDescription;
}