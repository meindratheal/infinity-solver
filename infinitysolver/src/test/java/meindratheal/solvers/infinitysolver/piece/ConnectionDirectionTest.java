package meindratheal.solvers.infinitysolver.piece;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link ConnectionDirection}.
 * @author Meindratheal
 */
public class ConnectionDirectionTest
{
	/**
	 * Tests that the {@link ConnectionDirection#next() next} method works
	 * correctly.
	 */
	@Test
	public void testNext()
	{
		assertEquals(ConnectionDirection.EAST, ConnectionDirection.NORTH.next());
		assertEquals(ConnectionDirection.SOUTH, ConnectionDirection.EAST.next());
		assertEquals(ConnectionDirection.WEST, ConnectionDirection.SOUTH.next());
		assertEquals(ConnectionDirection.NORTH, ConnectionDirection.WEST.next());
	}
	/**
	 * Tests that the {@link ConnectionDirection#prev() prev} method works
	 * correctly.
	 */
	@Test
	public void testPrev()
	{
		assertEquals(ConnectionDirection.WEST, ConnectionDirection.NORTH.prev());
		assertEquals(ConnectionDirection.NORTH, ConnectionDirection.EAST.prev());
		assertEquals(ConnectionDirection.EAST, ConnectionDirection.SOUTH.prev());
		assertEquals(ConnectionDirection.SOUTH, ConnectionDirection.WEST.prev());
	}
}
