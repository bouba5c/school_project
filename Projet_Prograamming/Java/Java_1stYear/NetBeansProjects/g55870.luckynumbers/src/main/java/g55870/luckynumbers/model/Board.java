package g55870.luckynumbers.model;

public class Board {

    private final Tile[][] tiles;

    /**
     * The constructor of the class Board : is a 2D array with 4 column and 4
     * rows
     */
    public Board() {
        this.tiles = new Tile[4][4];
    }

    /**
     * Is a getter method of the board to have the size of the board.
     *
     * @return the numbers of rows( or columns) of the board
     */
    public int getSize() {
        return this.tiles.length;
    }

    /**
     * @param pos the position of the board
     * @return a boolean true if it's inside false otherwise.
     */
    public boolean isInside(Position pos) {
        int column = pos.getColumn();
        int row = pos.getRow();
        return (column >= 0 && row >= 0 && row <= getSize() - 1 && column <= getSize() - 1);
    }

    /**
     * Is a getter method to get the tile of a given position.
     *
     * @param pos : position of a tile
     * @return the tile of a given position
     */
    public Tile getTile(Position pos) {
        if (pos == null) {
            return null;
        }
        return this.tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * check if the square can be put in the board according to the rules of the
     * lucky numbers game.
     *
     * @param tile : the tile to put in the board
     * @param pos : the position of the tile to put in the board
     * @return a boolean true if it respects the rule of the game and false
     * otherwise
     */
    public boolean canBePut(Tile tile, Position pos) {
        boolean canBePut = false;
        if (isEmpty()) {
            canBePut = true;
        }
        if (checkRow(tile, pos) && checkColumn(tile, pos)) {
            canBePut = true;
        }
        return canBePut;
    }

    /**
     * check the column of the board : if the right squares are empty or a
     * biggest tile compared to the tile to be given as parameter and if the
     * left squares are empty or a smaller tile compared to the tile to be given
     * as parameter
     *
     * @param tile tile to be compared with the right / left
     * @param pos position of the board to be compared with the right / left
     * @return true if the column is a ascending 2D array or the squares are
     * empty and not the same tiles as the previous tiles otherwise false.
     */
    private boolean checkColumn(Tile tile, Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int column = 0;
        var save = this.tiles[row][col];
        boolean check = true;
        this.tiles[row][col] = tile;
        //check if column elements are ascending order 
        var temp = 0;
        while (column < getSize() - 1 && check) {
            check = ((this.tiles[row][column] == null && this.tiles[row][column + 1] == null)
                    || (this.tiles[row][column] == null && this.tiles[row][column + 1] != null && this.tiles[row][column + 1].getValue() > temp)
                    || (this.tiles[row][column] != null && this.tiles[row][column].getValue() > temp && this.tiles[row][column + 1] == null)
                    || (this.tiles[row][column] != null && this.tiles[row][column + 1] != null && this.tiles[row][column + 1].getValue() > tiles[row][column].getValue()
                    && this.tiles[row][column].getValue() > temp));
            if (this.tiles[row][column] != null) {
                temp = this.tiles[row][column].getValue();
            }
            column++;
        }
        this.tiles[pos.getRow()][pos.getColumn()] = save;
        return check;
    }

    /**
     * check the rows of the board : if the down squares are empty or a biggest
     * tile compared to the tile to be given as parameter if the up squares are
     * empty or a smaller tile compared to the tile to be given as parameter.
     *
     * @param tile tile to be compared with the down / up
     * @param pos position of the board to be compared with the down / up
     * @return true if the row is ascending 2D array or the squares are empty
     * and not the same tiles as the previous tiles otherwise false.
     */
    private boolean checkRow(Tile tile, Position pos) {
        int rowTile = pos.getRow();
        int col = pos.getColumn();
        int row = 0;
        var save = this.tiles[rowTile][col];

        boolean check = true;
        this.tiles[rowTile][col] = tile;
        var temp = 0;
        //check if row elements are ascending order
        while (row < getSize() - 1 && check) {
            check = ((this.tiles[row][col] == null && this.tiles[row + 1][col] == null)
                    || (this.tiles[row][col] == null && this.tiles[row + 1][col] != null && this.tiles[row + 1][col].getValue() > temp)
                    || (this.tiles[row][col] != null && this.tiles[row][col].getValue() > temp && this.tiles[row + 1][col] == null)
                    || (this.tiles[row][col] != null && this.tiles[row + 1][col] != null && this.tiles[row + 1][col].getValue() > tiles[row][col].getValue() && this.tiles[row][col].getValue() > temp));
            if (this.tiles[row][col] != null) {
                temp = this.tiles[row][col].getValue();
            }
            row++;
        }
        this.tiles[pos.getRow()][pos.getColumn()] = save;
        return check;
    }

    /**
     * This methode will put a tile at a given position
     *
     * @param tile : the value of the tile
     * @param pos : the position of the tile in the board
     */
    public void put(Tile tile, Position pos) {
        this.tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * check if the board is full of tiles
     *
     * @return a boolean true if the board is full else false
     */
    public boolean isFull() {
        for (Tile[] array : this.tiles) {
            for (Tile board : array) {
                if (board == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Give the number of occupied cell int the board.
     *
     * @return the number of occupied cell
     */
    int countOccupiedCell() {
        int count = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * this method will check if the board is empty.
     *
     * @return true is the board is empty false otherwise.
     */
    private boolean isEmpty() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

}
