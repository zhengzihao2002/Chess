package Model;
/**
 * A abstract class / base for the chess pieces
 *
 * <p>
 * This class stores information such as the piece color, row, column, and a static reference to the board.
 * </p>
 *
 * <p>
 * This class is abstract and should be extended by the specific chess piece classes (e.g. Pawn, Knight, etc.).
 * </p>
 *
 * @author Yiming Huang
 * @author Zihao Zheng
 */
public abstract class ChessPiece {
	/**
	 * Color of the piece
	 * */
    private String color;
    
    /**
     *row of the piece on the board
     * */
    private int row;
    
    /**
     * columns of the piece on the board
     * */
    private int column;
    
    /**
     * static reference to the field
     * */
    public static ChessPiece[][] board;

    /**
     * The constructor of chess piece
     * @param color the color of the chess piece
     * @param row the row of the chess piece
     * @param column the column of the chess piece
     */
    public ChessPiece(String color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    /**
     * Method for getting the color of the piece
     * @return the color of the piece
     * */
    public String getColor() {
        return color;
    }

    /**
     * Method for getting the row of the piece
     * @return the row of the piece
     * */
    public int getRow() {
        return row;
    }

    /**
     * Method for getting the column of the piece
     * @return the columnn of the piece
     * */
    public int getColumn() {
        return column;
    }
    
    /**
     * Method for getting the symbol of the piece
     * @return the symbol of the piece
     * */
    public abstract String getSymbol();
    
    /**
     * Method for setting the position of the piece
     * @return the position of the piece
     * */
    public void setPosition(int row, int column) {
    	this.row = row;
        this.column = column;
    }
    
    /**
     * Checks if the given move is valid
     * @param rowthe destination row
     * @param column the destination column
     * @return true or false
     * */
    public abstract boolean isValidMove(int row, int column);
    
    /**
     * Checks if the given move is valid
     * @param fromRow the source row
     * @param fromCol the source column
     * @param toRow the destination row
     * @param toColumn the destination column
     * @return true or false
     * */
    public abstract boolean isValidMove(int fromRow, int fromCol,int toRow, int toColumn);
    
    /**
     * determine if the piece has moved
     * */
    private boolean hasMoved;
    
    /**
     * Checks if the given move is valid
     * @return true or false
     * */
    public boolean hasMoved() {
        return hasMoved;
    }
    
    /**
     * method for setting if a piece has moved
     * */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}



