package battleship;

/**
 * This class is for handling the exception while user gives range outside the board dimensions.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */
public class OutOfBoundsException extends BattleshipException {

    /**
     * The constructor handles exception which occurs while the user give value of row and column out of the board size.
     * @param row It has the row value of the board
     * @param column It has the column value of the board
     * @param message It gives the reason of the exception.
     */
    public OutOfBoundsException(int row,int column, String message)
    {
        super(row,column,message);
    }
}
