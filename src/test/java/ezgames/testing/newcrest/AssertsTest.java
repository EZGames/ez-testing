package ezgames.testing.newcrest;

import static org.junit.Assert.fail;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class AssertsTest
{
   @Test public void throwsOnFailure()
   {
      try
      {
         Asserts.assertThat("aString", MockMatcher.fails());
         fail("Failing assertion did not throw an Error");
      }
      catch(AssertionError ex) {}
   }

   @Test public void doesNotThrowOnPass()
   {
      try
      {
         Asserts.assertThat("aString", MockMatcher.passes());
      }
      catch(AssertionError ex)
      {
         fail("Passing assertion threw an Error");
      }
   }
}

class MockMatcher implements ezgames.testing.newcrest.Matcher<String>
{
   public static MockMatcher passes()
   {
      return new MockMatcher(false);
   }

   public static MockMatcher fails()
   {
      return new MockMatcher(true);
   }

   private MockMatcher(boolean fail)
   {
      this.fail = fail;
   }

   private final boolean fail;

   @NotNull @Override public Result matches(String actual)
   {
      return new Result(this.fail, "to pass", "failed");
   }
}