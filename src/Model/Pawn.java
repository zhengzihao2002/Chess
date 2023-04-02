package Model;
//Created by Zihao Zheng on 3/9/2023.

/**
* The Pawn class represents a chess piece of the pawn type. This class extends the ChessPiece class and implements
* methods for validating moves of a pawn.
* @author Yiming Huang
* @author Zihao Zheng
*/
public class Pawn extends ChessPiece {
	/**move 2 forward required variables*/
	public boolean firstMove = true;
	
	// enpassant required variables
	
	/**indicates if this pawn has only moved in a vertical line*/
	public boolean onlyVertical = true; 
	
	/**indicates if en_passant is ready*/
	public boolean en_passant_GOGOGO = false;
	
	/**indicates if it moved two grids*/
	public boolean moveTwoAlready = false;
	
	
	/**
	Constructs a Pawn object with the specified color, row, and column.
	@param color the color of the pawn, either "black" or "white"
	@param row the initial row of the pawn on the board
	@param column the initial column of the pawn on the board
	*/
    public Pawn(String color, int row, int column) {
        super(color, row, column);
    }

    
    /**
    Determines whether the move from the current location to the specified row and column is a valid move for the pawn.
    @param newRow the row to move the pawn to
    @param newColumn the column to move the pawn to
    @return true if the move is valid, false otherwise
    */
    @Override
    public boolean isValidMove(int newRow, int newColumn) {
    	if(newRow == (getRow()-1) && newColumn == (getColumn()-1)) {
        	// cannot eat itself
    		return false;
    	}
        int rowDiff = newRow - (getRow()-1);
        int colDiff = newColumn - (getColumn()-1);
        int forwardDir = getColor().equals("white") ? -1 : 1;
        if (colDiff == 0) { // moving straight
            if (rowDiff == forwardDir) { // moving one step forward
                return board[newRow][newColumn] == null;
            } else if (firstMove==true && rowDiff == 2 * forwardDir) { // moving two steps forward from starting position
                if(board[newRow][newColumn] == null) {
                	moveTwoAlready = true; // i can assume that it is definetly going to move, since the only check is promotion after isValidMove, and by the time promotion stops us, we are already non-first move. it is safe to assume we are definetly going to move
                	return true;
                }
            	return false;
            }
        } else if (Math.abs(colDiff) == 1 && rowDiff == forwardDir) { // capturing diagonally
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow-1>=0 &&board[newRow-1][newColumn]!=null && getColor().equals("black")) {
        		// enpassant can move diagonal, even if destination is empty
        		return true;
        	}
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow+1>=0 &&board[newRow+1][newColumn]!=null && getColor().equals("white")) {
        		return true;
        	}
        	
            return board[newRow][newColumn] != null && board[newRow][newColumn].getColor().equals(getColor().equals("white")?"black":"white");
        }
        return false;
    }

    
    
    /**
    Determines if a move from the current location to a new location is a valid move for the pawn piece.
    @param fromRow the source row
    @param fromColumn the source column
    @param newRow The row that the pawn piece is attempting to move to.
    @param newColumn The column that the pawn piece is attempting to move to.
    @return true if the move is valid, false otherwise.
    */
    public boolean isValidMove(int fromRow, int fromColumn, int newRow, int newColumn) {
    	if(newRow == fromRow && newColumn == fromColumn) {
        	// cannot eat itself
    		return false;
    	}
        int rowDiff = newRow - (fromRow);
        int colDiff = newColumn - (fromColumn);
        int forwardDir = getColor().equals("white") ? -1 : 1;
        if (colDiff == 0) { // moving straight
            if (rowDiff == forwardDir) { // moving one step forward
                return board[newRow][newColumn] == null;
            } else if (firstMove==true && rowDiff == 2 * forwardDir) { // moving two steps forward from starting position
                if(board[newRow][newColumn] == null) {
                	moveTwoAlready = true; // i can assume that it is definetly going to move, since the only check is promotion after isValidMove, and by the time promotion stops us, we are already non-first move. it is safe to assume we are definetly going to move
                	return true;
                }
            	return false;
            }
        } else if (Math.abs(colDiff) == 1 && rowDiff == forwardDir) { // capturing diagonally
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow-1>=0 &&board[newRow-1][newColumn]!=null && getColor().equals("black")) {
        		// enpassant can move diagonal, even if destination is empty
        		return true;
        	}
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow+1>=0 &&board[newRow+1][newColumn]!=null && getColor().equals("white")) {
        		return true;
        	}
        	
            return board[newRow][newColumn] != null && board[newRow][newColumn].getColor().equals(getColor().equals("white")?"black":"white");
        }
        return false;
    }
    
    /**
    This method checks if the given move is valid for the pawn.
    @param newRow the row to move to
    @param newColumn the column to move to
    @return true if the move is valid, false otherwise
    */
    public boolean isValidTest(int newRow, int newColumn) {
        int rowDiff = newRow - (getRow()-1);
        int colDiff = newColumn - (getColumn()-1);
        int forwardDir = getColor().equals("white") ? -1 : 1;
        if (colDiff == 0) { // moving straight
            if (rowDiff == forwardDir) { // moving one step forward
                return board[newRow][newColumn] == null;
            } else if (firstMove==true && rowDiff == 2 * forwardDir) { // moving two steps forward from starting position
                if(board[newRow][newColumn] == null) {
                	return true;
                }
            	return false;
            }
        } else if (Math.abs(colDiff) == 1 && rowDiff == forwardDir) { // capturing diagonally
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow-1>=0 &&board[newRow-1][newColumn]!=null && getColor().equals("black")) {
        		// enpassant can move diagonal, even if destination is empty
        		return true;
        	}
        	if(board[newRow][newColumn] == null && en_passant_GOGOGO && newRow+1>=0 &&board[newRow+1][newColumn]!=null && getColor().equals("white")) {
        		return true;
        	}
        	
            return board[newRow][newColumn] != null && board[newRow][newColumn].getColor().equals(getColor().equals("white")?"black":"white");
        }
        return false;
    }


    
    /**
     * Returns the symbol of the pawn, which is "P".
     * 
     * @return the symbol of the pawn
     */
	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "p";
	}
}