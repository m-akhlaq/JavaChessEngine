package pieces;
/**
 * @author Muhammad S. Akhlaq
 * @author John Brauner
 *
 */
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Pawn extends Pieces{
	
	boolean enPassant=false;
	/**
	 * This constructor initilizes the piece
	 * @param team the team the piece belongs to
	 * @param row the row of the piece
	 * @param column the column of the row
	 * @param name name of the piece
	 */
	public Pawn(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This constructor is only to be used to create deep copies
	 * @param p the piece whose deep copy you want made
	 */
	public Pawn(Pieces p){
		super(p);
	}


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
	
	@Override
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
	 * @return boolean weather the piece is in danger of captured by enpassant
	 */
	public boolean getEnpassant(){
		return enPassant;
	}
	

}