package ezgames.testing.params.mymocks;

public class ValidateCallRunner extends ValidatingRunner
{
	
	@Override
	public boolean validate()
	{
		return valid;
	}
	
	@Override
	public String errorMessage()
	{
		return "This consumer was not called";
	}
	
	@Override
	public void run(Integer t)
	{
		valid = true;
	}
	
	private boolean valid = false;
	
}
