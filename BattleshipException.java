package battleship;

/**
 * This class is for handling the exception while playing Battleship.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */
public class BattleshipException extends Exception {

    public static final int UNSET = -1;

    // Create public integer fields row and column.
    // Make them so they cannot be changed, and non-static.
    // Complete this constructor so that the row and column
    // are stored in the exception instance.

    /**
     * The constructor handles all possible exception which can occur while gameplay.
     * @param row It has the row value of the board
     * @param column It has the column value of the board
     * @param message It gives the reason of the exception.
     */
    public BattleshipException( int row, int column, String message ) {
        super( message + ", row=" + row + ", column=" + column );
    }

    // Add a second (overloading) constructor that only takes a
    // message string. It should pass the string up to its superclass
    // and set row and column to UNSET.

    /**
      * The method handels the exception which only has the message with it.
      * @param message It gives the reason for the exception to occur.
     */
    public BattleshipException(String message)
    {
        super(message + ", row=" + UNSET + ", column=" + UNSET);

    }
}
