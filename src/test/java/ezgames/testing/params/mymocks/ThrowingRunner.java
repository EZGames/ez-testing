package ezgames.testing.params.mymocks;

import ezgames.testing.params.TestParams;

public class ThrowingRunner implements TestParams.Runner<Integer>
{
	
	@Override
	public void run(Integer t) throws Exception
	{
		throw new Exception();
	}
	
}
