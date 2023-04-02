package Model;
//Created by Zihao Zheng on 3/9/2023.

/**
* The Queen class represents a chess piece called queen.
* It extends the ChessPiece class and overrides the isValidMove method to check if a move for a queen is valid.
* It also provides a constructor and a getSymbol method.
* @author Yiming Huang
* @author Zihao Zheng
*/
public class Queen extends ChessPiece {
	/**
    Constructs a queen with a specified color, row, and column on the chess board.
    @param color the color of the queen, either "white" or "black"
    @param row the row where the queen is located
    @param column the column where the queen is located
    */
    public Queen(String color, int row, int column) {
        super(color, row, column);
    }

  
    /**

    Checks if a move for the queen from its current position to a specified position is valid.
    A move is valid if it can be performed according to the rules of chess for the queen piece.
    The method first checks if the new position is the same as the current position or if there is a piece of the same color in the new position.
    Then it determines if the move is rook-like or bishop-like, and calls the corresponding helper method to check if the move is valid.
    @param newRow the row of the new position
    @param newColumn the column of the new position
    @return true if the move is valid, false otherwise
    */
    @Override
    public boolean isValidMove(int newRow, int newColumn) {
        int currRow = getRow()-1;
        int currCol = getColumn()-1;
        if(newRow == currRow && newColumn == currCol) {
        	// cannot eat itself
    		return false;
    	}
        
        // Queen moves are a combination of bishop and rook moves,
        // so we can reuse the existing methods for those pieces
        if (newRow == currRow || newColumn == currCol) {
            // This is a rook-like move
            return rookMove(currRow,currCol,newRow, newColumn);
        } else {
            // This is a bishop-like move
            return bishopMove(currRow, currCol, newRow, newColumn);
        }
    }
    
    
    /**
    Checks if a move for the queen from a specified position to a specified position is valid.
    A move is valid if it can be performed according to the rules of chess for the queen piece.
    The method first checks if the new position is the same as the current position or if there is a piece of the same color in the new position.
    Then it determines if the move is rook-like or bishop-like, and calls the corresponding helper method to check if the move is valid.
    @param fromRow the row of the current position
    @param fromCol the column of the current position
    @param newRow the row of the new position
    @param newColumn the column of the new position
    @return true if the move is valid, false otherwise
    */
    public boolean isValidMove(int fromRow,int fromCol,int newRow, int newColumn) {
        int currRow = fromRow;
        int currCol = fromCol;
        if(newRow == currRow && newColumn == currCol) {
        	// cannot eat itself
    		return false;
    	}
        // Queen moves are a combination of bishop and rook moves,
        // so we can reuse the existing methods for those pieces
        if (newRow == currRow || newColumn == currCol) {
            // This is a rook-like move
            return rookMove(currRow,currCol,newRow, newColumn);
        } else {
            // This is a bishop-like move
            return bishopMove(currRow, currCol, newRow, newColumn);
        }
    }
    
    /**
    Checks if a move for is valid for a rook
    @param fromRow the row of the current position
    @param fromCol the column of the current position
    @param newRow the row of the new position
    @param newColumn the column of the new position
    @return true if the move is valid, false otherwise
    */
    private boolean rookMove(int currRow, int currColumn, int newRow,int newColumn) {
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
    Checks if a move for is valid for a bishop
    @param fromRow the row of the current position
    @param fromCol the column of the current position
    @param newRow the row of the new position
    @param newColumn the column of the new position
    @return true if the move is valid, false otherwise
    */
    private boolean bishopMove(int curRow, int curCol, int newRow,int newCol) {

        // Check if bishop is moving diagonally
        if (Math.abs(newRow - curRow) != Math.abs(newCol - curCol)) {
//        	System.out.println("false1");
            return false;
        }

        // Check if there are any pieces in the way
        int rowDir = newRow > curRow ? 1 : -1;
        int colDir = newCol > curCol ? 1 : -1;
        int i = curRow + rowDir;
        int j = curCol + colDir;
        while (i != newRow && j != newCol) {
            if (board[i][j] != null) {
//            	System.out.println("false2");
                return false;
            }
            i += rowDir;
            j += colDir;
        }

        // Check if destination is occupied by an enemy piece
        if (board[newRow][newCol] != null && !board[newRow][newCol].getColor().equals(getColor())) {
            return true;
        }

        // If there's no piece in the destination or if it's occupied by a piece of the same color, return true
        if(board[newRow][newCol] !=null) {
//        	System.out.println("false3");
        }
        return board[newRow][newCol] == null;
    }

	@Override
	/**
     * Returns the symbol of the queen, which is "Q".
     * 
     * @return the symbol of the queen
     */
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "Q";
	}
}