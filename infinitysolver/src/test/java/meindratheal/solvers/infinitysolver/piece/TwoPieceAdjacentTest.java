package meindratheal.solvers.infinitysolver.piece;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *Tests for {@link TwoPieceAdjacent}.
 * @author Meindratheal
 */
public class TwoPieceAdjacentTest
{
	/**
	 * The piece used by each test.
	 */
	private TwoPieceAdjacent piece;

	/**
	 * Initialises the piece before each test.
	 */
	@Before
	public void before()
	{
		this.piece = new TwoPieceAdjacent();
	}

	/**
	 * Ensures that this piece has the correct type.
	 */
	@Test
	public void testPieceType()
	{
		assertEquals(PieceType.TWO_ADJACENT, piece.type());
	}

	/**
	 * Ensures that this piece has 4 orientations.
	 */
	@Test
	public void testOrientations()
	{
		assertEquals(4, piece.numOrientations());
		//Create four pieces with different orientations, and check they're all
		//different by adding them to a Set.
		final Set<TwoPieceAdjacent> set = Sets.newHashSetWithExpectedSize(4);
		for(int orientation = 0; orientation < 4; orientation++)
		{
			final TwoPieceAdjacent newPiece = new TwoPieceAdjacent();
			newPiece.setOrientation(orientation);
			set.add(newPiece);
			//Check that the piece matches one that's gone through the right
			//number of nextOrientation() calls.
			assertEquals(piece, newPiece);
			piece.nextOrientation();
		}
		assertEquals(4, set.size());
	}

	/**
	 * Ensures that this piece has two connections, in the correct directions.
	 */
	@Test
	public void testConnections()
	{
		assertEquals(2, piece.numConnections());
		for(int orientation = 0; orientation < 4; orientation++)
		{
			piece.setOrientation(orientation);
			for(ConnectionDirection dir : ConnectionDirection.values())
			{
				if(dir.ordinal() == orientation
						|| dir.ordinal() == (orientation + 1) % 4)
				{
					assertTrue(piece.hasConnection(dir));
				}
				else
				{
					assertFalse(piece.hasConnection(dir));
				}
			}
		}
	}
}
