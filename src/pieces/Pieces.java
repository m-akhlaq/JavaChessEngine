package pieces;

public abstract class Pieces {
	
	
	int team;
	int row,column;
	String name;
	public Pieces(int team,int row,int column,String name){
		 this.team= team;
		 this.row=row;
		 this.column=column;
		 this.name=name;
		 }

	public abstract Pieces[][] move(Pieces[][]board,int r,int c);
	public abstract String getPosition();
	public abstract String getName();
	
	
}