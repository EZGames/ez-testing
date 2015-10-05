package ezgames.testing.immatchure;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;

import static ezgames.testing.immatchure.Chaining.and;
import static ezgames.testing.immatchure.Chaining.chain;
import static ezgames.testing.immatchure.ImmatchurePackage.*;
import static ezgames.testing.immatchure.mocks.MockMatcher.*;
import static org.junit.Assert.*;

public class MatcherChainingTest
{
   @Test public void failsWithFailureLast()
   {
      Result result = chain(passes(), fails()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void failsWithFailureFirst()
   {
      Result result = chain(fails(), passes()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void failsWithFailureInMiddle()
   {
      Result result = chain(passes(), fails(), passes()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void passesWithAllPassing()
   {
      Result result = chain(passes(), passes(), passes()).matches("");

      assertFalse(result.getFailed());
   }

   @Test public void expectedMessageCorrect()
   {
      Result result = chain(passes(), fails()).matches("");

      assertEquals(result.getExpected(), "\tpassed\n\tpassed");
   }

   @Test public void actualMessageOrderPassThenFail()
   {
      Result result = chain(passes(), fails()).matches("");

      assertEquals(result.getActual(), "\tpassed\n\tfailed");
   }

   @Test public void actualMessageOrderFailFailPass()
   {
      Result result = chain(fails(), fails(), passes()).matches("");

      assertEquals(result.getActual(), "\tfailed\n\tfailed\n\tpassed");
   }

   @Test public void testSuperAndSubTypeMatchers()
   {
      assertThat(new ArrayList(), and(new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>()));
      assertThat(new ArrayList(), and(new TypeMatcher<Iterable>(), new TypeMatcher<ArrayList>()));
      assertThat(new ArrayList(), chain(new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>(), new TypeMatcher<Iterable>()));
      assertThat(new ArrayList(), chain(new TypeMatcher<Iterable>(), new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>()));
   }
}

class TypeMatcher<T> implements Matcher<T>
{
   @NotNull public Result matches(Object actual) {
      return new DefaultResult(false, "", "");
   }
}
