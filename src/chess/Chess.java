package chess;
import pieces.Pawn;
import pieces.Pieces;

public class Chess {
	private static Pieces board[][]=new Pieces[8][8];
	
	public static void main(String[] args) {
		
		for (int x=0;x<board[5].length;x++) {
			board[6][x]=new Pawn(1,6,x,"p"+(x+1));
		}
		renderBoard(board);
		System.out.println();
		move("b2","b3");
		renderBoard(board);
		
		
	}
	
	
	private static void move(String currentPos,String newPos){
		int currentCoord[]=convertPosition(currentPos);
		int newCoord[]=convertPosition(newPos);
		Pieces temp = board[currentCoord[0]][currentCoord[1]];
		board[currentCoord[0]][currentCoord[1]]=null;
		board[newCoord[0]][newCoord[1]]=temp;
		
		
	}
	private static int[] convertPosition(String pos){
		pos = pos.toLowerCase();
		char coordinate1=pos.charAt(0);
		char coordinate2=pos.charAt(1);
		int result[]=new int[2];
		result[0]=8-(Character.getNumericValue(coordinate2));
		result[1]=coordinate1-97;
		
		return result;
		
	}
	
	
	private static void renderBoard(Pieces [][] b){
		int color=2;
		for (int x=0;x<b.length;x++){
			for (int y=0;y<b[x].length;y++){
				if (b[x][y]==null){
					if (color%2==0){
						System.out.print("    ");
					}else {
						System.out.print("##  ");
					}
				}else {
					System.out.print(b[x][y].getName()+"  ");
				}
				color++;
			}
			
			System.out.println("   "+(8-x)+"");
			color+=1;
		}
		//System.out.println("a  b c  d  e f   g  h");
		
	}

}