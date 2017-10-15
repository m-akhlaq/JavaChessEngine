package chess;
import pieces.Pawn;
import pieces.Pieces;
import static utilities.convertCoordinates.convertPosition;

public class Chess {
	private static Pieces board[][]=new Pieces[8][8];
	
	public static void main(String[] args) {
		
		for (int x=0;x<board[5].length;x++) {
			board[6][x]=new Pawn(0,6,x,"p"+(x+1));
		}
		
		board[5][1]=new Pawn(1,6,1,"t"+(1+1));
		board[5][2]=new Pawn(1,6,1,"t"+(1+1));
		renderBoard(board);
		System.out.println();
		int currentCoord[]=convertPosition("b2");
		int newCoord[]=convertPosition("c3");
		Pieces p = board[currentCoord[0]][currentCoord[1]];
		System.out.println(currentCoord[0]+" "+currentCoord[1]);
		if (p.canMove(board, newCoord[0], newCoord[1]))
		   move(currentCoord,newCoord);
		else System.out.println("invalid ");
		renderBoard(board);
		
		
	}
	
	
	private static void move(int currentCoord[],int newCoord[]){
		Pieces temp = board[currentCoord[0]][currentCoord[1]];
		board[currentCoord[0]][currentCoord[1]]=null;
		board[newCoord[0]][newCoord[1]]=temp;
		
		
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
		
	}

}