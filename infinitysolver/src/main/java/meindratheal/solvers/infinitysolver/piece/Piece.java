package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A single piece on the grid. A piece can have many <em>orientations</em>,
 * which can be cycled through using  {@link #currentOrientation() currentOrientation},
 * {@link #numOrientations() numOrientations},
 * {@link #nextOrientation() nextOrientation},
 * {@link #prevOrientation() prevOrientation}, and
 * {@link #setOrientation() setOrientation}.
 * @author Meindratheal
 */
public abstract class Piece
{
	private final PieceType type;

	Piece(final PieceType type)
	{
		this.type = checkNotNull(type, "type");
	}

	/**
	 * Gets the piece type.
	 * @return The piece type.
	 */
	public PieceType type()
	{
		return type;
	}

	/**
	 * The number of orientations this piece can be in.
	 * @return The number of orientations.
	 */
	public abstract int numOrientations();

	/**
	 * Gets the current orientation. This is always in the range [0,
	 * {@link #numOrientations() numOrientations()}).
	 * @return The current orientation.
	 */
	public abstract int currentOrientation();

	/**
	 * Rotates this piece into the next orientation.
	 */
	public abstract void nextOrientation();

	/**
	 * Rotates this piece into the previous orientation.
	 */
	public abstract void prevOrientation();

	/**
	 * Sets the current orientation.
	 * @param orientation The new orientation.
	 * @throws IllegalArgumentException If {@code orientation < 0} or
	 * {@code orientation >= }{@link #numOrientations() numOrientations()}.
	 */
	public abstract void setOrientation(int orientation);
}
