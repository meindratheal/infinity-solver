package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A piece with two opposite connections.
 * @author Meindratheal
 */
class TwoPieceOpposite extends Piece
{
	/**
	 * A TwoPieceOpposite is characterised by the direction of one of its
	 * connections. We always use North or East (ordinals 0 and 1).
	 */
	private ConnectionDirection firstDir = ConnectionDirection.NORTH;

	TwoPieceOpposite()
	{
		super(PieceType.TWO_OPPOSITE);
	}

	@Override
	public int numOrientations()
	{
		return 2;
	}

	@Override
	public int currentOrientation()
	{
		return firstDir.ordinal();
	}

	@Override
	public void nextOrientation()
	{
		if(firstDir == ConnectionDirection.NORTH)
		{
			firstDir = ConnectionDirection.EAST;
		}
		else
		{
			firstDir = ConnectionDirection.NORTH;
		}
	}

	@Override
	public void prevOrientation()
	{
		nextOrientation();
	}

	@Override
	public void setOrientation(final int orientation)
	{
		final ConnectionDirection[] dirs = ConnectionDirection.values();
		checkArgument(orientation >= 0 && orientation < numOrientations(),
				"orientation out of range");
		this.firstDir = dirs[orientation];
	}

	@Override
	public int numConnections()
	{
		return 2;
	}

	@Override
	public boolean hasConnection(final ConnectionDirection dir)
	{
		checkNotNull(dir, "dir");
		return dir == firstDir || dir == firstDir.next().next();
	}
}
