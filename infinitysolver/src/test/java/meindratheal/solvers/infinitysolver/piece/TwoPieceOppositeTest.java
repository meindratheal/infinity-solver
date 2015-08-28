package meindratheal.solvers.infinitysolver.piece;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *Tests for {@link TwoPieceOpposite}.
 * @author Meindratheal
 */
public class TwoPieceOppositeTest
{
	/**
	 * The piece used by each test.
	 */
	private TwoPieceOpposite piece;

	/**
	 * Initialises the piece before each test.
	 */
	@Before
	public void before()
	{
		this.piece = new TwoPieceOpposite();
	}

	/**
	 * Ensures that this piece has the correct type.
	 */
	@Test
	public void testPieceType()
	{
		assertEquals(PieceType.TWO_OPPOSITE, piece.type());
	}

	/**
	 * Ensures that this piece has 2 orientations.
	 */
	@Test
	public void testOrientations()
	{
		assertEquals(2, piece.numOrientations());
		//Create two pieces with different orientations, and check they're all
		//different by adding them to a Set.
		final Set<TwoPieceOpposite> set = Sets.newHashSetWithExpectedSize(2);
		for(int orientation = 0; orientation < 2; orientation++)
		{
			final TwoPieceOpposite newPiece = new TwoPieceOpposite();
			newPiece.setOrientation(orientation);
			set.add(newPiece);
			//Check that the piece matches one that's gone through the right
			//number of nextOrientation() calls.
			assertEquals(piece, newPiece);
			piece.nextOrientation();
		}
		assertEquals(2, set.size());
	}

	/**
	 * Ensures that this piece has two connections, in the correct directions.
	 */
	@Test
	public void testConnections()
	{
		assertEquals(2, piece.numConnections());
		for(int orientation = 0; orientation < 2; orientation++)
		{
			piece.setOrientation(orientation);
			for(ConnectionDirection dir : ConnectionDirection.values())
			{
				if(dir.ordinal() == orientation
						|| dir.ordinal() == orientation + 2)
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
