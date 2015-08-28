package meindratheal.solvers.infinitysolver;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An Iterable that produces integers in a sequence, from a start (inclusive) up
 * to an end (exclusive). In other words, the numbers produced will be in the
 * range [start, end). The maximum may be less than the minimum, which will
 * cause the iterator produced to overflow once it reaches
 * {@link Integer#MAX_VALUE the maximum integer value} and start counting from
 * {@link Integer#MIN_VALUE the minimum integer value}. Note that if
 * {@code start == end} then this creates an empty range, not one containing the
 * entire integers.
 * @author Meindratheal
 */
public final class IntRangeIterable implements Iterable<Integer>
{
	/**
	 * The value to start at, inclusive.
	 */
	private final int start;
	/**
	 * The value to end at, exclusive.
	 */
	private final int end;

	/**
	 * Creates a new Iterable that will produce iterators that count from
	 * {@code start} inclusive up to {@code end} exclusive, wrapping round if
	 * {@code end < start}.
	 * @param start The value to start at, inclusive.
	 * @param end The value to end at, exclusive.
	 */
	public IntRangeIterable(final int start, final int end)
	{
		this.start = start;
		this.end = end;
	}

	@Override
	public Iterator<Integer> iterator()
	{
		return new IntRangeIterator();
	}

	/**
	 * The Iterator that produces the values.
	 */
	private class IntRangeIterator implements Iterator<Integer>
	{
		/**
		 * The value to return from the next call to {@link #next()}.
		 */
		private int next = start;

		@Override
		public boolean hasNext()
		{
			return next != end;
		}

		@Override
		public Integer next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException("end of range reached");
			}
			return next++;
		}
	}
}
