@file:JvmName("Chaining")

package com.collaborative.testing.matcherlib

internal class MatcherChainer<T>(private val subMatcher: Matcher<T>, private val thisMatcher: Matcher<T>): Matcher<T>
{
    override fun matches(actual: T): Result
    {
        return subMatcher.matches(actual).chainResult(thisMatcher.matches(actual))
    }
}

fun <T> chain(first: Matcher<T>, second: Matcher<T>, vararg matchers: Matcher<in T>): Matcher<T>
{
    var compound = first.and(second)

    for(matcher in matchers)
    {
        compound = compound.and(matcher)
    }

    return compound
}

fun <T> Matcher<T>.and(next: Matcher<T>) = MatcherChainer<T>(this, next)

private fun Result.chainResult(after: Result): Result
{
    val failed = this.failed || after.failed
    val expected = this.expected + "\n" + after.expected
    val actual = this.actual + "\n" + after.actual
    return Result(failed, expected, actual)
}