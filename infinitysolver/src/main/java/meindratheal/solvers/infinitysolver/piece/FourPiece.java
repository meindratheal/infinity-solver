package meindratheal.solvers.infinitysolver.piece;

import static meindratheal.common.base.Preconditions.*;

/**
 * A piece with four connections.
 * @author Meindratheal
 */
class FourPiece extends Piece
{
	FourPiece()
	{
		super(PieceType.FOUR);
	}

	@Override
	public int numOrientations()
	{
		return 1;
	}

	@Override
	public int currentOrientation()
	{
		return 0;
	}

	@Override
	public void nextOrientation()
	{
		//NO-OP
	}

	@Override
	public void prevOrientation()
	{
		//NO-OP
	}

	@Override
	public void setOrientation(final int orientation)
	{
		checkArgument(orientation == 0, "orientation out of range");
		//Don't need to actually set an orientation.
	}

	@Override
	public int numConnections()
	{
		return 4;
	}

	@Override
	public boolean hasConnection(final ConnectionDirection dir)
	{
		checkNotNull(dir, "dir");
		return true;
	}
}
