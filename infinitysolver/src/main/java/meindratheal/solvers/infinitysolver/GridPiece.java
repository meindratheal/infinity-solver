package meindratheal.solvers.infinitysolver;

import static meindratheal.common.base.Preconditions.*;

import meindratheal.solvers.infinitysolver.piece.Piece;

/**
 * Represents a single piece on the grid. In addition to the actual piece, it
 * also contains some metadata related to solving the grid.
 * @author Meindratheal
 */
public final class GridPiece
{
	/**
	 * The piece in this position.
	 */
	private final Piece piece;
	/**
	 * The current solver state of this piece.
	 */
	private State state;

	/**
	 * Constructs a new instance with the given underlying piece. The initial
	 * state is MOVABLE.
	 * @param piece The piece in this position.
	 */
	public GridPiece(final Piece piece)
	{
		this.piece = checkNotNull(piece, "piece");
		this.state = State.MOVABLE;
	}

	/**
	 * Gets the piece in this position.
	 * @return The piece.
	 */
	public Piece piece()
	{
		return piece;
	}

	/**
	 * Gets the solver state of this piece.
	 * @return The state.
	 */
	public State state()
	{
		return state;
	}

	/**
	 * Sets this piece to the LOCKED state.
	 */
	public void lock()
	{
		this.state = State.LOCKED;
	}

	/**
	 * An enumeration of the possible states a GridPiece can be in.
	 */
	public enum State
	{
		/**
		 * The piece has no other valid orientation.
		 */
		LOCKED,
		/**
		 * The piece is not necessarily in a valid orientation.
		 */
		MOVABLE;
	}
}
