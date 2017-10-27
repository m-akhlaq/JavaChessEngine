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

/**
 * @author Muhammad S. Akhlaq
 * @author John Brauner
 *
 */
public class Chess {
	private static Pieces board[][]=new Pieces[8][8];
	static int [] enpassantCoord = new int[2];
	private static boolean enpassantCounter=false;
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
		if (drawCounter==1 && !s.equals("draw"))
			drawCounter=0;
		if (s.contains("draw?") &&  drawCounter==0){
			drawCounter=1;
			s=s.substring(0,5);
			System.out.println(s);
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
		while (p==null ||  team!= p.getTeam() || p.canMove(board, newCoord[0], newCoord[1])==false){
		   System.out.println("Illegal Move, try again");
		   	//scanner is reset so new input could be entered
		    scan = new Scanner(System.in);
			s = scan.nextLine();
			if (s.contains("draw?") &&  drawCounter==0){
				drawCounter=1;
				s=s.substring(0,5);	
			}
			if (drawCounter==1 && s.equals("draw")){
				winner=3;
				break;
			}
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
		if (findKing(0,board)==null){
			winner=1;
			break;
		}
		if (findKing(1,board)==null){
			winner=0;
			break;
		}
		promotion();
		renderBoard(board);
		
		if (checkForBlackCheckMate()==true){
			System.out.println("CheckMate");
			winner=0;
			break;
		}
		if (checkForWhiteCheckMate()==true){
			System.out.println("CheckMate");
			winner=1;
			break;
		}
		if (checkForBlackCheck(board)==true || checkForWhiteCheck(board)==true  ){
			System.out.println("Check");
		}
		
		
		turn++;
		
		}
		if (winner==0){
			System.out.println("White wins");
		}else if (winner==1){
			System.out.println("Black wins");
		}else {
			System.out.println("draw");

			
		}
		
		}


		
	
	/**
	 * move changes a piece from the current position to the new coordinates
	 * 
	 * @param currentCoord This is an int array of the coordinates of the current position
	 * @param newCoord This is an int array of the coordinates of the new position
	 */
	private static void move(int currentCoord[],int newCoord[]){
		//swaps the pieces, places null where currentCoord[] was and places the piece in the new spot.
		Pieces temp = board[currentCoord[0]][currentCoord[1]];
		
		if (temp instanceof King && Math.abs(currentCoord[1]-newCoord[1])==2){
			if (newCoord[1]>4){
				Pieces rook = board[currentCoord[0]][7];
				board[currentCoord[0]][currentCoord[1]]=null;
				board[newCoord[0]][newCoord[1]]=temp;
				board[newCoord[0]][newCoord[1]].setRow(newCoord[0]);
				board[newCoord[0]][newCoord[1]].setColumn(newCoord[1]);
				board[newCoord[0]][newCoord[1]-1]=rook;
				board[newCoord[0]][newCoord[1]-1].setRow(newCoord[0]);
				board[newCoord[0]][newCoord[1]-1].setColumn(newCoord[1]-1);
				board[newCoord[0]][newCoord[1]-1].addOneMove();
				board[currentCoord[0]][7]=null;
			}else {
				Pieces rook = board[currentCoord[0]][0];
				board[currentCoord[0]][currentCoord[1]]=null;
				board[newCoord[0]][newCoord[1]]=temp;
				board[newCoord[0]][newCoord[1]].setRow(newCoord[0]);
				board[newCoord[0]][newCoord[1]].setColumn(newCoord[1]);
				board[newCoord[0]][newCoord[1]+1]=rook;
				board[newCoord[0]][newCoord[1]+1].setRow(newCoord[0]);
				board[newCoord[0]][newCoord[1]+1].setColumn(newCoord[1]+1);
				board[newCoord[0]][newCoord[1]+1].addOneMove();
				board[currentCoord[0]][0]=null;
			}
			
			
		}else{
		board[currentCoord[0]][currentCoord[1]]=null;
		board[newCoord[0]][newCoord[1]]=temp;
		board[newCoord[0]][newCoord[1]].setRow(newCoord[0]);
		board[newCoord[0]][newCoord[1]].setColumn(newCoord[1]);
		}
		if (enpassantCounter==true){
			int [] originalCoord = new int[2];
			if (enpassantCoord[0]==2){
				originalCoord[0]=enpassantCoord[0]+1;
				originalCoord[1]=enpassantCoord[1];
			}else{
				originalCoord[0]=enpassantCoord[0]-1;
				originalCoord[1]=enpassantCoord[1];
			}
			
			if (board[enpassantCoord[0]][enpassantCoord[1]] instanceof Pawn){
				board[originalCoord[0]][originalCoord[1]]=null;
			}else{
				if (board[originalCoord[0]][originalCoord[1]] instanceof Pawn)
					((Pawn)board[originalCoord[0]][originalCoord[1]]).setEnpassant(false);
			}
			enpassantCounter=false;
			
		}
		
	if (board[newCoord[0]][newCoord[1]] instanceof Pawn && Math.abs(currentCoord[0]-newCoord[0])==2){
		   if (board[newCoord[0]][newCoord[1]].getTeam()==0){
				enpassantCoord[0]=newCoord[0]+1;
			    enpassantCoord[1]=newCoord[1];
			}else{
				enpassantCoord[0]=newCoord[0]-1;
				enpassantCoord[1]=newCoord[1];
			}
		   ((Pawn) board[newCoord[0]][newCoord[1]]).setEnpassant(true);
		   enpassantCounter=true;	
		}
		
		board[newCoord[0]][newCoord[1]].addOneMove();
	}

	/**
	 * renderBoard prints out a command line version of the chess board
	 * 
	 * @param b This is a 2D array of Pieces and the current board.
	 */
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
			
			System.out.println(""+(8-x)+"");
			color+=1;
		}
		
		System.out.print(" a  ");
		System.out.print(" b  ");
		System.out.print(" c  ");
		System.out.print(" d  ");
		System.out.print(" e  ");
		System.out.print(" f  ");
		System.out.print(" g  ");
		System.out.print(" h  ");
		System.out.println();

	}
	/**
	 * promotion changes the value of a pawn once it reaches the other end of the board to a queen.
	 * 
	 */
	private static void promotion(){
		for (int x=0;x<=7;x++){
			if (board[0][x]!=null && board[0][x] instanceof Pawn &&  board[0][x].getTeam()==0)
				board[0][x]=new Queen(0,0,x,"wQ");
			if (board[7][x]!=null && board[7][x] instanceof Pawn && board[7][x].getTeam()==1)
				board[7][x]=new Queen(1,7,x,"bQ");
				
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
		for(Coordinates c:allWhiteMoves){
			if (c.equals(blackKing)){
				return true;
			}
		}
		return false;
	}
	/**
	 * checkForWhiteCheck returns whether black is in check.
	 * 
	 * @param board This is a 2D array of Pieces and the current board.
	 * @returns whether white is in check.
	 */
	
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
				return true;
			}
		}

		
		return false;
		
	}
	
	/**
	 * checkForBlackCheckMate returns whether black is in check mate. It first checks if black is in check.
	 * 
	 * @returns whether black is in check mate.
	 */
	private static boolean checkForBlackCheckMate(){
		//checking if black king is in check mate.
		
		//first we check if the king is under direct attack
		if (checkForBlackCheck(board)==false)
			return false;
		
		
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y]!=null && board[x][y].getTeam()==1){
					Pieces p = board[x][y];
					ArrayList<Coordinates> allMoves = board[x][y].allValidMoves(board);
					if (simulateForCheckMate(allMoves,p.getRow(),p.getColumn(),board,1)==true)
						return false;
					
				}
			}
		}
		
		
		return true;
	}
	
	/**
	 * checkForWhiteCheckMate returns whether black is in check mate. It first checks if white is in check.
	 * 
	 * @returns whether white is in check mate.
	 */
	private static boolean checkForWhiteCheckMate(){
		//checking if white king is in check mate.
		
		//first we check if the king is under direct attack, if not there is not checkmate
		if (checkForWhiteCheck(board)==false)
			return false;
		
		//the following code only runs if the king is in check (as it checks weather the check is just a check or a checkmate)
		
		//the logic of the algo is as follows: The loop below loops through the entire board and finds every white piece (if we are checking for white checkmate) 
		//or black piece (if we are checking for black checkmate)(including tha king)
		//and gets a list of all valid moves that they can make. Then it passes the coordinates of the current piece and the list of all valid moves it can make to
		//the simulateForcheckmate method. This method makes a copy of the board and actually makes each possible move to check weather that move
		//can get the king out of the check (including the king himself going through all his valid moves). If at any point, the simulateforcheck
		//method finds that a valid move has removed that king from check, this method returns false as the king is not in checkmate. If no
		//such move can be found, the king is in a checkmate!
		for (int x=0;x<=7;x++){
			for (int y=0;y<=7;y++){
				if (board[x][y]!=null && board[x][y].getTeam()==0){
					Pieces p = board[x][y];
					ArrayList<Coordinates> allMoves = board[x][y].allValidMoves(board);
					if (simulateForCheckMate(allMoves,p.getRow(),p.getColumn(),board,0)==true)
						return false;
					
				}
			}
		}
		
		
		return true;
	}
	///simulates all moves one single piece can make to make sure that particular piece cannot break the checkmate
	private static boolean simulateForCheckMate(ArrayList<Coordinates> allMoves,int r,int cl,Pieces originalBoard[][],int teamCheck){
		//makes a deep copy of the original board so that the original isnt modified
		Pieces[][] copyBoard=makeDeepCopy(originalBoard);
		//piece whose all moves will be checked
		Pieces cp = copyBoard[r][cl];
		//System.out.println("All Moves "+allMoves);
	///loops through every move that piece
		for(Coordinates c:allMoves){
			//resets the board and the piece after each move
			copyBoard = makeDeepCopy(originalBoard);
			cp=copyBoard[r][cl];
			//manually moves the piece to one of its possible valid moves
			copyBoard[c.getRow()][c.getColumn()]=cp;
			copyBoard[cp.getRow()][cp.getColumn()]=null;
			cp.setRow(c.getRow());
			cp.setColumn(c.getColumn());
			///this checks weather any move made by this piece can break the check. if the checkForBlackCheck or the white method return false
			///this would indicate that a move that this piece made broke that check meaning the king is not is a checkmate 
			if (teamCheck==1){
				//checks if the valid move broke the check
			if (checkForBlackCheck(copyBoard)==false){
				return true;
			}
			}else if (teamCheck==0){
				if (checkForWhiteCheck(copyBoard)==false){
					return true;
			}
			}
			
		}
		return false;
	}
	
	
	/**
	 * findKing returns the king of a given team.
	 * 
	 * @param team The team of the king being looked for.
	 * @param board This is a 2D array of pieces and the current board.
	 * @returns The coordinates of the king of a given team.
	 */
	//return the coordinates of the king that is specified by the team argument
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
	//used for custom testing. DELETE THIS
	private static void checkMateTest(){
		board[7][4] = new King(0,7,4,"wK");
		board[0][4] = new King(1,0,4,"bK");
		board[7][7] = new Rook(0,7,7,"wR");
		board[0][0] = new Rook(1,0,0,"bR");
		board[7][1] = new Pawn(1,7,1,"bP");
		//board[6][6] = new Pawn(1,6,6,"wP");
		renderBoard(board);
		System.out.println();
	}
	
	/**
	 * initBoard initializes the board by putting each piece in its starting position.
	 * 
	 */
	//initilized the board
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
	
	/**
	 * makeDeepCopy is a utility method that makes a deep copy of the passed array.
	 * 
	 * @returns a deep copy of the board.
	 */
	//A utility method that makes a deep copy of the passed array.
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
	
	/**
	 * splitInput splits a string into a String array.
	 * 
	 * @param This is a string.
	 * @returns an array of strings.
	 */
	private static String[] splitInput(String s){
		s=s.trim();
		String[] result = s.split(" ");
		
		return result;
	}

}