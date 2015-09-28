package ezgames.testing.immatchure;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;

import static com.collaborative.immatcher.MatcherlibPackage.and;
import static com.collaborative.immatcher.MatcherlibPackage.chain;
import static org.junit.Assert.*;

public class MatcherChainingTest
{
   @Test public void failsWithFailureLast()
   {
      Result result = chain(MockMatcher.passes(), MockMatcher.fails()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void failsWithFailureFirst()
   {
      Result result = chain(MockMatcher.fails(), MockMatcher.passes()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void failsWithFailureInMiddle()
   {
      Result result = chain(MockMatcher.passes(), MockMatcher.fails(), MockMatcher.passes()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void passesWithAllPassing()
   {
      Result result = chain(MockMatcher.passes(), MockMatcher.passes(), MockMatcher.passes()).matches("");

      assertFalse(result.getFailed());
   }

   @Test public void expectedMessageCorrect()
   {
      Result result = chain(MockMatcher.passes(), MockMatcher.fails()).matches("");

      assertEquals(result.getExpected(), "\tpassed\n\tpassed");
   }

   @Test public void actualMessageOrderPassThenFail()
   {
      Result result = chain(MockMatcher.passes(), MockMatcher.fails()).matches("");

      assertEquals(result.getActual(), "\tpassed\n\tfailed");
   }

   @Test public void actualMessageOrderFailFailPass()
   {
      Result result = chain(MockMatcher.fails(), MockMatcher.fails(), MockMatcher.passes()).matches("");

      assertEquals(result.getActual(), "\tfailed\n\tfailed\n\tpassed");
   }

   @Test public void testSuperAndSubTypeMatchers()
   {
      MatcherlibPackage.assertThat(new ArrayList(), and(new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>()));
      MatcherlibPackage.assertThat(new ArrayList(), and(new TypeMatcher<Iterable>(), new TypeMatcher<ArrayList>()));
      MatcherlibPackage.assertThat(new ArrayList(), chain(new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>(), new TypeMatcher<Iterable>()));
      MatcherlibPackage.assertThat(new ArrayList(), chain(new TypeMatcher<Iterable>(), new TypeMatcher<ArrayList>(), new TypeMatcher<Iterable>()));
   }
}

class TypeMatcher<T> implements Matcher<T>
{
   @NotNull public Result matches(Object actual) {
      return new DefaultResult(false, "", "");
   }
}
