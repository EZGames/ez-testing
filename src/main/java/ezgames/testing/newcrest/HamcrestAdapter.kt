package ezgames.testing.newcrest

import org.hamcrest.StringDescription

class HamcrestAdapter<in T>(private val original: org.hamcrest.Matcher<T>): Matcher<T>
{
    override fun matches(actual: T): Result
    {
        val fails = !original.matches(actual)
        return Result(fails,
                original.describeTo(StringDescription()).toString(),
                original.describeMismatch(actual, StringDescription()).toString())
    }
}