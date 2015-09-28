package com.collaborative.testing.matcherlib;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest
{
   public static final boolean FAILING = true;
   public static final boolean PASSING = false;
   public static final String EXPECTED = "expected";
   public static final String ON_FAILURE = "onFailure";
   public static final String EXPECTED_RESULT = "\texpected";
   public static final String ON_FAILURE_RESULT = "\tonFailure";

   @Test public void failingResultUsesFailureForActual()
   {
      Result result = new DefaultResult(FAILING, EXPECTED, ON_FAILURE);

      assertEquals(result.getActual(), ON_FAILURE_RESULT);
   }

   @Test public void passingResultUsesExpectedForActual()
   {
      Result result = new DefaultResult(PASSING, EXPECTED, ON_FAILURE);

      assertEquals(result.getActual(), EXPECTED_RESULT);
   }
}
