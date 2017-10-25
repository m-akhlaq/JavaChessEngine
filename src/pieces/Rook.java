package pieces;
import java.util.ArrayList;
import java.util.List;

import utilities.Coordinates;

public class Rook extends Pieces{
	
	public Rook(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}
	public Rook(Pieces p){
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
		List<Coordinates> validMoves = allValidMoves(board);
		
		//System.out.println(validMoves);
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
	//defining valid moves for both Rooks
	
	//moving up
	//System.out.println("MOVING UP");
	int tempRow=row-1;//used to track the distance from the current position
	while(tempRow>=0){
		if(board[tempRow][column]==null){//position is open
			validMoves.add(new Coordinates(tempRow,column));
		}
		else if(board[tempRow][column].getTeam()==team){//same team
			break;
		}
		else if(board[tempRow][column].getTeam()!=team){//other team
			validMoves.add(new Coordinates(tempRow,column));//replaces other team's piece
			break;//cant move further
		}
		tempRow--;//move up a row
	}
	
	//moving down
	//System.out.println("MOVING DOWN");
	int tempRow2=row+1;//used to track the distance from the current position
	while(tempRow2<=7){
		if(board[tempRow2][column]==null){//position is open
			validMoves.add(new Coordinates(tempRow2,column));
		}
		else if(board[tempRow2][column].getTeam()==team){//same team
			break;
		}
		else if(board[tempRow2][column].getTeam()!=team){//other team
			validMoves.add(new Coordinates(tempRow2,column));//replaces other team's piece
			break;//cant move further
		}
		tempRow2++;//move up a row
	}
	
	//moving right
	//System.out.println("MOVING RIGHT");
	int tempColumn=column+1;//used to track the distance from the current position
	while(tempColumn<=7){
		if(board[row][tempColumn]==null){//position is open
			validMoves.add(new Coordinates(row, tempColumn));
		}
		else if(board[row][tempColumn].getTeam()==team){//same team
			break;
		}
		else if(board[row][tempColumn].getTeam()!=team){//other team
			validMoves.add(new Coordinates(row,tempColumn));//replaces other team's piece
			break;//cant move further
		}
		tempColumn++;//move right one
	}
		
	//moving left
	//System.out.println("MOVING LEFT");
	int tempColumn2=column-1;//used to track the distance from the current position
	while(tempColumn2>=0){
		if(board[row][tempColumn2]==null){//position is open
			validMoves.add(new Coordinates(row, tempColumn2));
		}
		else if(board[row][tempColumn2].getTeam()==team){//same team
			break;
		}
		else if(board[row][tempColumn2].getTeam()!=team){//other team
			validMoves.add(new Coordinates(row, tempColumn2));//replaces other team's piece
			break;//cant move further
		}
		tempColumn2--;//move left one
	}
	return validMoves;
	
	}
}