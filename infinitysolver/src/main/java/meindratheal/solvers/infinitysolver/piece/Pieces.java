package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * Contains static factory methods for creating Piece instances.
 * <p>
 * There is no requirement for each factory method to create a distinct Piece.
 * For example, a Piece with only one orientation is stateless and so immutable,
 * so can be used by multiple callers without any interference.
 * @author Meindratheal
 */
public final class Pieces
{
	/**
	 * Suppress default constructor.
	 */
	private Pieces()
	{
		throw new AssertionError();
	}

	/**
	 * Returns a piece of the given type. The implementation may, if it chooses
	 * to, reuse the same instance if the piece has no state.
	 * @param type The type of piece to create.
	 * @return The piece.
	 */
	public static Piece createPiece(final PieceType type)
	{
		checkNotNull(type, "type");
		switch(type)
		{
		case ZERO:
			return new ZeroPiece();
		case ONE:
			return new OnePiece();
		case TWO_ADJACENT:
			return new TwoPieceAdjacent();
		case TWO_OPPOSITE:
			return new TwoPieceOpposite();
		case THREE:
			return new ThreePiece();
		case FOUR:
			return new FourPiece();
		default:
			throw new AssertionError();
		}
	}
}
