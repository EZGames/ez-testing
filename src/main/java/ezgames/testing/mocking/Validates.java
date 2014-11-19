package ezgames.testing.mocking;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsNot;

public class Validates extends TypeSafeMatcher<Validatable>
{
	//***************************************************************************
	// Static Factory Methods
	//***************************************************************************
	public static Validates validates()
	{
		return new Validates();
	}
	
	public static Matcher<Validatable> doesNotValidate()
	{
		return new IsNot<>(new Validates());
	}
	
	@Override
	public void describeTo(Description description)
	{
		description.appendText("validated");
	}

	@Override
	protected boolean matchesSafely(Validatable item)
	{
		return item.validate();
	}

	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(Validatable item, Description mismatchDescription) 
	{
		mismatchDescription.appendText(item.toString() + " did not validate");
   }
}
