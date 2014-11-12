package ezgames.testing.matchers.exceptions;

import static org.junit.Assert.*;
import static ezgames.testing.matchers.exceptions.Throws.*;
import java.io.IOException;
import org.junit.Test;

public class ThrowsTester
{
	public static void justThrow()
	{
		throw new RuntimeException();
	}
	
	public static void throwMessage()
	{
		throw new RuntimeException("message");
	}
	
	public static void throwCause()
	{
		throw new RuntimeException(new NullPointerException());
	}
	
	public static void throwBoth()
	{
		throw new RuntimeException("message", new NullPointerException());
	}
	
	public static void noThrow()
	{
		return;
	}
	
	@Test
	public void pass1()
	{
		assertThat(ThrowsTester::justThrow, throwsA(RuntimeException.class));
	}
	
	@Test
	public void fail1()
	{
		assertThat("wrong type", ThrowsTester::justThrow, throwsA(IOException.class));
	}
	
	@Test
	public void fail2()
	{
		assertThat("no message", ThrowsTester::justThrow, throwsA(RuntimeException.class).hasAMessage());
	}
	
	@Test
	public void fail3()
	{
		assertThat("no cause", ThrowsTester::justThrow, throwsA(RuntimeException.class).hasACause());
	}
	
	@Test
	public void fail4()
	{
		assertThat("has neither", ThrowsTester::justThrow, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail5()
	{
		assertThat("wrong type + has neither", ThrowsTester::justThrow, throwsA(IOException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail6()
	{
		assertThat("wrong type + no message", ThrowsTester::justThrow, throwsA(IOException.class).hasAMessage());
	}
	
	@Test
	public void fail7()
	{
		assertThat("wrong type + no cause", ThrowsTester::justThrow, throwsA(IOException.class).hasACause());
	}
	
	//************ throwMessage **************
	@Test
	public void pass2()
	{
		assertThat(ThrowsTester::throwMessage, throwsA(RuntimeException.class));
	}
	
	@Test
	public void fail8()
	{
		assertThat("wrong type", ThrowsTester::throwMessage, throwsA(IOException.class));
	}
	
	@Test
	public void pass3()
	{
		assertThat(ThrowsTester::throwMessage, throwsA(RuntimeException.class).hasAMessage());
	}
	
	@Test
	public void fail9()
	{
		assertThat("no cause", ThrowsTester::throwMessage, throwsA(RuntimeException.class).hasACause());
	}
	
	@Test
	public void fail10()
	{
		assertThat("no cause", ThrowsTester::throwMessage, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail11()
	{
		assertThat("wrong type + no cause", ThrowsTester::throwMessage, throwsA(IOException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail12()
	{
		assertThat("wrong type", ThrowsTester::throwMessage, throwsA(IOException.class).hasAMessage());
	}
	
	@Test
	public void fail13()
	{
		assertThat("wrong type + no cause", ThrowsTester::throwMessage, throwsA(IOException.class).hasACause());
	}
	
	//*************** throwCause **************
	@Test
	public void pass4()
	{
		assertThat(ThrowsTester::throwCause, throwsA(RuntimeException.class));
	}
	
	@Test
	public void fail14()
	{
		assertThat("wrong type", ThrowsTester::throwCause, throwsA(IOException.class));
	}
	
	@Test
	public void fail15()
	{
		assertThat("no message", ThrowsTester::throwCause, throwsA(RuntimeException.class).hasAMessage());
	}
	
	@Test
	public void pass5()
	{
		assertThat(ThrowsTester::throwCause, throwsA(RuntimeException.class).hasACause());
	}
	
	@Test
	public void fail16()
	{
		assertThat("no message", ThrowsTester::throwCause, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail17()
	{
		assertThat("wrong type + no message", ThrowsTester::throwCause, throwsA(IOException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail18()
	{
		assertThat("wrong type + no message", ThrowsTester::throwCause, throwsA(IOException.class).hasAMessage());
	}
	
	@Test
	public void fail19()
	{
		assertThat("wrong type", ThrowsTester::throwCause, throwsA(IOException.class).hasACause());
	}
	
	//*************** throwBoth **************
	@Test
	public void pass6()
	{
		assertThat(ThrowsTester::throwBoth, throwsA(RuntimeException.class));
	}
	
	@Test
	public void fail20()
	{
		assertThat("wrong type", ThrowsTester::throwBoth, throwsA(IOException.class));
	}
	
	@Test
	public void pass7()
	{
		assertThat(ThrowsTester::throwBoth, throwsA(RuntimeException.class).hasAMessage());
	}
	
	@Test
	public void pass8()
	{
		assertThat(ThrowsTester::throwBoth, throwsA(RuntimeException.class).hasACause());
	}
	
	@Test
	public void pass9()
	{
		assertThat(ThrowsTester::throwBoth, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail21()
	{
		assertThat("wrong type", ThrowsTester::throwBoth, throwsA(IOException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void fail22()
	{
		assertThat("wrong type", ThrowsTester::throwBoth, throwsA(IOException.class).hasAMessage());
	}
	
	@Test
	public void fail23()
	{
		assertThat("wrong type", ThrowsTester::throwBoth, throwsA(IOException.class).hasACause());
	}
	
	//*************** noThrow **************
	@Test
	public void fail24()
	{
		assertThat("no exception", ThrowsTester::noThrow, throwsA(RuntimeException.class));
	}
	
	@Test
	public void fail25()
	{
		assertThat("no exception + no message", ThrowsTester::noThrow, throwsA(RuntimeException.class).hasAMessage());
	}
	
	@Test
	public void fail26()
	{
		assertThat("no exception + no cause", ThrowsTester::noThrow, throwsA(RuntimeException.class).hasACause());
	}
	
	@Test
	public void fail27()
	{
		assertThat("no exception + has neither", ThrowsTester::noThrow, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
	
	@Test
	public void failsQuicklyDueToNull()
	{
		assertThat("null", null, throwsA(RuntimeException.class).hasACause().hasAMessage());
	}
}
