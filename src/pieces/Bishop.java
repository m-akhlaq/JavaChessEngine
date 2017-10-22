package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Bishop extends Pieces{
	
	public Bishop(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(Pieces[][] board, int r,int c) {
		if (r>7 || c>7 || r<0 || c<0)
			return false;
		List<Coordinates> validMoves = allValidMoves(board);
		Coordinates proposedMove = new Coordinates(r,c);	
		System.out.println(validMoves);
		//checks if proposed move is valid
		for(Coordinates co:validMoves){
			if (co.equals(proposedMove)){
				return true;
			}
		}
				
		return false;
		
	}
	
	public ArrayList<Coordinates> allValidMoves(Pieces[][] board){
		ArrayList<Coordinates> validMoves = new ArrayList<Coordinates>();
		//defining valid moves for both Bishops
		//moving up and to the left
		int tempRowA=row-1;//used to track row position
		int tempColA=column-1;//used to track column position
		while(tempRowA>=0&&tempColA>=0){
			if(board[tempRowA][tempColA]==null){//position is open
				validMoves.add(new Coordinates(tempRowA,tempColA));
			}
			else if(board[tempRowA][tempColA].getTeam()==team){//same team
				break;
			}
			else if(board[tempRowA][tempColA].getTeam()!=team){//other team
				validMoves.add(new Coordinates(tempRowA,tempColA));//replaces other team's piece
				break;//cant move further
			}
			tempRowA--;//move up a row
			tempColA--;//move left a column
		}

		//moving up and to the right
		int tempRowB=row-1;//used to track row position
		int tempColB=column+1;//used to track column position
		while(tempRowB>=0&&tempColB<=7){
			if(board[tempRowB][tempColB]==null){//position is open
				validMoves.add(new Coordinates(tempRowB,tempColB));
			}
			else if(board[tempRowB][tempColB].getTeam()==team){//same team
				break;
			}
			else if(board[tempRowB][tempColB].getTeam()!=team){//other team
				validMoves.add(new Coordinates(tempRowB,tempColB));//replaces other team's piece
				break;//cant move further
			}
			tempRowB--;//move up a row
			tempColB++;//move right a column
		}
		
		//moving down and to the left
		int tempRowC=row+1;//used to track row position
		int tempColC=column-1;//used to track column position
		while(tempRowC<=7&&tempColC>=0){
			if(board[tempRowC][tempColC]==null){//position is open
				validMoves.add(new Coordinates(tempRowC,tempColC));
			}
			else if(board[tempRowC][tempColC].getTeam()==team){//same team
				break;
			}
			else if(board[tempRowC][tempColC].getTeam()!=team){//other team
				validMoves.add(new Coordinates(tempRowC,tempColC));//replaces other team's piece
				break;//cant move further
			}
			tempRowC++;//move down a row
			tempColC--;//move left a column
		}
			
		//moving down and to the right
		int tempRowD=row+1;//used to track row position
		int tempColD=column+1;//used to track column position
		while(tempRowD<=7&&tempColD<=7){
			if(board[tempRowD][tempColD]==null){//position is open
				validMoves.add(new Coordinates(tempRowD,tempColD));
			}
			else if(board[tempRowD][tempColD].getTeam()==team){//same team
				break;
			}
			else if(board[tempRowD][tempColD].getTeam()!=team){//other team
				validMoves.add(new Coordinates(tempRowD,tempColD));//replaces other team's piece
				break;//cant move further
			}
			tempRowD++;//move down a row
			tempColD++;//move right a column
		}
		
		return validMoves;
		
	}
	

}