package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A piece with one connection. By default, the connections points East, South,
 * and West (missing North).
 * @author Meindratheal
 */
class ThreePiece extends Piece
{
	/**
	 * A ThreePiece is characterised by the one direction it <em>doesn't</em>
	 * point in.
	 */
	private ConnectionDirection missingDir = ConnectionDirection.NORTH;

	ThreePiece()
	{
		super(PieceType.THREE);
	}

	@Override
	public int numOrientations()
	{
		return 4;
	}

	@Override
	public int currentOrientation()
	{
		return missingDir.ordinal();
	}

	@Override
	public void nextOrientation()
	{
		missingDir = missingDir.next();
	}

	@Override
	public void prevOrientation()
	{
		missingDir = missingDir.prev();
	}

	@Override
	public void setOrientation(final int orientation)
	{
		final ConnectionDirection[] dirs = ConnectionDirection.values();
		checkArgument(orientation >= 0 && orientation < dirs.length,
				"orientation out of range");
		this.missingDir = dirs[orientation];
	}

	@Override
	public int numConnections()
	{
		return 3;
	}

	@Override
	public boolean hasConnection(final ConnectionDirection dir)
	{
		return checkNotNull(dir, "dir") != missingDir;
	}
}
