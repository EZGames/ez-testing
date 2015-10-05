@file:JvmName("Core")

package ezgames.testing.immatchure

fun <T> assertThat(actual: T, matcher: Matcher<T>): Unit
{
    val result = matcher.matches(actual)
    if(result.failed)
    {
        throw AssertionError(buildMessage(result))
    }
}

fun <T> assertThat(actual: T, matcher: (in T) -> Result): Unit
{
    assertThat(actual, LambdaMatcher(matcher))
}

fun <T> not(original: Matcher<T>): Matcher<T>
        = LambdaMatcher { original.matches(it).invert() }

// Hidden [helper] functions and classes
// TODO Test inversions (with nesting)
private fun Result.invert(): Result
        = DefaultResult(!failed, onFailure, expected)

private fun buildMessage(result: Result): String
{
    val builder = StringBuilder()
    builder.append("Expected that it:\n")
    builder.append(result.expected)
    builder.append("\nbut it:\n")
    builder.append(result.actual)
    return builder.toString()
}