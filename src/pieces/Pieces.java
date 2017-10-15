package pieces;

public abstract class Pieces {
	
	
	int team;
	String position;
	String name;
	public Pieces(int team,String position,String name){
		 this.team= team;
		 this.position=position;
		 this.name=name;
		 }

	public abstract void move(Pieces[][]board,String pos);
	public abstract String getPosition();
	public abstract String getName();
	
	
}