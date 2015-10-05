package ezgames.testing.immatchure.mocks;

import ezgames.testing.immatchure.DefaultResult;
import ezgames.testing.immatchure.Matcher;
import ezgames.testing.immatchure.Result;
import org.jetbrains.annotations.NotNull;

public class MockMatcher implements Matcher<String>
{
   // static factories
   public static MockMatcher passes() { return new MockMatcher(false); }
   public static MockMatcher fails() { return new MockMatcher(true); }
   // constructor
   private MockMatcher(boolean fail) { this.fail = fail; }

   @NotNull @Override public Result matches(String actual)
   {
      return new DefaultResult(this.fail, "passed", "failed");
   }

   private final boolean fail;
}
