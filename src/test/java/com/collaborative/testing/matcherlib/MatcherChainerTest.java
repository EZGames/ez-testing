package com.collaborative.testing.matcherlib;

import org.junit.Test;

import static com.collaborative.testing.matcherlib.MatcherlibPackage.chain;
import static org.junit.Assert.*;

public class MatcherChainerTest
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
      Result result = chain(chain(MockMatcher.passes(), MockMatcher.fails()), MockMatcher.passes()).matches("");

      assertTrue(result.getFailed());
   }

   @Test public void passesWithAllPassing()
   {
      Result result = chain(chain(MockMatcher.passes(), MockMatcher.passes()), MockMatcher.passes()).matches("");

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
      Result result = chain(chain(MockMatcher.fails(), MockMatcher.fails()), MockMatcher.passes()).matches("");

      assertEquals(result.getActual(), "\tfailed\n\tfailed\n\tpassed");
   }
}
