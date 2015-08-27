package meindratheal.solvers.infinitysolver.piece;

/**
 * An enumeration of the possible piece types.
 * @author Meindratheal
 */
public enum PieceType
{
	/**
	 * Has no connections.
	 */
	ZERO,
	/**
	 * Has a single connection.
	 */
	ONE,
	/**
	 * Has two connections. Also known as a corner.
	 */
	TWO_ADJACENT,
	/**
	 * Has two opposite connections.
	 */
	TWO_OPPOSITE,
	/**
	 * Has three connections.
	 */
	THREE,
	/**
	 * Has four connections.
	 */
	FOUR;
}
