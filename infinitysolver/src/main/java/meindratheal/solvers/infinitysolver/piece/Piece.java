package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A single piece on the grid. A piece can have many <em>orientations</em>,
 * which can be cycled through using null {@link #currentOrientation() currentOrientation},
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

	/**
	 * Gets the number of connections emanating from this piece.
	 * @return The number of connections.
	 */
	public abstract int numConnections();

	/**
	 * Checks if this piece has a connection emanating from it in the given
	 * direction in its current orientation.
	 * @param dir The direction to check.
	 * @return True if there is a connection in the given direction, false
	 * otherwise.
	 */
	public abstract boolean hasConnection(final ConnectionDirection dir);

	@Override
	public final boolean equals(final Object obj)
	{
		if(obj instanceof Piece)
		{
			final Piece other = (Piece) obj;
			return this.type() == other.type()
					&& this.currentOrientation() == other.currentOrientation();
		}
		return false;
	}

	@Override
	public final int hashCode()
	{
		return type().hashCode() * 17 + currentOrientation();
	}
}
