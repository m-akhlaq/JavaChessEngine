package chess;
import pieces.Pawn;
import pieces.Pieces;

public class Chess {

	public static void main(String[] args) {
		
		Pieces board[][]=new Pieces[8][8];
		for (int x=0;x<board[5].length;x++) {
			board[6][x]=new Pawn(1,"1","p"+(x+1));
		}
		renderBoard(board);
		
	}
	
	
	
	public static void renderBoard(Pieces [][] b){
		int color=2;
		for (int x=0;x<b.length;x++){
			for (int y=0;y<b[x].length;y++){
				if (b[x][y]==null){
					if (color%2==0){
						System.out.print("  ");
					}else {
						System.out.print(" ## ");
					}
				}else {
					System.out.print(" "+b[x][y].getName()+"");
				}
				color++;
			}
			
			System.out.println(" "+(8-x)+"");
			color+=1;
		}
		//System.out.println("a  b c  d  e f   g  h");
		
	}

}