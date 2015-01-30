package ezgames.testing.params.mymocks;

public class ValidateParamRunner extends ValidatingRunner
{
	public ValidateParamRunner(Integer expectedParam)
	{
		this.expectedParam = expectedParam;
		this.param = expectedParam - 1;
	}
	
	@Override
	public void run(Integer t)
	{
		this.param = t;
	}
	
	@Override
	public boolean validate()
	{
		return this.param == this.expectedParam;
	}
	
	@Override
	public String errorMessage()
	{
		return "Consumer was not called with the correct parameter";
	}
	
	private final Integer expectedParam;
	private Integer param;
}
