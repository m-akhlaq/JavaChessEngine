package chess;
import pieces.Pieces;

public class Chess {

	public static void main(String[] args) {
		
		Pieces board[][]=new Pieces[8][8];
		renderBoard(board);
	}
	
	
	
	public static void renderBoard(Pieces [][] b){
		int color=2;
		for (int x=0;x<b.length;x++){
			for (int y=0;y<b[x].length;y++){
				if (b[x][y]==null){
					if (color%2==0){
						System.out.print("   ");
					}else
						System.out.print("##");
				}
				color++;
			}
			
			System.out.println(" "+(8-x));
			color+=1;
		}
		System.out.println("a  b c  d  fadssde f   g  h");
		
	}

}
