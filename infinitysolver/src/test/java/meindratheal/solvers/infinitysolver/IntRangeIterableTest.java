package meindratheal.solvers.infinitysolver;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.UnsignedInts;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link IntRangeIterable}.
 * @author Meindratheal
 */
public class IntRangeIterableTest
{
	/**
	 * Ensures that an empty range (with start equal to end) has no values.
	 */
	@Test
	public void testEmptyRange()
	{
		final ImmutableList<Integer> values = ImmutableList.of(0, 1, -1,
				Integer.MAX_VALUE, Integer.MIN_VALUE);
		for(Integer val : values)
		{
			final Iterable<Integer> iterable = new IntRangeIterable(val, val);
			final Iterator<Integer> iterator = iterable.iterator();
			assertFalse(iterator.hasNext());
			try
			{
				iterator.next();
				fail("expected NoSuchElementException");
			}
			catch(final NoSuchElementException ex)
			{
				//Expected.
			}
		}
	}

	/**
	 * Ensures that a range with a single element works as expected.
	 */
	@Test
	public void testSingleElementRange()
	{
		final ImmutableList<Integer> values = ImmutableList.of(0, 1, -1, 2, -2,
				Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE - 1);
		for(Integer val : values)
		{
			final Iterable<Integer> iterable
					= new IntRangeIterable(val, val + 1);
			final Iterator<Integer> iterator = iterable.iterator();
			assertTrue(iterator.hasNext());
			assertEquals(val, iterator.next());
			assertFalse(iterator.hasNext());
			try
			{
				iterator.next();
				fail("expected NoSuchElementException");
			}
			catch(final NoSuchElementException ex)
			{
				//Expected.
			}
		}
	}

	/**
	 * Ensures that a range with multiple elements works as expected.
	 */
	@Test
	public void testMultipleElementRange()
	{
		final ImmutableList<IntegerRange> ranges = ImmutableList.of(
		new IntegerRange(0, 10),
		new IntegerRange(-5, 2),
		new IntegerRange(-17, -7),
		new IntegerRange(Integer.MAX_VALUE - 2, Integer.MIN_VALUE + 3),
		new IntegerRange(Integer.MIN_VALUE, Integer.MAX_VALUE)
				);
		for(IntegerRange range : ranges)
		{
			final Iterable<Integer> iterable
					= new IntRangeIterable(range.start, range.end);
			final Iterator<Integer> iterator = iterable.iterator();
			final long elements = UnsignedInts.toLong(range.end - range.start);
			for(long i = 0; i < elements; i++)
			{
				assertTrue(iterator.hasNext());
				//Cast is required to remove ambiguity of long and Object.
				assertEquals((Object) (range.start + (int) i), iterator.next());
			}
			//End of range reached.
			assertFalse(iterator.hasNext());
			try
			{
				iterator.next();
				fail("expected NoSuchElementException");
			}
			catch(final NoSuchElementException ex)
			{
				//Expected.
			}
		}
	}

	/**
	 * Basic structure containing a start and end for a range.
	 */
	private static final class IntegerRange
	{
		final Integer start;
		final Integer end;
		IntegerRange(final Integer start, final Integer end)
		{
			this.start = start;
			this.end = end;
		}
	}

}
