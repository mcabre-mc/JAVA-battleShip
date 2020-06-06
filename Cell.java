package battleship;

import java.io.Serializable;

import static java.util.Objects.isNull;

/**
 * A single spot on the Battleship game board. A cell knows if there is a ship on it, and remember if it has been hit.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelkar
 */

public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    private boolean isHit = false;
    private boolean isShip = false;
    private Ship shipOnCell;
    private int row;
    private int column;

    /**
     * The constructor instantiates the cell's row and column in which it belongs.
     * @param row the row at which the cell is present.
     * @param column the column at which the cell is present.
     */
    public Cell(int row,int column)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public Ship getShipOnCell(){return shipOnCell;}
    public boolean getisHit(){return isHit;}

    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    public void putShip(Ship ship) throws OverlapException {
        if(isNull(shipOnCell))
            shipOnCell = ship;
        else
            throw new OverlapException(row,column,"Another ship overlaps this one at: ");
    }

    /**
     * The method is called if the user hits a location to check if a ship is hit. W
     * @throws CellPlayedException Throws an exception if the cell combination is already played.
     */
    public void hit() throws CellPlayedException {
        if(isHit) {
            throw new CellPlayedException( this.row, this.column, "Cell already hit");
        }
        else{
            isHit = true;

            if(!isNull(shipOnCell)) {
                shipOnCell.hit();
                if (shipOnCell.isSunk()) {
                    System.out.println("A BattleShip has sunk!");
                }
            }
            }
    }

    /**
     * The method will determine which symbol should be used to display the cell in the console based on the hit status.
     * @return the corresponding charecter the cell must be displayed in the console.
     */

    public char displayHitStatus(){
        if(isHit)
        {
            if(!isNull(shipOnCell)) {
                if (shipOnCell.isSunk())
                    return SUNK_SHIP_SECTION;
                else
                    return HIT_SHIP_SECTION;
            }
            else
                return HIT_WATER;
        }
        else
            return PRISTINE_WATER;
    }

    /**
     * The method checks if the cell is hit, if yes it calls the displayHitStatus.
     * @return  The corresponding character to display in the output
     */
    public char displayStatus(){
        if(isHit)
        {
                return displayHitStatus();
        }
        else
        {
            if(isNull(shipOnCell))
                return PRISTINE_WATER;
            else
                return HIDDEN_SHIP_SECTION;
        }
    }
}
