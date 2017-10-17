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
		
		//upper left corner
		if(row==0 && column==0){
			Coordinates[] coList=new Coordinates[3];//declare coordinates array
			coList[0]= new Coordinates(0,1);
			coList[1]= new Coordinates(1,0);
			coList[2]= new Coordinates(1,1);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//upper right corner
		else if(row==0 && column==7){
			Coordinates[] coList=new Coordinates[3];//declare coordinates array
			coList[0]= new Coordinates(0,6);
			coList[1]= new Coordinates(1,7);
			coList[2]= new Coordinates(1,6);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//lower left corner
		else if(row==7 && column==0){
			Coordinates[] coList=new Coordinates[3];//declare coordinates array
			coList[0]= new Coordinates(6,0);
			coList[1]= new Coordinates(7,1);
			coList[2]= new Coordinates(6,1);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//lower right corner
		else if(row==7 && column==7){
			Coordinates[] coList=new Coordinates[3];//declare coordinates array
			coList[0]= new Coordinates(7,6);
			coList[1]= new Coordinates(6,7);
			coList[2]= new Coordinates(6,6);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//left most column not including top and bottom
		else if(row!=0 && row!=7 && column==0){
			Coordinates[] coList=new Coordinates[5];//declare coordinates array
			coList[0]=new Coordinates(row+1,column);
			coList[1]=new Coordinates(row+1,column+1);
			coList[2]=new Coordinates(row-1,column);
			coList[3]=new Coordinates(row-1,column+1);
			coList[4]=new Coordinates(row,column+1);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//right most column not including top and bottom
		else if(row!=0 && row!=7 && column==7 ){
			Coordinates[] coList=new Coordinates[5];//declare coordinates array
			coList[0]=new Coordinates(row,column-1);
			coList[1]=new Coordinates(row+1,column-1);
			coList[2]=new Coordinates(row-1,column-1);
			coList[3]=new Coordinates(row+1,column);
			coList[4]=new Coordinates(row-1,column);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//top most column not including furthest left and furthest right 
		else if(row==0 && column != 0 && column != 7){
			Coordinates[] coList=new Coordinates[5];//declare coordinates array
			coList[0]=new Coordinates(row+1,column);
			coList[1]=new Coordinates(row+1,column+1);
			coList[2]=new Coordinates(row+1,column-1);
			coList[3]=new Coordinates(row,column-1);
			coList[4]=new Coordinates(row,column+1);
			validMoves = checkMoves(coList, board);
		}
		
		//bottom most column not including furthest left and furthest right
		else if(row==0 && column != 0 && column != 7){
			Coordinates[] coList=new Coordinates[5];//declare coordinates array
			coList[0]=new Coordinates(row-1,column);
			coList[1]=new Coordinates(row-1,column+1);
			coList[2]=new Coordinates(row-1,column-1);
			coList[3]=new Coordinates(row,column-1);
			coList[4]=new Coordinates(row,column+1);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		//any position not on any end at all
		else{
			Coordinates[] coList=new Coordinates[8];//declare coordinates array
			coList[0]=new Coordinates(row,column+1);
			coList[1]=new Coordinates(row,column-1);
			coList[2]=new Coordinates(row+1,column+1);
			coList[3]=new Coordinates(row+1,column-1);
			coList[4]=new Coordinates(row+1,column);
			coList[5]=new Coordinates(row-1,column+1);
			coList[6]=new Coordinates(row-1,column-1);
			coList[7]=new Coordinates(row-1,column);
			validMoves = checkMoves(coList, board);//find valid moves
		}
		
		System.out.println(validMoves);
		for(Coordinates co:validMoves){
			if (co.equals(proposedMove)){
				return true;
			}
		}
		
				
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