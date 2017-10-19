package chess;
import pieces.King;
import pieces.Pawn;
import pieces.Pieces;
import pieces.Rook;

import static utilities.convertCoordinates.convertPosition;

import java.util.Scanner;

public class Chess {
	private static Pieces board[][]=new Pieces[8][8];
	
	public static void main(String[] args) {
		 int winner=0;
		 int turn=2;
		 
		 //placement of pieces on the board
		 //pawns
		for (int x=0;x<board[5].length;x++) {
			board[6][x]=new Pawn(0,6,x,"wP");
		}
		for (int x=0;x<board[1].length;x++) {
			board[1][x]=new Pawn(1,1,x,"bP");
		}
		//rooks
		board[0][0] = new Rook(1,0,0,"bR");
		board[0][7] = new Rook(1,0,7,"bR");
		board[7][0] = new Rook(0,7,0,"wR");
		board[7][7] = new Rook(0,7,7,"wR");
		//king
		board[0][4] = new King(1,0,4,"bK");
		board[7][4] = new King(0,7,4,"wK");
		
		renderBoard(board);
		System.out.println();
		
		//a loop that keeps going until there is some result
		String s="";
		while (winner==0){
		
		int team;
		//defines whose turn it is
		if (turn%2==0)
			team=0;
		else team=1;
		//reads in the initial input
		
		if (team==0)
			System.out.println("White's move:");
		else System.out.println("Black's move: ");
		Scanner scan = new Scanner(System.in);
			s = scan.nextLine();
		
		//calls the proposedMove method to split the input into current coordinates and proposed coordinates
		String[] proposedMove = splitInput(s);
		//makes the 1x2 array of x,y positions of current coordinates and propsed coordinates.
		int currentCoord[]=convertPosition(proposedMove[0]);
		int newCoord[]=convertPosition(proposedMove[1]);
		Pieces p = board[currentCoord[0]][currentCoord[1]];
		//this while loop runs when either the piece selected in null or the attempted move is illegal. 
		while (p==null || p.canMove(board, newCoord[0], newCoord[1])==false){
		   System.out.println("Invalid Move, try again");
		   	//scanner is reset so new input could be entered
		    scan = new Scanner(System.in);
			s = scan.nextLine();
			proposedMove = splitInput(s);
			//new temperory arrays are defined as a workaround for having an array returned from method convertpostion
			int [] tempCurrentCoord=convertPosition(proposedMove[0]);
			int [] tempNewCoord=convertPosition(proposedMove[1]);
			p=board[tempCurrentCoord[0]][tempCurrentCoord[1]];
			if (p==null)
				continue;
			//sets the values of the temp arrays to the actual coordinate arrays so that the loop could function properly.
			newCoord[0]=tempNewCoord[0];
			newCoord[1]=tempNewCoord[1]; 
			currentCoord[0]=tempCurrentCoord[0];
			currentCoord[1]=tempCurrentCoord[1];
		}
		move(currentCoord,newCoord);
		renderBoard(board);
		turn++;
		
		}
		
	}
	
	private static String[] splitInput(String s){
		s=s.trim();
		String[] result = s.split(" ");
		
		return result;
	}
	
	
	private static void move(int currentCoord[],int newCoord[]){
		//swaps the pieces, places null where currentCoord[] was and places the piece in the new spot.
		Pieces temp = board[currentCoord[0]][currentCoord[1]];
		board[currentCoord[0]][currentCoord[1]]=null;
		board[newCoord[0]][newCoord[1]]=temp;
		board[newCoord[0]][newCoord[1]].setRow(newCoord[0]);
		board[newCoord[0]][newCoord[1]].setColumn(newCoord[1]);
		
		
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