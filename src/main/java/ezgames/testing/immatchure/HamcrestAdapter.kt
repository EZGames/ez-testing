package ezgames.testing.immatchure

import org.hamcrest
import org.hamcrest.StringDescription

fun <T> hamcrest(original: hamcrest.Matcher<T>): Matcher<T>
{
    return LambdaMatcher {
        DefaultResult(!original.matches(it),
                original.describeTo(StringDescription()).toString(),
                original.describeMismatch(it, StringDescription()).toString())
    }
}