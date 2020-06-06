package battleship;

/**
 * This class is for handling the exception when the ships overlap in the configuration.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */
public class OverlapException extends BattleshipException {
    /**
     * The constructor handles the exception which occurs when there is an overlap between ships.
     * @param row It has the row value of the board
     * @param column It has the column value of the board
     * @param message It gives the reason of the exception.
     */
    public OverlapException(int row,int column, String message)
    {
        super(row,column,message);
    }
}
