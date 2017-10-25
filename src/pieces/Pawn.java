package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Pawn extends Pieces{
	boolean enPassant=false;
	public Pawn(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}
	public Pawn(Pieces p){
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
		
		Coordinates proposedMove = new Coordinates(r,c);
		ArrayList<Coordinates> validMoves = allValidMoves(board);
		//defining valid moves for white pawns
		//System.out.println("Valid Moves="+validMoves);
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
	if (team==0){
		 if (row>=1 && board[row-1][column]==null){
			validMoves.add(new Coordinates(row-1,column));			
		 }
		 if (row==6 && board[row-1][column]==null && board[row-2][column]==null){
			 validMoves.add(new Coordinates(row-2,column));	
		 }
		 if (row>=1 && column>=1 && board[row-1][column-1]!=null && board[row-1][column-1].getTeam()!=team){
			 validMoves.add(new Coordinates(row-1,column-1));
		 }
		 if (row>=1 && column<=6 && board[row-1][column+1]!=null && board[row-1][column+1].getTeam()!=team){
			 validMoves.add(new Coordinates(row-1,column+1));
		 }
		 if (row==3 && column>=1 && board[row][column-1] instanceof Pawn && ((Pawn)board[row][column-1]).getEnpassant()==true){
			 validMoves.add(new Coordinates(row-1,column-1));
		 }
		 if (row==3 && column<=6 && board[row][column+1] instanceof Pawn && ((Pawn)board[row][column+1]).getEnpassant()==true){
			 validMoves.add(new Coordinates(row-1,column+1));
		 }
		 
		 }else{
		 if (row<=6 && board[row+1][column]==null){
			 validMoves.add(new Coordinates(row+1,column));			
		 }
		 if (row==1 && board[row+1][column]==null && board[row+2][column]==null){
			 validMoves.add(new Coordinates(row+2,column));	
		 }
		  if (row<=6 && board[row+1][column]==null){
			  validMoves.add(new Coordinates(row+1,column));			
		   }
		  if (row==1 && board[row+1][column]==null && board[row+2][column]==null){
			  validMoves.add(new Coordinates(row+2,column));	
		 }
		 if (row<=6 && column>=1 && board[row+1][column-1]!=null && board[row+1][column-1].getTeam()!=team){
			  validMoves.add(new Coordinates(row+1,column-1));
		 }
		 if (row<=6 && column<=6 && board[row+1][column+1]!=null && board[row+1][column+1].getTeam()!=team){
			  validMoves.add(new Coordinates(row+1,column+1));
		 }	
		 if (row==4 && column>=1 && board[row][column-1] instanceof Pawn && ((Pawn)board[row][column-1]).getEnpassant()==true){
			 validMoves.add(new Coordinates(row+1,column-1));
		 }
		 if (row==4 && column<=6 && board[row][column+1] instanceof Pawn && ((Pawn)board[row][column+1]).getEnpassant()==true){
			 validMoves.add(new Coordinates(row+1,column+1));
		 }
			
			
		}
	
	return validMoves;
	}
	/**
	 * setEnpassant sets whether the enpassant move was performed.
	 * 
	 * @param b The value of enPassant is dependant on the value of this boolean.
	 */
	public void setEnpassant(boolean b){
		enPassant=b;
	}
	
	/**
	 * getEnpassant gets whether the enpassant move was performed.
	 * 
	 */
	public boolean getEnpassant(){
		return enPassant;
	}
	

}