package battleship;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class to represent the grid of cells (squares).A collection of ships is also kept so the
 * Board can be asked if the game is over. The class is Serializable so that its instance can
 * be saved to a file in binary form using an  * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}. Because the object holds references to all other
 * objects in the system, no other objects need to be separately saved.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */
public class Board implements Serializable {
    private Cell[][] cells;
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    public Board(int height,int width)
    {
        cells = new Cell[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j = 0; j < width; j++)
            {
                Cell boardCell = new Cell(i,j);
                cells[i][j] = boardCell;
            }
        }
        this.height = height;
        this.width = width;
    }

    /**
     * Fetch the Cell object at the given location.
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row,int column) throws OutOfBoundsException {
        //TODO exception handling
        if (row >= height || column >= width)
            throw new OutOfBoundsException(row,column,"Coordinates are past board edge");
        return cells[row][column];
    }
    int height;
    int width;
    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     * @see Cell#putShip(Ship)
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     *    that are involved.
     */
    public void addShip(Ship ship)
    {
        ships.add(ship);
    }

    /**
     * The function check if all ships in the board is sunk.
     * @return true is returned if all ships are sunk else false is returned.
     */
    public boolean allSunk(){
        for(Ship s: ships)
        {
            if (!s.isSunk())
                return false;
        }
        return true;
    }

    /**
     * Display the board in character form to the user.
     * @param po the output stream to which the display should be sent
     */
    public void display(PrintStream po)
    {
        po.print("\t");
        for(int r = 0;r < cells[0].length; r++){
            po.print(r+"\t");
        }
        po.print("\n");
        for(int i=0;i< cells.length ;i++)
        {
            po.print(i+"\t");
            for(int j = 0; j < cells[0].length; j++)
            {
                po.print(cells[i][j].displayHitStatus() + "\t");
            }
            po.print("\n");
        }

    }

    /**
     * Display the board with the ships positions marked as S in character form to the user.
     * @param po the output stream to which the display should be sent
     */
    public void fullDisplay(PrintStream po)
    {
        po.print("\t");
        for(int r = 0;r < cells[0].length; r++){
            po.print(r+"\t");
        }
        po.print("\n");
        for(int i=0;i< cells.length ;i++)
        {
            po.print(i+"\t");
            for(int j = 0; j < cells[0].length; j++)
            {
                po.print(cells[i][j].displayStatus() + "\t");
            }
            po.print("\n");
        }


    }

    @Override
    /**
     * Used for debugging not used in the in the output to the user.
     */
    public String toString(){
        return "";
    }

    /**
     * The getter method is used to return the height of the board. It is used for testing purpose.
     * @return the value of the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * The getter method is used to return the weight of the board. It is used for testing purpose.
     * @return the value of the weight of the board
     */

    public int getWidth() {
        return width;
    }
}
