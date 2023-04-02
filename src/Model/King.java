package Model;
//Created by Zihao Zheng on 3/9/2023.

/**
* @author Yiming Huang
* @author Zihao Zheng
*/
public class King extends ChessPiece {
	/**
	 * Initializes a king with the given color, row, and column.
	 * 
	 * @param color the color of the king ("white" or "black")
	 * @param row the starting row of the king
	 * @param column the starting column of the king
	 */
    public King(String color, int row, int column) {
        super(color, row, column);
    }

    /**
    Checks if a move is valid for a chess piece.
    @param newRow the row index of the piece's intended new position
    @param newColumn the column index of the piece's intended new position
    @return true if the move is valid, false otherwise
    */
    @Override
    public boolean isValidMove(int newRow, int newColumn) {
    	if(newRow==-1||newColumn==-1) {
    		return false;
    	}
    	if(newRow == (getRow()-1) && newColumn == (getColumn()-1)) {
        	// cannot eat itself
    		return false;
    	}

        // check if new position is out of board bounds
        if (newRow < 0 || newRow > 7 || newColumn < 0 || newColumn > 7) {
            return false;
        }

        // check if the new position is not occupied by a friendly piece
        if (board[newRow][newColumn] != null && board[newRow][newColumn].getColor().equals(getColor())) {
            return false;
        }

        // calculate the absolute distance between the current and new positions
        int rowDiff = Math.abs(newRow - (getRow()-1));
        int colDiff = Math.abs(newColumn - (getColumn()-1));

        // check if the new position is only one square away in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            return true;
        }
        
        
        // castling
        if (rowDiff == 0 && colDiff == 2) {
            // check if the king has already moved
            if (hasMoved()) {
                return false;
            }

            // check if there is a rook in the correct position
            if (newColumn == 2 && board[newRow][0] instanceof Rook && !board[newRow][0].hasMoved()) {
                // check if the squares between the king and the rook are empty
                if (board[newRow][1] == null && board[newRow][2] == null && board[newRow][3] == null) {
                    // move the rook to the left of the king
                    board[newRow][3] = board[newRow][0];
                    board[newRow][0] = null;
                    board[newRow][3].setHasMoved(true);
                    return true;
                }
            } else if (newColumn == 6 && board[newRow][7] instanceof Rook && !board[newRow][7].hasMoved()) {
                // check if the squares between the king and the rook are empty
                if (board[newRow][5] == null && board[newRow][6] == null) {
                    // move the rook to the right of the king
                    board[newRow][5] = board[newRow][7];
                    board[newRow][7] = null;
                    board[newRow][5].setHasMoved(true);
                    return true;
                }
            }
        }

        return false;
    }
    
    
    /**
    Checks if a move is valid for a chess piece.
    @param fromRow the row index of the piece's current position
    @param fromCol the column index of the piece's current position
    @param newRow the row index of the piece's intended new position
    @param newColumn the column index of the piece's intended new position
    @return true if the move is valid, false otherwise
    */
    public boolean isValidMove(int fromRow,int fromCol,int newRow, int newColumn) {
    	if(newRow == fromRow && newColumn == fromCol) {
        	// cannot eat itself
    		return false;
    	}
    	if(fromRow==-1 || fromCol==-1 || newRow==-1||newColumn==-1) {
    		return false;
    	}
        // check if new position is out of board bounds
        if (newRow < 0 || newRow > 7 || newColumn < 0 || newColumn > 7) {
            return false;
        }

        // check if the new position is not occupied by a friendly piece
        if (board[newRow][newColumn] != null && board[newRow][newColumn].getColor().equals(getColor())) {
            return false;
        }

        // calculate the absolute distance between the current and new positions
        int rowDiff = Math.abs(newRow - fromRow);
        int colDiff = Math.abs(newColumn -fromCol);

        // check if the new position is only one square away in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            return true;
        }
        
        // check if the new position is 2 squares away in left or right (castling)
        if (rowDiff == 0 && colDiff == 2) {
            // check if the king has already moved
            if (hasMoved()) {
                return false;
            }

            // check if there is a rook in the correct position
            if (newColumn == 2 && board[newRow][0] instanceof Rook && !board[newRow][0].hasMoved()) {
                // check if the squares between the king and the rook are empty
                if (board[newRow][1] == null && board[newRow][2] == null && board[newRow][3] == null) {
                    // move the rook to the left of the king
                    board[newRow][3] = board[newRow][0];
                    board[newRow][0] = null;
                    board[newRow][3].setHasMoved(true);
                    return true;
                }
            } else if (newColumn == 6 && board[newRow][7] instanceof Rook && !board[newRow][7].hasMoved()) {
                // check if the squares between the king and the rook are empty
                if (board[newRow][5] == null && board[newRow][6] == null) {
                    // move the rook to the right of the king
                    board[newRow][5] = board[newRow][7];
                    board[newRow][7] = null;
                    board[newRow][5].setHasMoved(true);
                    return true;
                }
            }
        }

        return false;
    }
	
    
    /**
     * Returns the symbol of the King, which is "K".
     * 
     * @return the symbol of the King
     */
    @Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "K";
	}
}