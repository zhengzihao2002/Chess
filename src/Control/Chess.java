package Control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Model.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 @since 2023-03-09
 @author Yiming Huang
 @author Zihao Zheng
 The Chess class represents a game of chess. It holds the current state of the game,
 including the chessboard, the current player's turn, and whether the game has ended or not.
 This class contains methods for initializing the game, printing the chessboard, and making moves.
 */

public class Chess {
	/**
	 * The mode of the game. 
	 * File: read from file. 
	 * terminal: read from terminal
	 * */
	static String mode = "terminal";
	
	/**
	 * Debug mode
	 * if set to true, then it will print out specific error messages
	 * */
	static boolean debug = false;
	
	/**
	 * The chess board 8x8
	 * */
	static ChessPiece[][] board = new ChessPiece[8][8];
	
	/**
	 * Whether it is the white side's move
	 * */
	static boolean isWhiteMove = true;
	
	/**
	 * Whether the game has ended
	 * */
	static boolean gameEnd = false;

	/**
	Initializes the board with the chess pieces in their starting positions.
	*/
	public static void initChess() {
		// Set up the initial state of the board
		board[0][0] = new Rook("black", 1, 1);
		board[0][1] = new Knight("black", 1, 2);
		board[0][2] = new Bishop("black", 1, 3);
		board[0][3] = new Queen("black", 1, 4);
		board[0][4] = new King("black", 1, 5);
		board[0][5] = new Bishop("black", 1, 6);
		board[0][6] = new Knight("black", 1, 7);
		board[0][7] = new Rook("black", 1, 8);
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn("black", 2, i + 1);
		}
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn("white", 7, i + 1);
		}
		board[7][0] = new Rook("white", 8, 1);
		board[7][1] = new Knight("white", 8, 2);
		board[7][2] = new Bishop("white", 8, 3);
		board[7][3] = new Queen("white", 8, 4);
		board[7][4] = new King("white", 8, 5);
		board[7][5] = new Bishop("white", 8, 6);
		board[7][6] = new Knight("white", 8, 7);
		board[7][7] = new Rook("white", 8, 8);

		ChessPiece.board = board;

	}
	

	/**
	Prints the current state of the chess board.
	Chess pieces are represented by their symbols, and are preceded by a letter indicating their color.
	Empty spots on the board are represented by two hash signs (##).
	If a spot has a chess piece on it, and it is a black piece, it is represented by a 'b' followed by the piece symbol.
	If a spot has a chess piece on it, and it is a white piece, it is represented by a 'w' followed by the piece symbol.
	If a spot does not have a chess piece on it, and it is an even numbered row and column, it is represented by three empty spaces.
	If a spot does not have a chess piece on it, and it is an odd numbered row and column, it is represented by two hash signs.
	*/
	public static void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if (board[i][j] != null) {
					// There exists a chess piece in this spot
					if (piece.getColor().equals("black")) {
						System.out.print("b" + piece.getSymbol() + " ");
					} else {
						System.out.print("w" + piece.getSymbol() + " ");
					}
				} else {
					// These isn't any chess piece in this spot
					if ((i + j) % 2 == 0) {
						// create shadow
						System.out.print("   ");
					} else {
						// no shadow
						System.out.print("## ");
					}
				}
			}
			System.out.println(8 - i);
		}
		System.out.println(" a  b  c  d  e  f  g  h ");
	}

	
	/**

	Returns a list of all chess pieces on the board that belong to the specified color.
	@param color of the pieces to retrieve (either "white" or "black")
	@return a list of ChessPiece objects that belong to the specified color
	*/
	public static List<ChessPiece> getPiecesOfColor(String color) {
		List<ChessPiece> pieces = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if (piece != null && piece.getColor().equals(color)) {
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}

	
	/**

	Returns a list of valid moves for a king at the given position on an 8x8 chess board.
	The method checks all 8 possible moves from the king's position and adds any valid moves to the list of valid moves.
	@param kingRow the row position of the king on the board
	@param kingCol the column position of the king on the board
	@return a list of int arrays representing the row and column positions of the valid moves for the king
	*/
	public static List<int[]> getValidKingMoves(int kingRow, int kingCol) {
		List<int[]> validMoves = new ArrayList<>();

		// Check each of the 8 possible moves from the king's position
		int[][] moves = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		for (int[] move : moves) {
			int newRow = kingRow + move[0];
			int newCol = kingCol + move[1];
			if (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7) {
				validMoves.add(new int[] { newRow, newCol });
			}
		}

		return validMoves;
	}

	
	/**
	Returns a list of all the chess pieces on the board that have the specified color.
	@param color the color of the chess pieces to retrieve (either "white" or "black")
	@return a list of all the chess pieces on the board with the specified color
	*/
	public static List<ChessPiece> getAllPiecesOfColor(String color) {
		List<ChessPiece> piecesOfColor = new ArrayList<>();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = board[row][col];
				if (piece != null && piece.getColor().equals(color)) {
					piece.setPosition(row + 1, col + 1);// rewrite position to avoid bugs
					piecesOfColor.add(piece);
				}
			}
		}
		return piecesOfColor;
	}

	
	/**
	Checks whether a king of the given color is in check.
	@param color the color of the king to check for check
	@return true if the king is in check, false otherwise
	*/
	public static boolean isChecked(String color) {
		// Find the king's position
		int kingRow = -1;
		int kingCol = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if (piece != null && piece.getSymbol().equals("K") && piece.getColor().equals(color)) {
					kingRow = i;
					kingCol = j;
					break;
				}
			}
			if (kingRow != -1) {
				break;
			}
		}
		// Check if any opponent piece can attack the king
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ChessPiece piece = board[i][j];
				if (piece != null && !piece.getColor().equals(color)) {
					if (piece.isValidMove(i, j, kingRow, kingCol)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	
	
	/**
	 Checks if the given color is in checkmate.
	 @param color the color to check for checkmate
	 @return true if the given color is in checkmate, false otherwise
	 */
	public static boolean isCheckmate(String color) {
		// Find the king's position on the board
		int kingRow = -1;
		int kingCol = -1;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ChessPiece piece = board[row][col];
				if (piece instanceof King && piece.getColor().equals(color)) {
					kingRow = row;
					kingCol = col;
					break;
				}
			}
			if (kingRow != -1 && kingCol != -1) {
				break;
			}
		}
		// If the king is not on the board, return false
		if (kingRow == -1 || kingCol == -1) {
			return false;
		}

		// Get the possible positions the king can move to in the next round, suppose
		// all of them are empty
		List<int[]> validMoves = getValidKingMoves(kingRow, kingCol);
		List<ChessPiece> enemyPiece = getAllPiecesOfColor(color == "white" ? "black" : "white");

		// Find and remove valid positions, that has pieces of own color already in it
//		Iterator<int[]> iterator = validMoves.iterator();
//		while (iterator.hasNext()) {
//		    int[] move = iterator.next();
//		    int destRow = move[0];
//		    int destCol = move[1];
//		    
//		    //  Remove this move since the king cannot eat a piece of its own color
//		    if (board[destRow][destCol]!=null&&board[destRow][destCol].getColor().equals(color)) {
//		    	iterator.remove();
//		    }
//		}

		// Find and remove valid positions that enemy pieces can go to
		boolean enemyReady = false;
		for (ChessPiece piece : enemyPiece) {
			Iterator<int[]> iterator2 = validMoves.iterator();
			while (iterator2.hasNext()) {
				int[] move = iterator2.next();
				int row = move[0];
				int col = move[1];

				// Remove this move since the king can be captured if it was to go there
				if (row >= 0 && row < 8 && col >= 0 && col < 8
						&& (piece instanceof Pawn ? ((Pawn) piece).isValidTest(row, col)
								: piece.isValidMove(row, col))) {
					iterator2.remove();
					enemyReady=true;
//					System.out.println(row + "," + col + " can be occupied by " + piece.getColor() + piece.getSymbol()
//							+ (piece.getRow()-1) + "," + (piece.getColumn()-1)+":"+(piece instanceof Pawn ? ((Pawn) piece).isValidTest(row, col)
//									: piece.isValidMove(row, col)));
				}
			}
		}
//		System.out.println(validMoves.size());
		int validEscapeRoute=validMoves.size();
		
		if(validEscapeRoute>0) {
			// Until here, a validEscapeRoute MAY contain a piece, that shouldn't be a validEscapeRoute
			// if king cannot perform all the validMoves, then also return true: must all be nonvalid moves
			for (int[] move : validMoves) {
			    int row = move[0];
			    int col = move[1];
			    if(!board[kingRow][kingCol].isValidMove(row, col)) {
			    	validEscapeRoute--;
			    }
			}
		}
		if (validEscapeRoute <= 0&&enemyReady==true) {
			return true;//king is surrounded
		}
		return false; // King is not surrounded
		
	}

	
	/**
	Reverses the current player's turn by flipping the value of the "isWhiteMove" field.
	This method assumes that the current turn belongs to the player whose value of x "isWhiteMove" is true.
	*/
	private static void reverseMove() {
		if (isWhiteMove) {
			isWhiteMove = false;
		} else {
			isWhiteMove = true;
		}
	}

	
	/**
	Prints the statement indicating the player whose turn it is, and then flips the
	value of the "isWhiteMove" field to indicate that it is the other player's turn next.
	This method assumes that the current turn belongs to the player whose value of
	"isWhiteMove" is true.
	*/
	private static void printMoveStatement() {
		if (isWhiteMove) {
			System.out.print("White's move: ");
			isWhiteMove = false;
		} else {
			System.out.print("Black's move: ");
			isWhiteMove = true;
		}
	}

	
	/**
	Checks if the given move is a resignation by either player. If the move is a
	resignation, this method prints the name of the winning player and returns true.
	Otherwise, it returns false. This method assumes that the current turn belongs to
	the player whose value of "isWhiteMove" is true.
	@param move a string representing the player's move
	@return true if the move is a resignation, false otherwise
	*/
	private static boolean isResign(String move) {
		if (move.equals("resign") && isWhiteMove) {
			System.out.println("White wins");
			return true;
		} else if (move.equals("resign") && !isWhiteMove) {
			System.out.println("Black wins");
			return true;
		}
		return false;
	}

	
	/**
	Converts a position in algebraic notation to a corresponding array index on the
	chessboard. The algebraic notation is assumed to be in the format of a lowercase
	letter representing the file (column) followed by a digit representing the rank (row).
	This method returns an int array containing the row and column indices on the chessboard.
	If the input position is invalid or out of bounds, this method returns {-1, -1}.
	@param position a string representing a chess position in algebraic notation
	@return anint array containing the row and column indices on the chessboard, or {-1, -1}
	*/
	public static int[] convertPosition(String position) {
		int x = -1;
		int y = -1;
		char fileChar = position.charAt(0);
		char rankChar = position.charAt(1);
		if (fileChar < 'a' || fileChar > 'h' || rankChar < '1' || rankChar > '8' || !Character.isLowerCase(fileChar)) {
			return new int[] { -1, -1 };
		}
		x = '8' - rankChar;
		y = fileChar - 'a';
		return new int[] { x, y };
	}

	
	
	/**
	 * Moves a chess piece from the specified starting position to the specified
	 * ending position on the board. The piece is removed from its starting position
	 * and placed on its ending position. If there is an enemy piece on the ending
	 * position, it is captured and removed from the board.
	 * 
	 * @param fromRow the row index of the starting position (0-7)
	 * @param fromColumn the column index of the starting position (0-7)
	 * @param toRow the row index of the ending position (0-7)
	 * @param toColumn the column index of the ending position (0-7)
	 * @throws ArrayIndexOutOfBoundsException if any of the position indices is out
	 *         of bounds
	 */
	private static void move(int fromRow, int fromColumn, int toRow, int toColumn) {
		String color = board[fromRow][fromColumn].getColor();
		int row = board[fromRow][fromColumn].getRow();
		int column = board[fromRow][fromColumn].getColumn();

		if (board[fromRow][fromColumn].getSymbol().equals("B")) {
			board[toRow][toColumn] = new Bishop(color, row, column);
		} else if (board[fromRow][fromColumn].getSymbol().equals("K")) {
			board[toRow][toColumn] = new King(color, row, column);
		} else if (board[fromRow][fromColumn].getSymbol().equals("N")) {
			board[toRow][toColumn] = new Knight(color, row, column);
		} else if (board[fromRow][fromColumn].getSymbol().equals("Q")) {
			board[toRow][toColumn] = new Queen(color, row, column);
		} else if (board[fromRow][fromColumn].getSymbol().equals("R")) {
			board[toRow][toColumn] = new Rook(color, row, column);
		} else {
			// check if destination is emtpy and under/above (depending on color) is non
			// empty: if so then remove it
			if (((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO && board[toRow][toColumn] == null
					&& isDiagonalMove(fromRow, fromColumn, toRow, toColumn)) {
				// identify color
				if (board[fromRow][fromColumn].getColor().equals("white")) {
					// white
					if (board[toRow + 1][toColumn] != null) {
						board[toRow + 1][toColumn] = null;
					}
				} else {
					// black
					if (board[toRow - 1][toColumn] != null) {
						board[toRow - 1][toColumn] = null;
					}
				}
			}

			board[toRow][toColumn] = new Pawn(color, row, column);
			Pawn obj = ((Pawn) board[toRow][toColumn]);
			if (isDiagonalMove(fromRow, fromColumn, toRow, toColumn)) {
				// Pawn has moved diagonal
				obj.onlyVertical = false;
			} else {
				// Inherit the old onlyVertical from old pawn object
				obj.onlyVertical = ((Pawn) board[fromRow][fromColumn]).onlyVertical;
			}
			obj.moveTwoAlready = ((Pawn) board[fromRow][fromColumn]).moveTwoAlready;

			// Check left and right for enemy pawn, if so: set their enpassant to true if
			// their isVertical is true and i have only move once (2 forward)
			if (toColumn - 1 >= 0 && toColumn - 1 <= 7 && board[toRow][toColumn - 1] != null
					&& board[toRow][toColumn - 1].getSymbol().equals("p")
					&& board[toRow][toColumn - 1].getColor() != obj.getColor()
					&& ((Pawn) board[toRow][toColumn - 1]).onlyVertical && obj.moveTwoAlready && obj.firstMove) {
				((Pawn) board[toRow][toColumn - 1]).en_passant_GOGOGO = true;
//				System.out.println("Left enemy set to TRUE");
			} else if (toColumn + 1 >= 0 && toColumn + 1 <= 7 && board[toRow][toColumn + 1] != null
					&& board[toRow][toColumn + 1].getSymbol().equals("p")
					&& board[toRow][toColumn + 1].getColor() != obj.getColor()
					&& ((Pawn) board[toRow][toColumn + 1]).onlyVertical && obj.moveTwoAlready && obj.firstMove) {
				((Pawn) board[toRow][toColumn + 1]).en_passant_GOGOGO = true;
//				System.out.println("RIGHT enemy set to TRUE");
			}

			if (obj.firstMove == true) {
				obj.firstMove = false;
			}
			// Losing en passant chance
			if (obj.en_passant_GOGOGO == true) {
				obj.en_passant_GOGOGO = false;
			}
		}
//		System.out.println("from "+x+" "+y+"| to "+(toRow+1)+" "+(toColumn+1));
		board[toRow][toColumn].setPosition(toRow + 1, toColumn + 1);
		board[fromRow][fromColumn] = null;
	}

	
	/**
	Checks if a pawn has reached the opposite end of the board and is eligible for promotion
	@param toRow The row where the pawn has moved to
	@param color The color of the pawn
	@return true if the pawn has reached the opposite end of the board and is eligible for promotion, false otherwise
	*/
	public static boolean checkPromote(int toRow, String color) {
		if (toRow == 0 && color.equals("white")) {
			return true;
		} else if (toRow == 7 && color.equals("black")) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	Promotes a pawn to a new piece type.
	@param fromRow the row of the pawn to be promoted
	@param fromColumn the column of the pawn to be promoted
	@param promoteTo the new piece type to promote the pawn to (B, N, Q, or R)
	*/
	public static void promote(int fromRow, int fromColumn, String promoteTo) {
		String color = board[fromRow][fromColumn].getColor();
		int row = board[fromRow][fromColumn].getRow();
		int column = board[fromRow][fromColumn].getColumn();

		if (promoteTo.equals("B")) {
			board[fromRow][fromColumn] = new Bishop(color, row, column);
		} else if (promoteTo.equals("N")) {
			board[fromRow][fromColumn] = new Knight(color, row, column);
		} else if (promoteTo.equals("Q")) {
			board[fromRow][fromColumn] = new Queen(color, row, column);
		} else if (promoteTo.equals("R")) {
			board[fromRow][fromColumn] = new Rook(color, row, column);
		}
	}

	
	
	/**
	Determines if a move from the given starting position to the given ending position
	is a diagonal move for a pawn. A diagonal move is defined as moving exactly one column to the left
	or right and one row forward (for a white pawn) or backward (for a black pawn).
	@param fromRow the starting row of the move
	@param fromColumn the starting column of the move
	@param toRow the ending row of the move
	@param toColumn the ending column of the move
	@return true if the move is a diagonal move for a pawn, false otherwise
	*/
	public static boolean isDiagonalMove(int fromRow, int fromColumn, int toRow, int toColumn) {
		int rowDiff = toRow - fromRow;
		int colDiff = toColumn - fromColumn;
		int forwardDir = board[fromRow][fromColumn].getColor().equals("white") ? -1 : 1;
		if (Math.abs(colDiff) == 1 && rowDiff == forwardDir) {
			return true;
		} else {
			return false;
		}
	}

	
	
	/**
	This class contains a method that runs a chess game using the console as the
	user interface. It prompts the user to enter their moves via the console, and
	validates each move before making it on the board. It also handles
	pawn promotion, and checking for a draw or resignation. The game continues
	until a player wins, resigns, or the user enters "draw?".
	*/
	public static void runner1() {
		// This is running the program by taking in inputs in the terminal

		Scanner scanner = new Scanner(System.in);
		boolean printStatus = true;

		// Runner
		while (true) {
			// print the board
			if (printStatus == true) {
				printBoard();
			} else if (printStatus == false) {
				printStatus = true;
			}

			// determine who should move
			printMoveStatement();

			// get the move coordinate
			String move = scanner.nextLine();

			// Check Resign
			if (isResign(move) == true)
				break;

			// Split the move coordinate into from and to
			if (move.split(" ").length < 2 || move.split(" ").length > 3 || move.split(" ")[0].length() != 2
					|| move.split(" ")[1].length() != 2) {
				if(debug==true) {
					System.out.println("Illegal move, try again : Wrong format");
				}else {
					System.out.println("Illegal move, try again.");
				}
				printStatus = false;
				reverseMove();
				continue;
			}
			String from = move.split(" ")[0];
			String to = move.split(" ")[1];
			String promotion = null;
			if (move.split(" ").length == 3) {
				promotion = move.split(" ")[2];
			}


			// check if destination and "from" is the same thing
			if (from.equals(to)) {
				if(debug==true) {
					System.out.println("Illegal move, try again : Destination cannot be same as source");
				}else {
					System.out.println("Illegal move, try again.");
				}
				printStatus = false;
				reverseMove();
				continue;
			}

			// get the position in the array
			int fromRow = convertPosition(from)[0];
			int fromColumn = convertPosition(from)[1];
			int toRow = convertPosition(to)[0];
			int toColumn = convertPosition(to)[1];

			// Check if the from and to coordinates are -1 (meaning the coordinate is
			// invalid)
			if (fromRow == -1 || fromColumn == -1 || toRow == -1 || toColumn == -1) {
				if(debug==true) {
					System.out.println("Illegal move, try again : Invalid Coordinate");
				}else {
					System.out.println("Illegal move, try again.");
				}
				printStatus = false;
				reverseMove();
				continue;
			}

			// Check if there is a piece at the position "from", or a piece already in
			// position "to"
			if (board[fromRow][fromColumn] == null) {
				// the piece of chess we are trying to move does not exists
				if(debug==true) {
					System.out.println("Illegal move, try again : Trying to move a non-existing piece");
				}else {
					System.out.println("Illegal move, try again.");
				}				printStatus = false;
				reverseMove();
				continue;
			}

			// there is a piece but we are trying to move the opponents piece
			if ((board[fromRow][fromColumn].getColor().equals("white") && isWhiteMove == true)
					|| (board[fromRow][fromColumn].getColor().equals("black") && isWhiteMove == false)) {
				// because isWhiteMove is modified above, we have to do it reversely (true ||
				// false instead of false||true)
				if(debug==true) {
					System.out.println("Illegal move, try again : Cannot move opponent");
				}else {
					System.out.println("Illegal move, try again.");
				}				printStatus = false;
				reverseMove();
				continue;
			}

			// if we are trying to eat our own piece
			if (board[toRow][toColumn] != null
					&& board[fromRow][fromColumn].getColor().equals(board[toRow][toColumn].getColor())) {
				// because isWhiteMove is modified above, we have to do it reversely (true ||
				// false instead of false||true)
				if(debug==true) {
					System.out.println("Illegal move, try again : Cannot eat own type");
				}else {
					System.out.println("Illegal move, try again.");
				}				printStatus = false;
				reverseMove();
				continue;
			}

			// Check if it is a valid move for the piece (using isValidMove)
			boolean valid = board[fromRow][fromColumn].isValidMove(toRow, toColumn);
			
			// check draw: will be in the promotion's spot
			if (valid==true && move.split(" ").length == 3 && promotion.equals("draw?")) {
				System.out.println("draw");
				break;
			}

			// Promotion
			if (promotion != null && board[fromRow][fromColumn].getSymbol().equals("p")) {
				// Check if the piece we are moving is a pawn, if so we should check promotion
				// letter is legal
				// if all checks passed, we promote
				if (promotion.equals("R") || promotion.equals("N") || promotion.equals("B") || promotion.equals("Q")) {
					// trying to promote pawn to a rook/knight/bishop/queen
					// check destination
					if (checkPromote(toRow, board[fromRow][fromColumn].getColor())) {
						promote(fromRow, fromColumn, promotion);
					} else {
						if(debug==true) {
							System.out.println("Illegal promotion, try again. Cannot promote bc didn't reach end of board");
						}else {
							System.out.println("Illegal promotion, try again.");
						}
						printStatus = false;
						reverseMove();
						continue;
					}

				} else {
					if(debug==true) {
						System.out.println("Illegal promotion, try again. Unknown promote final type (must be Q/N/R/B)");
					}else {
						System.out.println("Illegal promotion, try again.");
					}					
					printStatus = false;
					reverseMove();
					continue;
				}
			} else if ((promotion == null || promotion == " " || promotion == "")
					&& board[fromRow][fromColumn].getSymbol().equals("p")) {
				// promotion to queen
				// check destination
				if (checkPromote(toRow, board[fromRow][fromColumn].getColor())) {
					promote(fromRow, fromColumn, "Q");
				}
			}

			// All Checks Successful: Move the piece
			if (valid == true) {
				if (board[fromRow][fromColumn].getSymbol().equals("p")
						&& isDiagonalMove(fromRow, fromColumn, toRow, toColumn) && board[toRow - 1][toColumn] != null
						&& board[toRow - 1][toColumn].getSymbol().equals("p")
						&& board[toRow - 1][toColumn].getColor() != board[fromRow][fromColumn].getColor()
						&& ((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO == true) {
					// Pawn is making a diagonal move, and enemy pawn is right below destination,
					// and our pawn is enpassant ready
					board[toRow - 1][toColumn] = null; // captured the piece using enpassant
					((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO = false; // captured the piece, no more
																					// enpassant
					((Pawn) board[fromRow][fromColumn]).onlyVertical = false;
				} else if (board[fromRow][fromColumn].getSymbol().equals("p")
						&& isDiagonalMove(fromRow, fromColumn, toRow, toColumn) && board[toRow + 1][toColumn] != null
						&& board[toRow + 1][toColumn].getSymbol().equals("p")
						&& board[toRow + 1][toColumn].getColor() != board[fromRow][fromColumn].getColor()
						&& ((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO == true) {
					// Pawn is making a diagonal move, and enemy pawn is right below destination,
					// and our pawn is enpassant ready
					board[toRow + 1][toColumn] = null; // captured the piece using enpassant
					((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO = false; // captured the piece, no more
																					// enpassant
					((Pawn) board[fromRow][fromColumn]).onlyVertical = false;
				}
				move(fromRow, fromColumn, toRow, toColumn);
			} else {
				if(debug==true) {
					System.out.println("Illegal move, try again : Not a valid move for piece (returned by isValidMove)");
				}else {
					System.out.println("Illegal move, try again.");
				}				printStatus = false;
				reverseMove();
				continue;
			}

			// Check status of game (such as if one side has won after the move)
			if(isWhiteMove) {
				// before white moves, we check if white is in checkmate
				if (isCheckmate("white")) {
					System.out.println("Checkmate");
					System.out.println("Black wins");
					gameEnd = true;
					break;
				}else {
					if (isChecked("white")) {
						System.out.println("White is Checked");
					}
				}
			}else {
				if (isCheckmate("black")) {
					System.out.println("Checkmate");
					System.out.println("White wins");
					gameEnd = true;
					break;
				}else {
					if (isChecked("black")) {
						System.out.println("Black is Checked");
					}
				}
			}

			System.out.println();
		}
		scanner.close();
	}

	
	
	/**
	This class provides a chess game runner that reads moves from a file and makes the moves on the chess board.
	It includes a method to start the runner, and methods to check if a move is valid, if a promotion is valid,
	and to promote a pawn.
	The class uses a 2D array to represent the chess board, and has classes to represent each type of chess piece.
	@param path The file path to read moves from.
	@param printEveryRound If true, print the board after every move.
	@param printAtLast If true, print the final board after all moves.
	*/
	public static void runner2(String path,boolean printEveryRound,boolean printAtLast) {
		try {
			File file = new File(path);

			Scanner scanner = new Scanner(file);
			boolean printStatus = true;

			// Runner
			while (true) {
				if (!scanner.hasNext()) {
					break;
				}
				// print the board
				if (printStatus == true && printEveryRound==true) {
					printBoard();
				} else if (printStatus == false) {
					printStatus = true;
				}

				// determine who should move
				printMoveStatement();

				// get the move coordinate
				String move = scanner.nextLine();
				System.out.println(move);

				// Check Resign
				if (isResign(move) == true)
					break;

				// Split the move coordinate into from and to
				if (move.split(" ").length < 2 || move.split(" ").length > 3 || move.split(" ")[0].length() != 2
						|| move.split(" ")[1].length() != 2) {
					if(debug==true) {
						System.out.println("Illegal move, try again : Wrong format");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}
				String from = move.split(" ")[0];
				String to = move.split(" ")[1];
				String promotion = null;
				if (move.split(" ").length == 3) {
					promotion = move.split(" ")[2];
				}

				// check draw: will be in the promotion's spot
				if (move.split(" ").length == 3 && promotion.equals("draw?")) {
					System.out.println("draw");
					break;
				}

				// check if destination and "from" is the same thing
				if (from.equals(to)) {
					if(debug==true) {
						System.out.println("Illegal move, try again : Destination cannot be same as source");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				// get the position in the array
				int fromRow = convertPosition(from)[0];
				int fromColumn = convertPosition(from)[1];
				int toRow = convertPosition(to)[0];
				int toColumn = convertPosition(to)[1];

//	 	       System.out.println(board[fromRow][fromColumn]!=null?board[fromRow][fromColumn].getColor()+board[fromRow][fromColumn].getSymbol():"");

				// Check if the from and to coordinates are -1 (meaning the coordinate is
				// invalid)
				if (fromRow == -1 || fromColumn == -1 || toRow == -1 || toColumn == -1) {
					if(debug==true) {
						System.out.println("Illegal move, try again : Invalid coordinate");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				// Check if there is a piece at the position "from", or a piece already in
				// position "to"
				if (board[fromRow][fromColumn] == null) {
					// the piece of chess we are trying to move does not exists
					if(debug==true) {
						System.out.println("Illegal move, try again : Trying to move a non-exsisting piece");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				// there is a piece but we are trying to move the opponents piece
				if ((board[fromRow][fromColumn].getColor().equals("white") && isWhiteMove == true)
						|| (board[fromRow][fromColumn].getColor().equals("black") && isWhiteMove == false)) {
					// because isWhiteMove is modified above, we have to do it reversely (true ||
					// false instead of false||true)
					if(debug==true) {
						System.out.println("Illegal move, try again : Cannot move opponent");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				// if we are trying to eat our own piece
				if (board[toRow][toColumn] != null
						&& board[fromRow][fromColumn].getColor().equals(board[toRow][toColumn].getColor())) {
					// because isWhiteMove is modified above, we have to do it reversely (true ||
					// false instead of false||true)
					if(debug==true) {
						System.out.println("Illegal move, try again : Cannot eat own type");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				// Check if it is a valid move for the piece (using isValidMove)
				board[fromRow][fromColumn].setPosition(fromRow + 1, fromColumn + 1);
				boolean valid = board[fromRow][fromColumn].isValidMove(toRow, toColumn);

				// Promotion
				if (promotion != null && board[fromRow][fromColumn].getSymbol().equals("p")) {
					// Check if the piece we are moving is a pawn, if so we should check promotion
					// letter is legal
					// if all checks passed, we promote
					if (promotion.equals("R") || promotion.equals("N") || promotion.equals("B")
							|| promotion.equals("Q")) {
						// trying to promote pawn to a rook/knight/bishop/queen
						// check destination
						if (checkPromote(toRow, board[fromRow][fromColumn].getColor())) {
							promote(fromRow, fromColumn, promotion);
						} else {
							if(debug==true) {
								System.out.println("Illegal promotion, try again. Cannot promote bc didn't reach end of board");
							}else {
								System.out.println("Illegal promotion, try again.");
							}
							printStatus = false;
							reverseMove();
							continue;
						}

					} else {
						if(debug==true) {
							System.out.println("Illegal promotion, try again. Unknown promote final type (must be Q/N/R/B)");
						}else {
							System.out.println("Illegal promotion, try again.");
						}							
						printStatus = false;
						reverseMove();
						continue;
					}
				} else if ((promotion == null || promotion == " " || promotion == "")
						&& board[fromRow][fromColumn].getSymbol().equals("p")) {
					// promotion to queen
					// check destination
					if (checkPromote(toRow, board[fromRow][fromColumn].getColor())) {
						promote(fromRow, fromColumn, "Q");
					}
				}

				// All Checks Successful: Move the piece
				if (valid == true) {
					if (board[fromRow][fromColumn].getSymbol().equals("p")
							&& isDiagonalMove(fromRow, fromColumn, toRow, toColumn)
							&& board[toRow - 1][toColumn] != null && board[toRow - 1][toColumn].getSymbol().equals("p")
							&& board[toRow - 1][toColumn].getColor() != board[fromRow][fromColumn].getColor()
							&& ((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO == true) {
						// Pawn is making a diagonal move, and enemy pawn is right below destination,
						// and our pawn is enpassant ready
						board[toRow - 1][toColumn] = null; // captured the piece using enpassant
						((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO = false; // captured the piece, no more
																						// enpassant
						((Pawn) board[fromRow][fromColumn]).onlyVertical = false;
					} else if (board[fromRow][fromColumn].getSymbol().equals("p")
							&& isDiagonalMove(fromRow, fromColumn, toRow, toColumn)
							&& board[toRow + 1][toColumn] != null && board[toRow + 1][toColumn].getSymbol().equals("p")
							&& board[toRow + 1][toColumn].getColor() != board[fromRow][fromColumn].getColor()
							&& ((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO == true) {
						// Pawn is making a diagonal move, and enemy pawn is right below destination,
						// and our pawn is enpassant ready
						board[toRow + 1][toColumn] = null; // captured the piece using enpassant
						((Pawn) board[fromRow][fromColumn]).en_passant_GOGOGO = false; // captured the piece, no more
																						// enpassant
						((Pawn) board[fromRow][fromColumn]).onlyVertical = false;
					}
					move(fromRow, fromColumn, toRow, toColumn);
				} else {
					if(debug==true) {
						System.out.println("Illegal move, try again : Invalid Move (by isValidMove)");
					}else {
						System.out.println("Illegal move, try again.");
					}					printStatus = false;
					reverseMove();
					continue;
				}

				 
				
				// Check status of game (such as if one side has won after the move)
				if(isWhiteMove) {
					// before white moves, we check if white is in checkmate
					if (isCheckmate("white")) {
						System.out.println("Checkmate");
						System.out.println("Black wins");
						gameEnd = true;
						break;
					}else {
						if (isChecked("white")) {
							System.out.println("White is Checked");
						}
					}
				}else {
					if (isCheckmate("black")) {
						System.out.println("Checkmate");
						System.out.println("White wins");
						gameEnd = true;
						break;
					}else {
						if (isChecked("black")) {
							System.out.println("Black is Checked");
						}
					}
				}
				  

				System.out.println();
			}
			scanner.close();
			if (!gameEnd) {
				runner1();
			}else {
				if(printAtLast) {
					printBoard();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * This is the main method that starts the chess game.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		if (mode.equals("terminal")) {
			// run by taking input from terminal
			initChess();
			runner1();
		} else if (mode.equals("file")) {
			// run by taking input from a file (FILE FORMAT: 2 coordinates, then skip a line, and so on)
			initChess();
			runner2("/Users/zhengzihao/eclipse-workspace/Chess/src/Control/ex1.txt",false,false);// do not: print every round, print at last
		}

	}
}
