package meindratheal.solvers.infinitysolver.piece;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link FourPiece}.
 * @author Meindratheal
 */
public class FourPieceTest
{
	/**
	 * The piece used by each test.
	 */
	private FourPiece piece;

	/**
	 * Initialises the piece before each test.
	 */
	@Before
	public void before()
	{
		this.piece = new FourPiece();
	}

	/**
	 * Ensures that this piece has the correct type.
	 */
	@Test
	public void testPieceType()
	{
		assertEquals(PieceType.FOUR, piece.type());
	}

	/**
	 * Ensures that this piece has 1 orientation.
	 */
	@Test
	public void testOrientations()
	{
		assertEquals(1, piece.numOrientations());
		//Try rotation, and ensure it doesn't change.
		final FourPiece rotatingPiece = new FourPiece();
		assertEquals(piece, rotatingPiece);
		rotatingPiece.nextOrientation();
		assertEquals(piece, rotatingPiece);
		rotatingPiece.prevOrientation();
		assertEquals(piece, rotatingPiece);
	}

	/**
	 * Ensures that this piece has all four connections.
	 */
	@Test
	public void testConnections()
	{
		assertEquals(4, piece.numConnections());
		for(ConnectionDirection dir : ConnectionDirection.values())
		{
			assertTrue(piece.hasConnection(dir));
		}
	}
}
