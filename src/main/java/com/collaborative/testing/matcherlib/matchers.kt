package com.collaborative.testing.matcherlib

import org.hamcrest

interface Matcher<in T>
{
    companion object
    {
        fun <T> fromHamcrest(original: hamcrest.Matcher<T>): Matcher<T>
        {
            return hamcrest(original)
        }
    }
    fun matches(actual: T):Result
}

interface Result
{
    val failed: Boolean
    val expected: String
    val onFailure: String
    val actual: String
}

class DefaultResult(override val failed: Boolean,
                    expected: String,
                    onFailure: String): Result
{
    override val actual: String
    override val expected: String
    override val onFailure: String
    init
    {
        this.expected = tabIt(expected)
        this.onFailure = tabIt(onFailure)
        actual = if(failed) tabIt(onFailure) else tabIt(expected)
    }

    private fun tabIt(output: String) =
        if(output.startsWith("\t"))
            output
        else
            "\t" + output
}