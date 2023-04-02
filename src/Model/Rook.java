package Model;
//Created by Zihao Zheng on 3/9/2023.

/**
* The Rook class represents a chess piece of the rook type, which can move horizontally or vertically any number of squares.
* It extends the abstract ChessPiece class and overrides its isValidMove and getSymbol methods.
* @author Yiming Huang
* @author Zihao Zheng
*/
public class Rook extends ChessPiece {
	
	/**
	Constructs a new Rook object with the specified color, row and column.
	@param color the color of the rook, either "white" or "black".
	@param row the initial row of the rook.
	@param column the initial column of the rook.
	*/
    public Rook(String color, int row,int column) {
        super(color, row, column);
    }

    @Override
    /**
    Determines whether the rook can move from its current position to a specified position on the board.
    The rook can move horizontally or vertically any number of squares as long as there are no pieces in its way.
    @param newRow the row of the position the rook wants to move to.
    @param newColumn the column of the position the rook wants to move to.
    @return true if the move is valid, false otherwise.
    */
    public boolean isValidMove(int newRow, int newColumn) {
    	
    	if(newRow==-1||newColumn==-1) {
    		return false;
    	}
        int currRow = getRow()-1;
        int currColumn = getColumn()-1;
        
        if(newRow == currRow && newColumn == currColumn) {
        	// cannot eat itself
    		return false;
    	}
        if(board[newRow][newColumn]!=null && board[currRow][currColumn].getColor().equals(board[newRow][newColumn].getColor())) {
        	return false;
        }
//        System.out.println(currRow+","+currColumn+"     "+newRow+","+newColumn);
        if (newRow == currRow && newColumn == currColumn) {
            // the new position is the same as the current position
            return false;
        }
        if (newRow != currRow && newColumn != currColumn) {
            // the rook is not moving in a straight line
            return false;
        }
        if (newRow == currRow) {
            // the rook is moving horizontally
            int minColumn = Math.min(currColumn, newColumn);
            int maxColumn = Math.max(currColumn, newColumn);
            for (int col = minColumn + 1; col < maxColumn; col++) {
                if (board[currRow][col] != null) {
                    // there is another piece in the way
                    return false;
                }
            }
        } else {
            // the rook is moving vertically
            int minRow = Math.min(currRow, newRow);
            int maxRow = Math.max(currRow, newRow);
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[row][currColumn] != null) {
                    // there is another piece in the way
                    return false;
                }
            }
        }
        
        return true;
    }

    
    @Override
    /**
    Checks if a move is a valid move for a rook piece
    @param fromRow the starting row of the piece
    @param fromCol the starting column of the piece
    @param newRow the row the piece is moving to
    @param newColumn the column the piece is moving to
    @return true if the move is valid, false otherwise
    */
    public boolean isValidMove(int fromRow, int fromCol, int newRow, int newColumn) {
    	if(fromRow==-1 || fromCol==-1 || newRow==-1||newColumn==-1) {
    		return false;
    	}
        int currRow = fromRow;
        int currColumn = fromCol;
        
        if(newRow == currRow && newColumn == currColumn) {
        	// cannot eat itself
    		return false;
    	}
        if(board[newRow][newColumn]!=null && board[currRow][currColumn].getColor().equals(board[newRow][newColumn].getColor())) {
        	return false;
        }
//        System.out.println(currRow+","+currColumn+"     "+newRow+","+newColumn);
        if (newRow == currRow && newColumn == currColumn) {
            // the new position is the same as the current position
            return false;
        }
        if (newRow != currRow && newColumn != currColumn) {
            // the rook is not moving in a straight line
            return false;
        }
        if (newRow == currRow) {
            // the rook is moving horizontally
            int minColumn = Math.min(currColumn, newColumn);
            int maxColumn = Math.max(currColumn, newColumn);
            for (int col = minColumn + 1; col < maxColumn; col++) {
                if (board[currRow][col] != null) {
                    // there is another piece in the way
                    return false;
                }
            }
        } else {
            // the rook is moving vertically
            int minRow = Math.min(currRow, newRow);
            int maxRow = Math.max(currRow, newRow);
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[row][currColumn] != null) {
                    // there is another piece in the way
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
    @return the symbol of the piece as a String "R"
    */
	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "R";
	}
}