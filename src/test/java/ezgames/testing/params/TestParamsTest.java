package ezgames.testing.params;


import static ezgames.testing.matchers.exceptions.ThrowsA.throwsA;
import static ezgames.testing.mocking.Validates.validates;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import ezgames.testing.params.mymocks.ThrowingRunner;
import ezgames.testing.params.mymocks.ValidateParamRunner;
import ezgames.testing.params.mymocks.ValidateCallRunner;
import ezgames.testing.params.mymocks.ValidatingRunner;

public class TestParamsTest
{	
	private void validConsumer(Object obj){}
	private final int A_NUMBER = 1;
	private final int NUM_1 = 1;
	private final int NUM_2 = 2;
	private final int NUM_3 = 3;
	private final int NUM_4 = 4;
	
	@Test
	// Construction doesn't accept null
	public void construction1()
	{
		assertThat(() -> TestParams.running(null), throwsA(NullPointerException.class));
	}
	
	@Test
	// Construction with proper Consumer parameter returns a new TestParams object
	public void construction2()
	{
		assertThat(TestParams.running(this::validConsumer), is(instanceOf(TestParams.class)));
	}
	
	@Test
	// Calling testWith(P) runs the consumer
	public void testWithP1() throws Exception
	{
		ValidateCallRunner runner = new ValidateCallRunner();
		TestParams<Integer> paramsRunner = TestParams.running(runner);
		
		paramsRunner.testWith(A_NUMBER);
		
		assertThat(runner, validates());
	}
	
	@Test
	// Exceptions and Assertion errors are passed up from testWith(P)
	public void testWithP2()
	{
		TestParams<Integer> paramsRunner = TestParams.running(new ThrowingRunner());
		
		assertThat(() -> paramsRunner.testWith(A_NUMBER), throwsA(Exception.class));
	}
	
	@Test
	// Calling testWith(P) runs the consumer with the given Parameter
	public void testWithP3() throws Exception
	{
		ValidatingRunner runner = new ValidateParamRunner(A_NUMBER);
		TestParams<Integer> paramsRunner = TestParams.running(runner);
		
		paramsRunner.testWith(A_NUMBER);
		
		assertThat(runner, validates());
	}
	
	@Test
	// Calling "testWith(P varargs)" runs the consumer
	public void testWithPVarArgs1() throws Exception
	{
		ValidatingRunner runner = new ValidateCallRunner();
		TestParams<Integer> paramsRunner = TestParams.running(runner);
		
		paramsRunner.testWith(NUM_1, NUM_2, NUM_3, NUM_4);
		
		assertThat(runner, validates());
	}
	
	// Calling testWith(P...) runs as many times as there are parameter sets given
	// Throwables are passed up from testWith(P...)
	// Calling testWith(P...) runs as many times as there are parameter sets given, even with an exception thrown
	// Calling testWith(P) transforms exception to AssertionError
}