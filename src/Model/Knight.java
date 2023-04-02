package Model;
/**
* Created by Zihao Zheng on 3/9/2023.
* The knight class represents a chess piece of the knight type. This class extends the ChessPiece class and implements
* methods for validating moves of a knight.
* @author Yiming Huang
* @author Zihao Zheng
*/
public class Knight extends ChessPiece {
	/**
     * Creates a new Knight with the given color, row, and column.
     * 
     * @param color the color of the knight
     * @param row the starting row of the knight
     * @param column the starting column of the knight
     */
    public Knight(String color, int row,int column) {
        super(color, row, column);
    }

    /**
     * Checks if the given position is a valid move for the knight from its current position.
     * 
     * @param newRow the row of the position to move to
     * @param newColumn the column of the position to move to
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean isValidMove(int newRow, int newColumn) {
        int currentRow = getRow()-1;
        int currentColumn = getColumn()-1;
        if(newRow == currentRow && newColumn == currentColumn) {
        	// cannot eat itself
    		return false;
    	}
        
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newColumn - currentColumn);
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            ChessPiece newPiece = board[newRow][newColumn];
            if (newPiece == null || newPiece.getColor() != getColor()) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Checks if the given move is a valid move for the knight from the given starting position.
     * 
     * @param fromRow the starting row of the knight
     * @param fromCol the starting column of the knight
     * @param newRow the row of the position to move to
     * @param newColumn the column of the position to move to
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int fromRow,int fromCol,int newRow, int newColumn) {
        int currentRow = fromRow;
        int currentColumn = fromCol;
        if(newRow == currentRow && newColumn == currentColumn) {
        	// cannot eat itself
    		return false;
    	}
        
        
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newColumn - currentColumn);
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            ChessPiece newPiece = board[newRow][newColumn];
            if (newPiece == null || newPiece.getColor() != getColor()) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * Returns the symbol of the knight, which is "N".
     * 
     * @return the symbol of the knight
     */
	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return "N";
	}
}
