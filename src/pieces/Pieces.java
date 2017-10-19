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
	
	public String getPosition() {
		return null;
	}
	public int getTeam(){
		return team;
	}
	public String getName() {
		return name;
	}
	
	
	public void setRow(int r) {
		row =r;	
	}
	public void setColumn(int c) {
		column=c;
	}

	
}