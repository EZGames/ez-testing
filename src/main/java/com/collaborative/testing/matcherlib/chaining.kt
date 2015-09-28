@file:JvmName("Chaining")

package com.collaborative.testing.matcherlib

fun <T> chain(first: Matcher<T>, second: Matcher<T>, vararg matchers: Matcher<in T>): Matcher<T>
{
    var compound = first.and(second)

    for(matcher in matchers)
    {
        compound = compound.and(matcher)
    }

    return compound
}

fun <T> Matcher<T>.and(next: Matcher<T>) =
        LambdaMatcher<T>({ ChainedResult(this.matches(it), next.matches(it)) })

// Hidden [helper] functions and classes
private class ChainedResult(val wrapped: Result, val next: Result): Result
{
    override val failed: Boolean = wrapped.failed || next.failed
    override val expected: String = wrapped.expected + "\n" + next.expected
    override val onFailure: String = wrapped.onFailure + "\n" + next.onFailure
    override val actual: String
        get() = wrapped.actual + "\n" + next.actual
}