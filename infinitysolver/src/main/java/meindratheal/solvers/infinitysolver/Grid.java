package meindratheal.solvers.infinitysolver;

import com.google.common.collect.ArrayTable;
import java.util.BitSet;
import meindratheal.solvers.infinitysolver.GridPiece.State;
import meindratheal.solvers.infinitysolver.piece.ConnectionDirection;
import meindratheal.solvers.infinitysolver.piece.Piece;
import meindratheal.solvers.infinitysolver.piece.PieceType;
import meindratheal.solvers.infinitysolver.piece.Pieces;

import static meindratheal.common.base.Preconditions.*;

/**
 * A representation of a particular puzzle grid, as a grid of GridPiece
 * instances.
 * @author Meindratheal
 */
public class Grid
{
	/**
	 * The table housing the grid. This table is two rows taller and two columns
	 * wider than the one passed into the constructor because it is padded with
	 * zero-connection pieces, to make it easier to solve (no special casing
	 * needed when checking north of a cell on the top row, for example).
	 */
	private final ArrayTable<Integer, Integer, GridPiece> grid;

	/**
	 * The number of rows in the portion of the grid that needs solving. This is
	 * equal to the number of rows in the entire grid, minus 2 (the Zero padding
	 * at the top and bottom).
	 */
	private final int rows;
	/**
	 * The number of columns in the portion of the grid that needs solving. This
	 * is equal to the number of columns in the entire grid, minus 2 (the Zero
	 * padding on each side).
	 */
	private final int cols;

	/**
	 * Creates a grid with the given pieces.
	 * @param grid The 2D array of pieces. This array must be rectangular.
	 */
	public Grid(final Piece[][] grid)
	{
		validateGridArray(grid);
		this.rows = grid.length;
		this.cols = grid[0].length;
		this.grid = createGridTable(grid, rows, cols);
	}

	/**
	 * Solves this grid.
	 */
	public void solve()
	{
		iterativeLock();
		//TODO: Iterative locking can't solve everything. For example, a 2x2
		//grid of Ones can't be solved by iterative locking, so we need another
		//method (brute force, temporary locking, others).
	}

	/**
	 * Performs the first step of solving, iterative locking. This checks for
	 * pieces that only have one valid orientation, and "locks" them into that
	 * orientation. The entire grid is swept, and this is repeated until no more
	 * pieces can be locked.
	 */
	private void iterativeLock()
	{
		boolean changed = false;

		//First pass: look for pieces with a single orientation and lock them.
		//Include the outer border of Zero pieces.
		System.out.println("Initial pass");
		for(int r = -1; r < rows + 1; r++)
		{
			for(int c = -1; c < cols + 1; c++)
			{
				final GridPiece gp = grid.at(r + 1, c + 1);
				if(gp.piece().numOrientations() == 1)
				{
					gp.lock();
					System.out.printf("Locked (%d, %d)%n", r, c);
				}
			}
		}

		System.out.println("Entering iterative step");
		do
		{
			System.out.println("Performing iteration");
			changed = false;
			for(int r = 0; r < rows; r++)
			{
				for(int c = 0; c < cols; c++)
				{
					//Check all the orientations of this piece, looking for
					//provably invalid ones. An invalid orientation is one where
					//either the piece has a connection in a direction but
					//there is a locked piece in that direction which does not
					//have a matching connection, or the piece does not have a
					//connection in a direction but there is a locked piece
					//there that does have a connection.
					//In other words, if the connection status doesn't match
					//with a locked piece, the orientation is invalid.
					final GridPiece gp = grid.at(r + 1, c + 1);
					final Piece piece = gp.piece();
					final BitSet invalidOrientations = new BitSet(
							piece.numOrientations());

					//If this piece is already locked, skip it.
					if(gp.state() == State.LOCKED)
					{
						continue;
					}

					for(int orientation = 0;
							orientation < piece.numOrientations(); orientation++)
					{
						piece.setOrientation(orientation);

						//North.
						final GridPiece north = grid.at(r, c + 1);
						if(north.state() == State.LOCKED
								&& (piece.hasConnection(
										ConnectionDirection.NORTH)
								!= north.piece().hasConnection(
										ConnectionDirection.SOUTH)))
						{
							invalidOrientations.set(orientation);
						}

						//East.
						final GridPiece east = grid.at(r + 1, c + 2);
						if(east.state() == State.LOCKED
								&& (piece.hasConnection(
										ConnectionDirection.EAST)
								!= east.piece().hasConnection(
										ConnectionDirection.WEST)))
						{
							invalidOrientations.set(orientation);
						}

						//South.
						final GridPiece south = grid.at(r + 2, c + 1);
						if(south.state() == State.LOCKED
								&& (piece.hasConnection(
										ConnectionDirection.SOUTH)
								!= south.piece().hasConnection(
										ConnectionDirection.NORTH)))
						{
							invalidOrientations.set(orientation);
						}

						//West.
						final GridPiece west = grid.at(r + 1, c);
						if(west.state() == State.LOCKED
								&& (piece.hasConnection(
										ConnectionDirection.WEST)
								!= west.piece().hasConnection(
										ConnectionDirection.EAST)))
						{
							invalidOrientations.set(orientation);
						}
					}

					if(invalidOrientations.cardinality()
							== piece.numOrientations() - 1)
					{
						//Only one bit not set, so this is the only valid
						//orientation, so lock it to that.
						piece.setOrientation(
								invalidOrientations.nextClearBit(0));
						gp.lock();
						changed = true;
						System.out.printf("Locked (%d, %d)%n", r, c);
					}
				}
			}
		}
		while(changed);
	}

	/**
	 * Utility method for checking that a given 2D grid array is non-null, has
	 * no null rows, has no null elements, has at least 1 row and column, and is
	 * rectangular.
	 * @param grid The grid to validate.
	 */
	private static void validateGridArray(final Piece[][] grid)
	{
		checkArrayNotNull(grid, "grid");
		checkGreaterThan(grid.length, 0, "grid height");
		final int width = grid[0].length;
		checkGreaterThan(width, 0, "grid width");
		for(int i = 0; i < grid.length; i++)
		{
			checkArrayNotNull(grid[i], "grid[" + i + "]");
			checkArgument(width == grid[i].length, "grid must be rectangular");
		}
	}

	/**
	 * Given a 2D array of Pieces, creates the grid table for it.
	 * @param grid The grid array to copy. Assumed to be rectangular, with
	 * {@code arrayRows} rows and {@code arrayCols} columns.
	 * @param arrayRows The number of rows in the array.
	 * @param arrayCols The number of columns in the array.
	 * @return The constructed grid table.
	 */
	private static ArrayTable<Integer, Integer, GridPiece> createGridTable(
			final Piece[][] grid, final int arrayRows, final int arrayCols)
	{
		final ArrayTable<Integer, Integer, GridPiece> table = ArrayTable.create(
				new IntRangeIterable(0, arrayRows + 2),
				new IntRangeIterable(0, arrayCols + 2));

		//Copy the grid into the table. Offset by 1 each direction.
		for(int r = 0; r < arrayRows; r++)
		{
			for(int c = 0; c < arrayCols; c++)
			{
				table.set(r + 1, c + 1, new GridPiece(grid[r][c]));
			}
		}

		//Fill the first row with Zero pieces.
		for(int c = 0; c < arrayCols + 2; c++)
		{
			table.set(0, c, new GridPiece(Pieces.createPiece(PieceType.ZERO)));
		}
		//Fill the last row with Zero pieces.
		for(int c = 0; c < arrayCols + 2; c++)
		{
			table.set(arrayRows + 1, c,
					new GridPiece(Pieces.createPiece(PieceType.ZERO)));
		}
		//Fill the first column with Zero pieces.
		for(int r = 0; r < arrayRows + 2; r++)
		{
			table.set(r, 0, new GridPiece(Pieces.createPiece(PieceType.ZERO)));
		}
		//Fill the last column with Zero pieces.
		for(int r = 0; r < arrayRows + 2; r++)
		{
			table.set(r, arrayCols + 1,
					new GridPiece(Pieces.createPiece(PieceType.ZERO)));
		}

		return table;
	}

	/**
	 * Converts the grid table into a printable representation. This
	 * representation will span multiple lines, and is not required to be one
	 * character per piece. It is not suitable as a general string
	 * representation, which is why {@link #toString() toString} does not call
	 * this method. It is mostly intended for debugging.
	 * @param grid The grid table. This should be padded with zero pieces, which
	 * will be ignored.
	 * @param rows The number of rows to print. The first row printed will be
	 * the second row in the table (skipping the first row, of Zero piece
	 * padding).
	 * @param cols The number of columns to print. The first column printed will
	 * be the second column in the table (skipping the first column, of Zero
	 * piece padding).
	 * @return A printable representation of the grid.
	 */
	private static String gridTableToString(
			final ArrayTable<Integer, Integer, GridPiece> grid, final int rows,
			final int cols)
	{
		//Since the Windows and Netbeans consoles don't seem to support the box
		//drawing characters, we opt for a 3x3 representation for each piece.
		//To get the string representation, we have a "master" SB that contains
		//the representation of each row so far, and then 3 "row" SB that are
		//used to handle one row of pieces.
		final StringBuilder master = new StringBuilder((3 * cols + 1) * (3
				* rows));
		final StringBuilder row1 = new StringBuilder(3 * cols + 1);
		final StringBuilder row2 = new StringBuilder(3 * cols + 1);
		final StringBuilder row3 = new StringBuilder(3 * cols + 1);

		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				final Piece piece = grid.at(row + 1, col + 1).piece();
				row1.append(' ')
						.append(piece.hasConnection(
										ConnectionDirection.NORTH) ? '|' : ' ')
						.append(' ');
				row2.append(piece.hasConnection(ConnectionDirection.WEST) ? '-'
						: ' ')
						.append(piece.numConnections() > 0 ? 'O' : ' ')
						.append(piece.hasConnection(ConnectionDirection.EAST)
										? '-' : ' ');
				row3.append(' ')
						.append(piece.hasConnection(
										ConnectionDirection.SOUTH) ? '|' : ' ')
						.append(' ');
			}
			master.append(row1).append('\n').append(row2).append('\n').append(
					row3).append('\n');
			row1.setLength(0);
			row2.setLength(0);
			row3.setLength(0);
		}
		return master.toString();
	}
}
