package cs3331;

/**
 * Contains the model for the Connect Five board. (No GUI elements should placed here.)
 *
 * @author Andrea Torres
 */
public class Board {
    private Square[][] tiles;
    private boolean[][] isFilled;
    private int counter = 0;

    /**
     * Defines the size of the board
     */
    private final int size;

    /**
     * Constructor including size of board
     *
     * @param size Board Size
     */
    public Board(int size) {
        this.size = size;
        tiles= new Square[size][size];
        isFilled=new boolean[size][size];
        // Your Code Goes Here!
    }

    /**
     * Adds a disc to the game board.
     *
     * @param x x coordinate of where the disc needs to be placed.
     * @param y y coordinate of where the disc needs to be placed.
     */
    public void addDisc(int x, int y, int player) throws InValidDiskPositionException, PlayerWonException {
        if (isValidPosition(x, y)) {

            tiles[y][x] = new Square(x, y, player);
            isFilled[y][x] = true;
            counter++;
            if (checkForWin(tiles[y][x])) {
                throw new PlayerWonException();
            }

        } else {
            throw new InValidDiskPositionException();
        }
    }

    /**
     * Checks if input positions is valid. Checks if valid x-y range. Also checks if position is empty.
     *
     * @param x x input.
     * @param y y input.
     * @return Validity of placement of the disc.
     */
    private boolean isValidPosition(int x, int y) {
        // Your Code Goes Here!
        return !isFilled[y][x];
    }

    public Square getTiles(int x,int y){
        try {
            return tiles[y][x];
        }catch(NullPointerException e){
            return new Square(x,y,null);
        }
    }

    /**
     * Returns the size of this board.
     *
     * @return Returns size of board
     */
    public int size() {
        return size;
    }

    /**
     * This method is used to check for a tie
     *
     * @return true if tie, false otherwise
     */
    public boolean isBoardFull() {
        return counter >= Math.pow(size, 2);
    }

    private boolean checkForWin(Square square) {
        if (((checkWinHelper(square.getX(), square.getY(), square.getPlayer(),0,-1) + checkWinHelper(square.getX(), square.getY(),square.getPlayer(),0,1))) >= 6) {
            // System.out.println("Current Points:" + currpoints);
            return true;
        }
        if (checkWinHelper(square.getX(), square.getY(), square.getPlayer(),-1,0) + checkWinHelper(square.getX(), square.getY(), square.getPlayer(),1,0) >= 6)
            return true;
        if (checkWinHelper(square.getX(), square.getY(), square.getPlayer(),-1,-1) + checkWinHelper(square.getX(), square.getY(), square.getPlayer(),1,1) >= 6)
            return true;
        return checkWinHelper(square.getX(), square.getY(), square.getPlayer(),-1,1) + checkWinHelper(square.getX(), square.getY(), square.getPlayer(),1,-1) >= 6;
    }
    private int checkWinHelper(int x, int y,int player,int dx,int dy){
        try {
            if (tiles[y][x].getPlayer() == player) {
                return 1 + checkWinHelper(x+dx, y+dy, player,dx,dy);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
/*    //dx:0 dy:-1 upCheck
    private int upCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player) {
                return 1 + upCheck(x, y - 1, player);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:0 dy:1 downCheck
    private int downCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player)
                return 1 + downCheck(x, y + 1, player);

        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:-1 dy:0 leftCheck
    private int leftCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player)
                return 1 + leftCheck(x - 1, y, player);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:1 dy:0 rightCheck
    private int rightCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player) {
                return 1 + rightCheck(x + 1, y, player);
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:-1 dy:-1 leftUpCheck
    private int leftUpCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player) {
                return 1 + leftUpCheck(x - 1, y - 1, player);
            }
        } catch (NullPointerException e) {
            return 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:1 dy:1 rightDownCheck
    private int rightDownCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player)
                return 1 + rightDownCheck(x + 1, y + 1, player);
        } catch (NullPointerException e) {
            return 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:-1 dy:1 leftDownCheck
    private int leftDownCHeck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player) {
                return 1 + leftDownCHeck(x - 1, y + 1, player);
            }
        } catch (NullPointerException e) {
            return 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }
    //dx:1 dy:-1 rightUpCheck
    private int rightUpCheck(int x, int y, char player) {
        try {
            if (tiles[y][x].getPlayer().getSymbol() == player)
                return 1 + rightUpCheck(x + 1, y - 1, player);
        } catch (NullPointerException e) {
            return 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
        return 0;
    }*/
}
