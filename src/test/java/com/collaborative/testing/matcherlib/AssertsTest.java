package com.collaborative.testing.matcherlib;

import org.junit.Test;

import static org.junit.Assert.fail;

public class AssertsTest
{
   @Test public void throwsOnFailure()
   {
      try
      {
         MatcherlibPackage.assertThat("aString", MockMatcher.fails());
         fail("Failing assertion did not throw an Error");
      }
      catch(AssertionError ex) {}
   }

   @Test public void doesNotThrowOnPass()
   {
      try
      {
         MatcherlibPackage.assertThat("aString", MockMatcher.passes());
      }
      catch(AssertionError ex)
      {
         fail("Passing assertion threw an Error");
      }
   }
}

