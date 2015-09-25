@file:JvmName("Asserts")

package com.collaborative.testing.matcherlib

fun <T> assertThat(actual: T, matcher: Matcher<T>): Unit
{
    val result = matcher.matches(actual)
    if(result.failed)
    {
        throw AssertionError(buildMessage(result))
    }
}

internal fun buildMessage(result: Result): String
{
    val builder = StringBuilder()
    builder.append("Expected that it:\n\t")
    builder.append(result.expected)
    builder.append("\nbut it:\n\t")
    builder.append(result.actual)
    return builder.toString()
}