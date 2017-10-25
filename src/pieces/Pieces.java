package pieces;

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
	public Pieces(Pieces p){
		this.team=p.team;
		this.row=p.row;
		this.column=p.column;
		this.name=p.name;
		this.numberOfMoves=p.numberOfMoves;
	}
	public abstract boolean canMove(Pieces[][]board,int r,int c);
	public abstract ArrayList<Coordinates> allValidMoves(Pieces[][] board);
	
	public String getPosition() {
		return null;
	}
	public int getTeam(){
		return team;
	}
	public String getName() {
		return name;
	}
	
	public int getRow(){
		return row;
	}
	public int getColumn(){
		return column;
	}
	
	public void setRow(int r) {
		row =r;	
	}
	public void setColumn(int c) {
		column=c;
	}
	public int getNumberOfMoves(){
		return numberOfMoves;
	}
	public void addOneMove(){
		numberOfMoves++;
	}

	
}