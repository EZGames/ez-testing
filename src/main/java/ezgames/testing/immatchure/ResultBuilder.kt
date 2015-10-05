package ezgames.testing.immatchure

public class ResultBuilder private constructor(private val expected: String)
{
    companion object
    {
        fun withExpected(expected: String): ResultBuilder
                = ResultBuilder(expected)

        fun withMessages(expected: String, onFailure: String): ResultBuilderStep2
                = ResultBuilderStep2(expected, onFailure)
    }

    fun withOnFailureMessage(onFailure: String): ResultBuilderStep2
            = ResultBuilderStep2(expected, onFailure)
}

public class ResultBuilderStep2 internal constructor(private val expected: String, private val onFailure: String)
{
    fun pass(): Result = DefaultResult(false, expected, onFailure)
    fun fail(): Result = DefaultResult(true, expected, onFailure)
    fun buildWithPassStatusOf(didPass: Boolean) = DefaultResult(!didPass, expected, onFailure)
}