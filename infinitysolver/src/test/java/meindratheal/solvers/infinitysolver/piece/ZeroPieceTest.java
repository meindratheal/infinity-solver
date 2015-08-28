package meindratheal.solvers.infinitysolver.piece;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link ZeroPiece}.
 * @author Meindratheal
 */
public class ZeroPieceTest
{
	/**
	 * The piece used by each test.
	 */
	private ZeroPiece piece;

	/**
	 * Initialises the piece before each test.
	 */
	@Before
	public void before()
	{
		this.piece = new ZeroPiece();
	}

	/**
	 * Ensures that this piece has the correct type.
	 */
	@Test
	public void testPieceType()
	{
		assertEquals(PieceType.ZERO, piece.type());
	}

	/**
	 * Ensures that this piece has 1 orientation.
	 */
	@Test
	public void testOrientations()
	{
		assertEquals(1, piece.numOrientations());
		//Try rotation, and ensure it doesn't change.
		final ZeroPiece rotatingPiece = new ZeroPiece();
		assertEquals(piece, rotatingPiece);
		rotatingPiece.nextOrientation();
		assertEquals(piece, rotatingPiece);
		rotatingPiece.prevOrientation();
		assertEquals(piece, rotatingPiece);
	}

	/**
	 * Ensures that this piece has no connections.
	 */
	@Test
	public void testConnections()
	{
		assertEquals(0, piece.numConnections());
		for(ConnectionDirection dir : ConnectionDirection.values())
		{
			assertFalse(piece.hasConnection(dir));
		}
	}
}
