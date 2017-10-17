package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class King extends Pieces{
	
	public King(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Pieces[][] board, int r,int c) {
		
		if (r>7 || c>7 || r<0 || c<0)
			return false;
		
		Coordinates proposedMove = new Coordinates(r,c);
		List<Coordinates> validMoves = new ArrayList<Coordinates>();
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
			
			
			
			
			
		
		
		System.out.println(validMoves);
				
		return false;
		
	}
	
	//method to check which moves are valid
	public List<Coordinates> checkMoves(Coordinates[] moves, Pieces[][] board){
		List<Coordinates> validMoves = new ArrayList<Coordinates>();
		for(int i=0; i<moves.length;i++){
			int rowTemp=moves[i].getRow();
			int columnTemp = moves[i].getColumn();
			if(board[rowTemp][columnTemp]==null){
				validMoves.add(moves[i]);
			}
		}
		return validMoves;
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