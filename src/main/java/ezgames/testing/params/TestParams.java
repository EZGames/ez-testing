package ezgames.testing.params;


public class TestParams<P>
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	public static <P> TestParams<P> running(Runner<P> testMethod)
	{
		if(testMethod == null)
			throw new NullPointerException("TestParams cannot run a null test method");
		
		return new TestParams<>(testMethod);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public void testWith(P paramSet) throws Exception
	{
		testMethod.run(paramSet);
	}
	
	@SuppressWarnings("unchecked")
	public void testWith(P... paramSets) throws Exception
	{
		testMethod.run(paramSets[0]);
	}
	
	//***************************************************************************
	// Public Functional Interface
	//***************************************************************************
	@FunctionalInterface
	public static interface Runner<P>
	{
		void run(P parameter) throws Exception;
	}
	
	//***************************************************************************
	// Private Constructor
	//***************************************************************************
	private TestParams(Runner<P> testMethod)
	{
		this.testMethod = testMethod;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final Runner<P> testMethod;
}
