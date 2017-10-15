package pieces;

public class Pawn extends Pieces{

	public Pawn(int team, int row,int column, String name) {
		super(team, row,column, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pieces[][] move(Pieces[][] board, int r,int c) {
		
		
		
		return new Pieces[2][2];
		
	}

	@Override
	public String getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}