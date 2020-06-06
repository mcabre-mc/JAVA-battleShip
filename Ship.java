package battleship;
import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {

    public static final String SUNK_MESSAGE = "A battleship has been sunk!";

    /**
     * Orientation is a property of a ship. The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration files.
     * @see Orientation#valueOf(String)
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**Use it to loop through all of the cell locations a ship is on,
         * based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number change in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }
    private int length;
    private int hitCounter;

    public int getHitCounter() {
        return hitCounter;
    }

    public int getLength() {
        return length;
    }

    /**
     * Initialize this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     * @param board holds a collection of ships
     * @param uRow the uppermost row that the ship is on
     * @param lCol the leftmost column that the ship is on
     * @param ort the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException if this ship would overlap another one that already exists
     * @throws OutOfBoundsException if this ship would extend beyond the board
     */
    public Ship(Board board,int uRow,int lCol,Orientation ort,int length) throws OverlapException,OutOfBoundsException
    {
        //calculate the cells of the board where the ship should be. Calculate remaining cells based on orientation
        int col = 0,ro = 0;
        if (ort == Orientation.HORIZONTAL) {
            col = lCol;
            for (int i = 0; i < length; i++,col++) {

                Cell thatCell = board.getCell(uRow, col);
                //Throw overlapexception if there is already a ship here

                thatCell.putShip(this);


            }
        }
        else if (ort == Orientation.VERTICAL)
        {
            ro = uRow;
            for(int i = 0 ; i < length ; i++,ro++)
            {

                Cell thatCell = board.getCell(ro,lCol);
                //Throw overlapexception if there is already a ship here
                thatCell.putShip(this);

            }
        }
        this.length = length;
        this.hitCounter = 0;

        }

    /**
     * The hit method is used to increment the hitcounter for a ship.
     */
    public void hit() {
        hitCounter++;

    }

    /**
     * The function is used to check if a ship is sunk or not
     * @return true is the ship is sunk else false.
     */
    public boolean isSunk() {
        if (hitCounter >= length) {
            return true;
        }
        return false;
    }

    }


