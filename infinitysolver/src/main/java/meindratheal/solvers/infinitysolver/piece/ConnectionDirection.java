package meindratheal.solvers.infinitysolver.piece;

/**
 * The direction of a connection emanating from a piece.
 * @author Meindratheal
 */
public enum ConnectionDirection
{
	NORTH,
	EAST,
	SOUTH,
	WEST;

	/**
	 * Gets the next ConnectionDirection according to ordinal.
	 * @return The next direction.
	 */
	public ConnectionDirection next()
	{
		final ConnectionDirection[] vals = values();
		if(this.ordinal() == vals.length - 1)
		{
			return vals[0];
		}
		else
		{
			return vals[this.ordinal() + 1];
		}
	}

	/**
	 * Gets the previous ConnectionDirection according to ordinal.
	 * @return The previous direction.
	 */
	public ConnectionDirection prev()
	{
		final ConnectionDirection[] vals = values();
		if(this.ordinal() == 0)
		{
			return vals[vals.length - 1];
		}
		else
		{
			return vals[this.ordinal() - 1];
		}
	}
}
