package ezgames.testing.newcrest

interface Matcher<in T>
{
    companion object
    {
        fun <T> fromHamcrest(original: org.hamcrest.Matcher<T>): Matcher<T>
        {
            return HamcrestAdapter(original)
        }
    }
    fun matches(actual: T):Result
}

open class Result(val failed: Boolean,
                  val expected: String,
                  val actual: String)

