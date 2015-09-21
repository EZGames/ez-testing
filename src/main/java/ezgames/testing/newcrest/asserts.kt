@file:JvmName("Asserts")

package ezgames.testing.newcrest

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
    builder.append("Expected:\n\t")
    builder.append(result.expected)
    builder.append("\nbut:\n\t")
    builder.append(result.actual)
    return builder.toString()
}