package chess;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Pieces;
import pieces.Queen;
import pieces.Rook;
import utilities.Coordinates;
import static utilities.convertCoordinates.convertPosition;

import java.util.ArrayList;
import java.util.Scanner;

public class Chess {
	private static Pieces board[][]=new Pieces[8][8];
	
	public static void main(String[] args) {
		 int winner=0;
		 int turn=2;
		 int drawCounter=0;
		 initBoard();
		
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
		//checks if the users asks for a draw
		if (s.equals("draw?")){
			drawCounter=1;
			turn++;
			continue;
		}
		if (drawCounter==1 && s.equals("draw")){
			winner=3;
			break;
		}
		if (s.equals("resign")){
			if (team==0){
				winner=1;
				break;
			}
			if (team==1){
				winner=0;
				break;
			}
			
			}
		
		
		
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
			//new temperory arrays are defined as a workaround for having an array returned from method convert postion
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

		if (checkForCheckMate()==true){
			System.out.println("CheckMate");
			break;
		}
		
		checkForCheck(board);
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
	
	private static boolean checkForBlackCheck(Pieces[][] board){
		ArrayList<Coordinates> allWhiteMoves = new ArrayList<Coordinates>();
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y]!=null && board[x][y].getTeam()==0){
					allWhiteMoves.addAll(board[x][y].allValidMoves(board));
				}
			}
		}
		Coordinates blackKing = findKing(1,board);
	//	System.out.println("black King is at "+ blackKing );
		//System.out.println("All White Moves are "+ allWhiteMoves);
		

		for(Coordinates c:allWhiteMoves){
			if (c.equals(blackKing)){
				System.out.println("black King in Check");
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkForWhiteCheck(Pieces[][] board){
		ArrayList<Coordinates> allBlackMoves = new ArrayList<Coordinates>();
		
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y]!=null && board[x][y].getTeam()==1){
					allBlackMoves.addAll(board[x][y].allValidMoves(board));
				}
			}
		}
		
		Coordinates whiteKing = findKing(0,board);
		
		for(Coordinates c:allBlackMoves){
			if (c.equals(whiteKing)){
				//System.out.println("White King in Check");
				return true;
			}
		}

		
		return false;
		
	}
	
	
	private static boolean checkForCheckMate(){
		//checking if black king is in check mate.
		
		//first we check if the king is under direct attack
		if (checkForBlackCheck(board)==false)
			return false;
		
		
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y]!=null && board[x][y].getTeam()==1){
					Pieces p = board[x][y];
					ArrayList<Coordinates> allMoves = board[x][y].allValidMoves(board);
					if (simulateForCheckMate(allMoves,p.getRow(),p.getColumn(),board)==true)
						return false;
					
				}
			}
		}
		
		
		return true;
	}
	private static Pieces[][] makeDeepCopy(Pieces[][] p){
		Pieces[][] copy = new Pieces[8][8];
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				Pieces a = p[x][y];
				if (a==null)
					copy[x][y]=null;
				if (a instanceof Pawn)
					copy[x][y]=new Pawn((Pieces)a);
				if (a instanceof Bishop)
					copy[x][y]=new Bishop((Pieces)a);
				if (a instanceof King)
					copy[x][y]=new King((Pieces)a);
				if (a instanceof Knight)
					copy[x][y]=new Knight((Pieces)a);
				if (a instanceof Queen)
					copy[x][y]=new Queen((Pieces)a);
				if (a instanceof Rook)
					copy[x][y]=new Rook((Rook)a);
			}
			
		}
		return copy;
	}
	private static boolean simulateForCheckMate(ArrayList<Coordinates> allMoves,int r,int cl,Pieces originalBoard[][]){
		Pieces[][] copyBoard=makeDeepCopy(originalBoard);
		Pieces cp = copyBoard[r][cl];
		//System.out.println("All Moves "+allMoves);
	
		for(Coordinates c:allMoves){
			
			copyBoard = makeDeepCopy(originalBoard);
			cp=copyBoard[r][cl];
			copyBoard[c.getRow()][c.getColumn()]=cp;
			copyBoard[cp.getRow()][cp.getColumn()]=null;
			cp.setRow(c.getRow());
			cp.setColumn(c.getColumn());
			if (checkForBlackCheck(copyBoard)==false){
				System.out.println("Saving Coordinate "+c);
				return true;
			}
			
		}
		return false;
	}
	
	private static Coordinates findKing(int team,Pieces board[][]){
		
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y] instanceof King && board[x][y].getTeam()==team){
					return new Coordinates(x,y);
				}
			}	
		}
		
		return null; 
	}
	private static void checkMateTest(){
		board[3][7] = new King(1,3,7,"bK");
		board[4][5] = new King(0,4,5,"wK");
		board[7][6] = new Rook(0,7,6,"wR");
		board[6][6] = new Pawn(1,6,6,"wP");
		renderBoard(board);
		System.out.println();
	}
	private static void initBoard(){
		//rooks
		board[0][0] = new Rook(1,0,0,"bR");
		board[0][7] = new Rook(1,0,7,"bR");
		board[7][0] = new Rook(0,7,0,"wR");
		board[7][7] = new Rook(0,7,7,"wR");
		//king
		board[0][4] = new King(1,0,4,"bK");
		board[7][4] = new King(0,7,4,"wK");
		//bishop
		board[7][2] = new Bishop(0,7,2,"wB");
		board[7][5] = new Bishop(0,7,5,"wB");
		board[0][2] = new Bishop(1,0,2,"bB");
		board[0][5] = new Bishop(1,0,5,"bB");
		//knight
		board[7][1] = new Knight(0,7,1,"wN");
		board[7][6] = new Knight(0,7,6,"wN");
		board[0][1] = new Knight(1,0,1,"bN");
		board[0][6] = new Knight(1,0,6,"bN");
		//Queen
		board[7][3] = new Queen(0,7,3,"wQ");
		board[0][3] = new Queen(1,0,3,"bQ");
		 //pawns
		for (int x=0;x<board[5].length;x++) {
			board[6][x]=new Pawn(0,6,x,"wP");
		}
		for (int x=0;x<board[1].length;x++) {
			board[1][x]=new Pawn(1,1,x,"bP");
		}

		renderBoard(board);
		System.out.println();
	}

}