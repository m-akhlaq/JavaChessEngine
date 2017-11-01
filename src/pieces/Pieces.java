package pieces;
/**
 * @author Muhammad S. Akhlaq
 * @author John Brauner
 *
 */
import java.util.ArrayList;

import utilities.Coordinates;

public abstract class Pieces {
	
	
	protected int team;
	protected int row,column;
	protected String name;
	protected int numberOfMoves;
	public Pieces(int team,int row,int column,String name){
		 this.team= team;
		 this.row=row;
		 this.column=column;
		 this.name=name;
		 numberOfMoves=0;
		 }
	
	/**
	 * This constructor is used to make deep copy of the piece
	 * @param p A piece whose copy you want made
	 */
	public Pieces(Pieces p){
		this.team=p.team;
		this.row=p.row;
		this.column=p.column;
		this.name=p.name;
		this.numberOfMoves=p.numberOfMoves;
	}
	
	/**
	 * canMove returns whether a piece can move to the proposed position.
	 * 
	 * @param board This is a 2D array of Pieces and the current board.
	 * @param r This is the proposed row.
	 * @param c This is the proposed column.
	 * @return booelan weather the piece can move to the proposed location.
	 */
	public abstract boolean canMove(Pieces[][]board,int r,int c);
	
	/**
	 * allValidMoves returns a list of all the coordinates of all the valid moves for this piece
	 * 
	 * @param board This is a 2D array of Pieces and the current board.
	 * @return an ArrayList of coordinates of all the valid moves
	 */
	public abstract ArrayList<Coordinates> allValidMoves(Pieces[][] board);
	
	/**
	 * getPosition returns a string of the current position of the piece.
	 * 
	 * @return the current position of the piece as a string.
	 */
	public String getPosition() {
		return null;
	}
	
	/**
	 * getPosition returns the team of the current piece.
	 * 
	 * @return the team of the piece as an int.
	 */
	public int getTeam(){
		return team;
	}
	
	/**
	 * getName returns a string of the name of the selected piece.
	 * 
	 * @return the a string used to identify the piece.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getRow returns the current row of the selected piece.
	 * 
	 * @return the current row of the piece as an int.
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * getColumn returns the current column of the selected piece.
	 * 
	 * @return the current column of the piece as an int.
	 */
	public int getColumn(){
		return column;
	}
	
	/**
	 * setRow sets the row of the selected piece to the proposed row.
	 * 
	 * @param r This is the row of the proposed position.
	 */
	public void setRow(int r) {
		row =r;	
	}
	
	/**
	 * setRow sets the column of the selected piece to the proposed column.
	 * 
	 * @param c This is the column of the proposed position.
	 */
	public void setColumn(int c) {
		column=c;
	}
	
	/**
	 * getNumberOfMoves returns the number of moves made by this piece.
	 * 
	 * @return the number of moves this piece has made.
	 */
	public int getNumberOfMoves(){
		return numberOfMoves;
	}
	
	/**
	 * addOneMove increments the move count of this piece.
	 * 
	 */
	public void addOneMove(){
		numberOfMoves++;
	}

	
}