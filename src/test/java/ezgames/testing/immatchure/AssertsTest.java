package ezgames.testing.immatchure;

import org.junit.Test;

import static org.junit.Assert.fail;

public class AssertsTest
{
   @Test public void throwsOnFailure()
   {
      try
      {
         ImmatchurePackage.assertThat("aString", MockMatcher.fails());
         fail("Failing assertion did not throw an Error");
      }
      catch(AssertionError ex) {}
   }

   @Test public void doesNotThrowOnPass()
   {
      try
      {
         ImmatchurePackage.assertThat("aString", MockMatcher.passes());
      }
      catch(AssertionError ex)
      {
         fail("Passing assertion threw an Error");
      }
   }

   //TODO test lambda version?
}

