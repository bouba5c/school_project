package g55870.luckynumbers.model;

public class Position {

    private final int row;
    private final int column;

    /**
     * The constructor of the class Position which going to indicate a position
     * of the board
     *
     * @param row : a row of the board
     * @param column : a column of the board
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * this method is a getter of row's of a given position
     *
     * @return the object of a row
     */
    public int getRow() {
        return row;
    }

    /**
     * this method is a getter of a column of a given position
     *
     * @return the object a column
     */
    public int getColumn() {
        return column;
    }

}
