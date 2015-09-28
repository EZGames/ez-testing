package com.collaborative.testing.matcherlib;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultTest
{
   public static final boolean FAILING = true;
   public static final boolean PASSING = false;
   public static final String EXPECTED = "expected";
   public static final String ONFAILURE = "onFailure";
   public static final String OUTEXPECTED = "\texpected";
   public static final String OUTONFAILURE = "\tonFailure";

   @Test public void failingResultUsesFailureForActual()
   {
      Result result = new Result(FAILING, EXPECTED, ONFAILURE);

      assertEquals(result.getActual(), OUTONFAILURE);
   }

   @Test public void passingResultUsesExpectedForActual()
   {
      Result result = new Result(PASSING, EXPECTED, ONFAILURE);

      assertEquals(result.getActual(), OUTEXPECTED);
   }
}
