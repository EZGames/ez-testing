package com.collaborative.testing.matcherlib

import org.hamcrest

interface Matcher<in T>
{
    companion object
    {
        fun <T> fromHamcrest(original: hamcrest.Matcher<T>): Matcher<T>
        {
            return HamcrestAdapter(original)
        }
    }
    fun matches(actual: T):Result
}

class Result(val failed: Boolean, expected: String, failure: String)
{
    val actual: String
    val expected: String
    init
    {
        this.expected = tabIt(expected)
        this.actual = if(failed) tabIt(failure) else tabIt(expected)
    }

    private fun tabIt(output: String) =
        if(output.startsWith("\t"))
            output
        else
            "\t" + output
}

