package ezgames.testing.multirunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MultiParameterRunner<P>
{
	//***************************************************************************
	// Public static factory
	//***************************************************************************
	public static <P> MultiParameterRunner<P> run(Consumer<P> testMethod)
	{
		return new MultiParameterRunner<>(testMethod);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public void with(P parameterSet)
	{
		testMethod.accept(parameterSet);
	}
	
	@SuppressWarnings("unchecked")
	public void with(P... parameterSets)
	{
		List<P> parameterSetList = Arrays.asList(parameterSets);
		with(parameterSetList);
	}
	
	public void with(Iterable<P> parameterSets)
	{
		StringBuilder exceptions = new StringBuilder();
		Consumer<P> testMethodRunner = exceptionLogging(testMethod, exceptions);
		parameterSets.forEach(testMethodRunner);
		if(exceptions.length() > 0)
		{
			throw new AssertionError(exceptions.toString());
		}
	}
	
	//***************************************************************************
	//Private helper
	//***************************************************************************
	private Consumer<P> exceptionLogging(Consumer<P> testMethod, Appendable exceptions)
	{
		return parameterSet -> {
						try
						{
							testMethod.accept(parameterSet);
						}
						catch(Throwable ex)
						{
							String additionalMessage = String.format("\n\nWhen run with %s, got:\n\t%s", parameterSet.toString(), ex.getMessage());
							try
							{
								exceptions.append(additionalMessage);
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
		};
	}
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private MultiParameterRunner(Consumer<P> testMethod)
	{
		this.testMethod = testMethod;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final Consumer<P> testMethod;
}
