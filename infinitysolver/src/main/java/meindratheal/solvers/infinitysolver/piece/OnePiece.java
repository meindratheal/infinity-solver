package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A piece with one connection. By default, the connection points North.
 * @author Meindratheal
 */
class OnePiece extends Piece
{
	/**
	 * The direction of the connection emanating from this piece.
	 */
	private ConnectionDirection connectionDir = ConnectionDirection.NORTH;

	OnePiece()
	{
		super(PieceType.ONE);
	}

	@Override
	public int numOrientations()
	{
		return 4;
	}

	@Override
	public int currentOrientation()
	{
		return connectionDir.ordinal();
	}

	@Override
	public void nextOrientation()
	{
		connectionDir = connectionDir.next();
	}

	@Override
	public void prevOrientation()
	{
		connectionDir = connectionDir.prev();
	}

	@Override
	public void setOrientation(final int orientation)
	{
		final ConnectionDirection[] dirs = ConnectionDirection.values();
		checkArgument(orientation >= 0 && orientation < dirs.length,
				"orientation out of range");
		this.connectionDir = dirs[orientation];
	}

	@Override
	public int numConnections()
	{
		return 1;
	}

	@Override
	public boolean hasConnection(final ConnectionDirection dir)
	{
		return checkNotNull(dir, "dir") == connectionDir;
	}
}
