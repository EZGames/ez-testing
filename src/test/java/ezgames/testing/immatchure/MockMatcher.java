package ezgames.testing.immatchure;

import org.jetbrains.annotations.NotNull;

class MockMatcher implements Matcher<String>
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
