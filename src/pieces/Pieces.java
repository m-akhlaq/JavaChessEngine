package pieces;

public abstract class Pieces {
	
	
	protected int team;
	protected int row,column;
	protected String name;
	public Pieces(int team,int row,int column,String name){
		 this.team= team;
		 this.row=row;
		 this.column=column;
		 this.name=name;
		 }

	public abstract boolean canMove(Pieces[][]board,int r,int c);
	public abstract String getPosition();
	public abstract String getName();
	public abstract int getTeam();
	public abstract void setRow(int r);
	public abstract void  setColumn(int c);
	
}