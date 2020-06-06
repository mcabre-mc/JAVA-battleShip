package battleship;
import java.io.*;

/**
 * This is the class that runs the game loop.
 * @author Vijay Gangayadi Venkatesh
 * @author Sagar Suhas Karambelakar
 */
public class Battleship {
    private static String BAD_ARG_COUNT = "Wrong number of arguments for command ";
    private static String ALL_SHIPS_SUNK = "All Ships Sunk!";
    private static String BAD_CONFIG_FILE = "Bad Configuration!";
    private static String DIM_TOO_BIG = "The given board size is too large.";
    private static int MAX_DIM = 20;
    private static String MISSING_SETUP_FILE = "No filename specified";
    private static String PROMPT = ">";
    private static String WHITESPACE = " ";

    /**
     * Takes user commands and runs the game loop.
     * @param args Gets the file name of the game configuration to run.
     */
    public static void main(String[] args) {
        if(args.length != 1)
        {
            System.out.println(MISSING_SETUP_FILE);
            System.exit(2);
        }
        String fileName = args[0]; // replace with command line argument
        boolean isSavedGame = false;
        Board playerBoard = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            isSavedGame = true;
            System.out.print("Checking if " + fileName + " is a saved game..");
            playerBoard = (Board) ois.readObject();
            System.out.println("Yes");
            playerBoard.display(System.out);
        } catch (Exception e) {
            System.out.println("No, will read as a text setup file");
        }
        try {

            if (!isSavedGame) {
                int counter = 1;

                Ship ships[] = new Ship[0];
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                String currentLine = bufferedReader.readLine();
                while (currentLine != null) {
                    if (counter == 1) {
                        String[] boardSize = currentLine.split(" ");
                        if (boardSize.length == 2) {
                            int uRow = Integer.parseInt(boardSize[0]);
                            int lCol = Integer.parseInt(boardSize[1]);
                            if(uRow > MAX_DIM || lCol >MAX_DIM)
                            {
                                System.out.println(DIM_TOO_BIG);
                                System.exit(1);
                            }
                            playerBoard = new Board(uRow, lCol);
                        } else {
                            System.out.println(BAD_CONFIG_FILE);
                            System.exit(0);
                        }
                    } else {
                        String[] shipDetails = currentLine.split(" ");
                        if (shipDetails.length == 4) {
                            int uRow = Integer.parseInt(shipDetails[0]);
                            int lCol = Integer.parseInt(shipDetails[1]);
                            String orientation = shipDetails[2];
                            Ship.Orientation orientation1 = Ship.Orientation.valueOf(orientation);
                            int length = Integer.parseInt(shipDetails[3]);
                            Ship s = new Ship(playerBoard, uRow, lCol, orientation1, length);
                            playerBoard.addShip(s);
                        } else {
                            System.out.println(BAD_CONFIG_FILE);
                            System.exit(0);
                        }
                    }
                    currentLine = bufferedReader.readLine();
                    counter++;

                }
                bufferedReader.close();
            }
        } catch (IOException |OverlapException | OutOfBoundsException ie){
            System.out.println(ie);
            System.exit(1);

        }
        while (!playerBoard.allSunk()) {
            try {
                //game loop
                System.out.print(PROMPT);
                BufferedReader userInputStream = new BufferedReader(new InputStreamReader(System.in));
                String[] userInput = userInputStream.readLine().split(WHITESPACE);
                char command = userInput[0].charAt(0);
                if (command == 'h') {
                    if (userInput.length != 3) {
                        System.out.println(BAD_ARG_COUNT + command);
                        System.out.println("Usage: h row column");
                        continue;
                    }
                    int row = Integer.parseInt(userInput[1]);
                    int column = Integer.parseInt(userInput[2]);
                    Cell hitCell = playerBoard.getCell(row, column);
                    hitCell.hit();
                    playerBoard.display(System.out);
                }
                if (command == 's') {
                    if (userInput.length != 2) {
                        System.out.println(BAD_ARG_COUNT + command);
                        System.out.println("Usage: s saveFileName");
                        continue;
                    }
                    String saveFileName = userInput[1];
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFileName));
                    oos.writeObject(playerBoard);

                }
                if (command == 'q') {
                    System.exit(0);
                }
                if (command == '!') {
                    playerBoard.fullDisplay(System.out);
                }
            } catch (BattleshipException be) {
                System.out.println(be);
            }

            catch(IOException ie)
            {
                //check
                System.out.println(ie);
            }

        }
        System.out.println(ALL_SHIPS_SUNK);
    }
}




