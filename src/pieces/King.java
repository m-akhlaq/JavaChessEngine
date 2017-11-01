package pieces;
/**
 * @author Muhammad S. Akhlaq
 * @author John Brauner
 *
 */
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class King extends Pieces{
	
	/**
	 * This constructor initilizes the piece
	 * @param team the team the piece belongs to
	 * @param row the row of the piece
	 * @param column the column of the row
	 * @param name name of the piece
	 */
	public King(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This constructor is only to be used to create deep copies
	 * @param p the piece whose deep copy you want made
	 */
	public King(Pieces p){
		super(p);
	}


	@Override
	public boolean canMove(Pieces[][] board, int r,int c) {
		
		if (r>7 || c>7 || r<0 || c<0)
			return false;
		
		Coordinates proposedMove = new Coordinates(r,c);	
		ArrayList<Coordinates> validMoves=allValidMoves(board);
		//System.out.println(validMoves);
		for(Coordinates co:validMoves){
			if (co.equals(proposedMove)){
				return true;
			}
		}
		return false;
		
	}
	
	
	
	public ArrayList<Coordinates> allValidMoves(Pieces[][] board){
		ArrayList<Coordinates> validMoves = new ArrayList<Coordinates>();
		//defining valid moves for both Kings
			if (row>=1) {
				if (board[row-1][column]==null || board[row-1][column].getTeam()!=team)
					validMoves.add(new Coordinates(row-1,column));
			}
			if (row<=6) {
				if (board[row+1][column]==null || board[row+1][column].getTeam()!=team)
					validMoves.add(new Coordinates(row+1,column));
			}
			if (column>=1) {
				if (board[row][column-1]==null || board[row][column-1].getTeam()!=team)
					validMoves.add(new Coordinates(row,column-1));
			}
			if (column<=6) {
				if (board[row][column+1]==null || board[row][column+1].getTeam()!=team)
					validMoves.add(new Coordinates(row,column+1));
			}
			if (row>=1 && column>=1) {
				if (board[row-1][column-1]==null || board[row-1][column-1].getTeam()!=team)
					validMoves.add(new Coordinates(row-1,column-1));
			}
			if (row<=6 && column<=6) {
				if (board[row+1][column+1]==null || board[row+1][column+1].getTeam()!=team)
					validMoves.add(new Coordinates(row+1,column+1));
			}
			if (row>=1 && column<=6) {
				if (board[row-1][column+1]==null || board[row-1][column+1].getTeam()!=team)
					validMoves.add(new Coordinates(row-1,column+1));
			}
			if (row<=6 && column>=1) {
				if (board[row+1][column-1]==null || board[row+1][column-1].getTeam()!=team)
					validMoves.add(new Coordinates(row+1,column-1));
			}
			
			if (getNumberOfMoves()==0 && board[row][column+3] instanceof Rook && board[row][column+3].getTeam()==team && board[row][column+3].getNumberOfMoves()==0 && board[row][column+2]==null && board[row][column+1]==null){
				validMoves.add(new Coordinates(row,column+2));
			}
			if (getNumberOfMoves()==0 && board[row][column-4] instanceof Rook && board[row][column-4].getTeam()==team && board[row][column-4].getNumberOfMoves()==0 && board[row][column-3]==null && board[row][column-2]==null && board[row][column-1]==null){
				validMoves.add(new Coordinates(row,column-2));
			}
			return validMoves;
		
	}
	

}