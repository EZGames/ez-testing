@file:JvmName("Chaining")

package ezgames.testing.newcrest

abstract class ChainableMatcher<in T>(private val subMatcher: Matcher<T>?):Matcher<T>
{
    override final fun matches(actual: T): Result {
        if(subMatcher == null)
            return chainedMatch(actual)
        else
            return subMatcher.matches(actual).chainResult(chainedMatch(actual))
    }

    abstract protected fun chainedMatch(actual: T): Result
}

class MatcherChainer<in T>(subMatcher: Matcher<T>, private val thisMatcher: Matcher<T>)
                           :ChainableMatcher<T>(subMatcher)
{
    override fun chainedMatch(actual: T) = thisMatcher.matches(actual)
}

fun <T> Matcher<T>.and(next: () -> Matcher<T>) = MatcherChainer<T>(this, next())

fun Result.chainResult(after: Result): Result
{
    val failed = this.failed || after.failed
    val expected = this.expected + "\n\t" + after.expected
    val actual = this.actual + "\n\t" + after.actual
    return Result(failed, expected, actual)
}