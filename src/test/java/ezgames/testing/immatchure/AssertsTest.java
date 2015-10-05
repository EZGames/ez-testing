package ezgames.testing.immatchure;

import static ezgames.testing.immatchure.mocks.MockMatcher.*;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AssertsTest
{
   @Test public void throwsOnFailure()
   {
      try
      {
         ImmatchurePackage.assertThat("aString", fails());
         fail("Failing assertion did not throw an Error");
      }
      catch(AssertionError ex) {}
   }

   @Test public void doesNotThrowOnPass()
   {
      try
      {
         ImmatchurePackage.assertThat("aString", passes());
      }
      catch(AssertionError ex)
      {
         fail("Passing assertion threw an Error");
      }
   }

   //TODO test lambda version?
}

