/* -----------------------------------------------------------------------------------------------------------------------------
 * NQueens2.java:		Determines the position n queens can be placed on an nXn chessboard without ever attacking one another.
 * 						Utilizes the backtracking programming technique.  Prints total number of possible move combinations.
 * 
 * Author:				Kevin Richardson <kevin@magically.us>
 * Date:				24-Feb-2011
 * ----------------------------------------------------------------------------------------------------------------------------*/


public class NQueens2 {
	
	// size of the board.  also tracks number of queens to place.
	private int size;
	
	//track positions of the queen in every column.  ex: queen in row 2 of column 1:  positions[1] = 2;
	private int[] positions;
	// track whether or not a row is already occupied by a queen (true if it's occupied)
	private boolean[] row;
	// track whether or not the diagonals are occupied by a queen (true if it's occupied)
	private boolean[] diagLeft;
	private boolean[] diagRight;
	
	// number of solutions found.
	private int count = 0;
	
	
	public NQueens2(int size) {
		this.size = size;
		this.positions = new int[size];
		this.row = new boolean[size];
		this.diagLeft = new boolean[size * 2 - 1];
		this.diagRight = new boolean[size * 2 -1];
	}

	
	public static void main(String[] args) {
		for(int i = 3; i <= 12; i++){
			doGame(i);
		}
	}
	
	// creates a new game with board size nxn and prints out the number of solutions
	public static void doGame(int size) {		
		NQueens2 game = new NQueens2(size);
		
		// find solution, starting at column 0
		game.tryNextMove();
		
		// tell results
		System.out.println("There are " + game.getCount() + " solutions for a " + size + "x" + size + " grid.");
	}
	

	// overload tryNextMove
	public void tryNextMove() {	this.tryNextMove(0); }
	
	// attempts to place a piece in (each row of) column c.
	// Counts each solution as it's found.
	public void tryNextMove(int column) {
		// initialize selection of moves and run through each row.
		for(int row = 0; row < this.size; row++){
			
			// only work on this path if this move is acceptable
			if(this.isAcceptable(column, row)){
				
				// add a queen
				this.recordMove(column, row);
				
				// the board is full:  we've reached a solution!
				if(column == size - 1){ this.count++; }
				
				// try the next move because there are more columns to fill.
				else{ this.tryNextMove(column + 1); }
				
				// remove this queen so the loop can keep on going to find all solutions.
				this.eraseMove(column, row);
			}
			
		}
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
	
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
