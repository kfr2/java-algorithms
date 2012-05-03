/* -----------------------------------------------------------------------------------------------------------------------------
 * EightQueens.java:	Determines the position n queens can be placed on an nXn chessboard without ever attacking one another.
 * 						Utilizes the backtracking programming technique.
 * 
 * Author:				Kevin Richardson <kevin@magically.us>
 * Date:				20-Feb-2011
 * ----------------------------------------------------------------------------------------------------------------------------*/


public class NQueens {
	
	// size of the board.  also tracks number of queens to place.
	private int size;
	
	//track positions of the queen in every column.  ex: queen in row 2 of column 1:  positions[1] = 2;
	private int[] positions;
	// track whether or not a row is already occupied by a queen (true if it's occupied)
	private boolean[] row;
	// track whether or not the diagonals are occupied by a queen (true if it's occupied)
	private boolean[] diagLeft;
	private boolean[] diagRight;
	
	
	public NQueens(int size) {
		this.size = size;
		this.positions = new int[size];
		this.row = new boolean[size];
		this.diagLeft = new boolean[size * 2 - 1];
		this.diagRight = new boolean[size * 2 -1];
	}

	
	public static void main(String[] args) {
		// size of the grid/number of queens to place.
		int size = 8;
		
		NQueens game = new NQueens(size);
		
		// find solution, starting at column 0
		game.tryNextMove();
	}
	

	// overload tryNextMove
	public void tryNextMove() {	this.tryNextMove(0); }
	
	// attempts to place a piece in (each row of) column c.
	// returns true if the move was successful and false if not.
	public boolean tryNextMove(int column) {
		// initialize selection of moves.
		int r = 0;
		boolean result;
		
		do {
			if(this.isAcceptable(column, r)){
				result = true;
				
				// record the move
				this.recordMove(column, r);
				
				// if the board is full (all the queens are placed), then we've found a solution!
				if(column == size - 1){
					this.printGrid();
				}
			
				// if the board is not full, continue onward.
				else {
					result = tryNextMove(column + 1);
					
					// if the move in this column is unsuccessful, erase the previous move
					if(result == false) {
						this.eraseMove(column, r);
					}
				}
	
			}
			
			// if the move is not acceptable, signal the tryNextMove() above this one to erase the previous move.
			else{ result = false; }
			
			// to see if a move is acceptable in this column, try each row.
			r++;
		}
		
		// run until the board is full (at which size queens are placed onto it) or there are no more possible moves.
		while(r < size);
		
		
		// ultimately, this move was either acceptable or not. return this status.
		return result;
	}
	
	
	// erases a queen placement
	public void eraseMove(int column, int row) {
		this.setPositions(column, 0);
		this.setRow(row, false);
		this.setDiagLeft(column, row, false);
		this.setDiagRight(column, row, false);
	}
	
	
	// records a queen placement
	public void recordMove(int column, int row) {
		this.setPositions(column, row);
		this.setRow(row, true);
		this.setDiagLeft(column, row, true);
		this.setDiagRight(column, row, true);
	}
	
	// returns true if queen placement in column c, row r is acceptable and false if it is not.
	public boolean isAcceptable(int c, int r) {
		return (!this.getRow(r)) && (!this.getDiagLeft(c, r)) && (!this.getDiagRight(c, r));
	}
	
	public int getPositions(int column) {
		return positions[column];
	}
	
	public void setPositions(int column, int row) {
		positions[column] = row;
	}
	
	
	// returns true if the row is occupied. false if it is not.
	public boolean getRow(int row) {
		return this.row[row];
	}
	
	public void setRow(int row, boolean bool) {
		this.row[row] = bool;
	}
	
	
	// returns true if the left-facing diagonal based on column, row is occupied. false if it is not.
	public boolean getDiagLeft(int column, int row) {
		return this.diagLeft[column + row];
	}
	
	public void setDiagLeft(int column, int row, boolean bool) {
		this.diagLeft[column + row] = bool;
	}
	
	
	// returns true if the right-facing diagonal based on column, row is occupied. false if it is not.
	public boolean getDiagRight(int column, int row) {
		return this.diagRight[row - column  + (size - 1)];
	}
	
	public void setDiagRight(int column, int row, boolean bool) {
		this.diagRight[row - column + (size - 1)] = bool;
	}
	
	
	// prints out the positions of the queens on the grid.
	public  void printGrid() {
		System.out.print("Col: ");
		for(int i = 0; i < this.positions.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		
		System.out.print("Row: ");
		for(int i = 0; i < this.positions.length; i++) {
			System.out.print(this.getPositions(i) + " ");
		}
		System.out.println("\n");
	}

}
