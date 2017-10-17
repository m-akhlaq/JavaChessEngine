package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Pawn extends Pieces{
	
	public Pawn(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Pieces[][] board, int r,int c) {
		
		if (r>7 || c>7 || r<0 || c<0)
			return false;
		
		Coordinates proposedMove = new Coordinates(r,c);
		List<Coordinates> validMoves = new ArrayList<Coordinates>();
		//defining valid moves for white pawns
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
		 }else{
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
			
			
		}
		System.out.println(validMoves);
		for(Coordinates co:validMoves){
			if (co.equals(proposedMove)){
				return true;
			}
		}
		
			
		
		
		
		return false;
		
	}

	@Override
	public String getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getTeam(){
		return team;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}