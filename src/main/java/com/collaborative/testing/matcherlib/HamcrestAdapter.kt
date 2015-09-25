package com.collaborative.testing.matcherlib

import org.hamcrest
import org.hamcrest.StringDescription

class HamcrestAdapter<T>(private val original: hamcrest.Matcher<T>): Matcher<T>
{
    override fun matches(actual: T): Result
    {
        val fails = !original.matches(actual)
        return Result(fails,
                original.describeTo(StringDescription()).toString(),
                original.describeMismatch(actual, StringDescription()).toString())
    }
}