package battleship;

/**
 * This class is for handling the exception while a played cell has been called again.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */
public class CellPlayedException extends BattleshipException {

    /**
     * The constructor handles which can occur while calling the played cell.
     * @param row It has the row value of the board
     * @param column It has the column value of the board
     * @param message It gives the reason of the exception.
     */
    public CellPlayedException(int row,int column, String message) {
        super(row,column,message);
    }

}
