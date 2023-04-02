package Model;


/**
 * The Bishop class represents a bishop chess piece on a chess board.
* @author Yiming Huang
* @author Zihao Zheng
* @since Zihao Zheng on 3/9/2023.
*/
public class Bishop extends ChessPiece {
	
	/**
     * Constructs a new Bishop object with the specified color, row, and column.
     * 
     * @param color the color of the bishop ("white" or "black")
     * @param row the initial row of the bishop (between 1 and 8)
     * @param column the initial column of the bishop (between 1 and 8)
     */
    public Bishop(String color, int row, int column) {
        super(color, row, column);
    }
    
    
    /**
     * Checks if the bishop can move to the specified position on the chess board.
     * @param newRow The row of the destination position.
     * @param newCol The column of the destination position.
     * @return true if the bishop can move to the specified position, false otherwise.
     */
    @Override
    public boolean isValidMove(int newRow, int newCol) {
        int curRow = getRow()-1;
        int curCol = getColumn()-1;
        
        if(newRow == curRow && newCol == curCol) {
        	// cannot eat itself
    		return false;
    	}

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
            if (i>=0&&i<=7&&j>=0&&j<=7&&board[i][j] != null) {
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

    
    /**
    Determines if a move is valid for a bishop piece.
    @param fromRow the current row position of the bishop
    @param fromCol the current column position of the bishop
    @param newRow the row position the bishop is attempting to move to
    @param newCol the column position the bishop is attempting to move to
    @return true if the move is valid, false otherwise
    */
    public boolean isValidMove(int fromRow, int fromCol, int newRow, int newCol) {
        int curRow = fromRow;
        int curCol = fromCol;
        if(newRow == curRow && newCol == curCol) {
        	// cannot eat itself
    		return false;
    	}
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
    
    /**
     * Returns the symbol of the bishop, which is "B".
     * 
     * @return the symbol of the bishop
     */
	@Override
	public String getSymbol() {
		return "B";
	}

}