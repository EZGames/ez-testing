package com.collaborative.testing.matcherlib

internal class LambdaMatcher<T> (val lambda: (in T) -> Result): Matcher<T> {
    override fun matches(actual: T): Result {
        return lambda(actual)
    }
}