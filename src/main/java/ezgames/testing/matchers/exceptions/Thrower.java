package ezgames.testing.matchers.exceptions;

import ezgames.testing.matchers.exceptions.ThrowsA;

/**
 * For use with the {@link ThrowsA} matcher, the {@code ThrowingRunnable}
 * interface is a functional interface similar to {@link Runnable}, except that
 * it can throw a {@link Throwable}.  No specific Throwable type is required,
 * since it's intended us is with the Throws matcher, which does not require it
 * to.
 */
public interface Thrower
{
	void run() throws Throwable;
	
	/**
	 * A pass-through method that is used simply to increase the fluency of
	 * an assertion taking in a {@code ThrowingRunnable}.<br>
	 * For example:<br>
	 * {@code assertThat(calling(() -> methodThatThrows), throwsAn(Exception.class))}<br>
	 * Using it is completely optional, since it reads fairly well without it:<br>
	 * {@code assertThat(() -> methodThatThrows(), throwsAn(Exception.class))}
	 * @param runnable - a {@code ThrowingRunnable} to be returned
	 * @return the {@code ThrowingRunnable} passed in
	 */
	public static Thrower calling(Thrower runnable) {
		return runnable;
	}
}
