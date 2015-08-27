package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A piece with two adjacent connections. By default, the connections point
 * North and East.
 * @author Meindratheal
 */
class TwoPieceAdjacent extends Piece
{
	/**
	 * A TwoPieceAdjacent is characterised by the direction of the first
	 * connection. The second connection is the
	 * {@link ConnectionDirection#next() next} one around.
	 */
	private ConnectionDirection firstDir = ConnectionDirection.NORTH;

	TwoPieceAdjacent()
	{
		super(PieceType.TWO_ADJACENT);
	}

	@Override
	public int numOrientations()
	{
		return 4;
	}

	@Override
	public int currentOrientation()
	{
		return firstDir.ordinal();
	}

	@Override
	public void nextOrientation()
	{
		firstDir = firstDir.next();
	}

	@Override
	public void prevOrientation()
	{
		firstDir = firstDir.prev();
	}

	@Override
	public void setOrientation(final int orientation)
	{
		final ConnectionDirection[] dirs = ConnectionDirection.values();
		checkArgument(orientation >= 0 && orientation < dirs.length,
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
		return dir == firstDir || dir == firstDir.next();
	}
}
