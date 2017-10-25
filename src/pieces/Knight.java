package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Knight extends Pieces{
	
	public Knight(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}
	public Knight(Pieces p){
		super(p);
	}

	/**
	 * canMove returns whether a piece can move to the proposed position.
	 * 
	 * @param board This is a 2D array of Pieces and the current board.
	 * @param r This is the proposed row.
	 * @param c This is the proposed column.
	 * @returns a deep copy of the board.
	 */
	@Override
	public boolean canMove(Pieces[][] board, int r,int c) {
		
		if (r>7 || c>7 || r<0 || c<0)
			return false;
		ArrayList<Coordinates> validMoves = allValidMoves(board);
		Coordinates proposedMove = new Coordinates(r,c);
	//	System.out.println(validMoves);
		//checks if proposed move is valid
		for(Coordinates co:validMoves){
			if (co.equals(proposedMove)){
				return true;
			}
		}
				
		return false;	
	}
	
	/**
	 * allValidMoves returns a list of all the coordinates of all the valid moves for this piece
	 * 
	 * @param board This is a 2D array of Pieces and the current board.
	 * @returns an ArrayList of coordinates of all the valid moves
	 */
	public ArrayList<Coordinates> allValidMoves(Pieces[][] board){
	ArrayList<Coordinates> validMoves = new ArrayList<Coordinates>();
	//hardcoded if statements
	//going 2 left and 1 down
	if(column>1 && row<7&& (board[row+1][column-2]==null||board[row+1][column-2].getTeam()!=team)){
		validMoves.add(new Coordinates(row+1,column-2));
	}
	
	//2 left and 1 up
	if(column>1 && row>0 && (board[row-1][column-2]==null||board[row-1][column-2].getTeam()!=team)){
		validMoves.add(new Coordinates(row-1,column-2));
	}
	
	//2 up and 1 left
	if(column>0 && row>1&& (board[row-2][column-1]==null||board[row-2][column-1].getTeam()!=team)){
		validMoves.add(new Coordinates(row-2,column-1));
	}
	
	//2 up and 1 right
	if(column<7 && row>1 && (board[row-2][column+1]==null||board[row-2][column+1].getTeam()!=team)){
		validMoves.add(new Coordinates(row-2,column+1));
	}
	
	//2 right and 1 up
	if(column<6 && row>0 && (board[row-1][column+2]==null||board[row-1][column+2].getTeam()!=team)){
		validMoves.add(new Coordinates(row-1,column+2));
	}
	
	//2 right and 1 down
	if(column<6 && row<7 && (board[row+1][column+2]==null||board[row+1][column+2].getTeam()!=team)){
		validMoves.add(new Coordinates(row+1,column+2));
	}
	
	//2 down and 1 right
	if(column<7 && row<6 && (board[row+2][column+1]==null|| board[row+2][column+1].getTeam()!=team)){
		validMoves.add(new Coordinates(row+2,column+1));
	}
	
	//2 down and 1 left
	if(column>0 && row<6 && (board[row+2][column-1]==null || board[row+2][column-1].getTeam()!=team)){
		validMoves.add(new Coordinates(row+2,column-1));
	}

	return validMoves;
	}
	
	

}