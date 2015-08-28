package meindratheal.solvers.infinitysolver.piece;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link OnePiece}.
 * @author Meindratheal
 */
public class OnePieceTest
{
	/**
	 * The piece used by each test.
	 */
	private OnePiece piece;

	/**
	 * Initialises the piece before each test.
	 */
	@Before
	public void before()
	{
		this.piece = new OnePiece();
	}

	/**
	 * Ensures that this piece has the correct type.
	 */
	@Test
	public void testPieceType()
	{
		assertEquals(PieceType.ONE, piece.type());
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
		final Set<OnePiece> set = Sets.newHashSetWithExpectedSize(4);
		for(int orientation = 0; orientation < 4; orientation++)
		{
			final OnePiece newPiece = new OnePiece();
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
	 * Ensures that this piece has one connection, in the correct direction.
	 */
	@Test
	public void testConnections()
	{
		assertEquals(1, piece.numConnections());
		for(int orientation = 0; orientation < 4; orientation++)
		{
			piece.setOrientation(orientation);
			for(ConnectionDirection dir : ConnectionDirection.values())
			{
				if(dir.ordinal() == orientation)
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
