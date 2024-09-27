package g55870.atl.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Represents the game board for Othello.
 */
public class Board {
    private final int height;
    private final Pawn[][] tiles;
    private final List<Position> possibleWhiteMoves;
    private final List<Position> possibleBlackMoves;
    private final List<Position> opponentPosition;
    /**
     * Constructs a new Board with the specified height.
     *
     * @param height the height of the board
     * @throws IllegalArgumentException if the height is less than or equal to 3 or greater than or equal to 15
     */
    public Board(int height)
    {
        if (height <= 3 || height >= 15) {
            throw new IllegalArgumentException("size of board must be between 3 and 15");
        }
        this.height = height;
        possibleBlackMoves = new ArrayList<>();
        possibleWhiteMoves = new ArrayList<>();
        opponentPosition = new ArrayList<>();
        tiles = new Pawn[height][height];
    }
    /**
     * Gets the size of the board.
     *
     * @return the size of the board
     */
    public int getSize() {
        return height;
    }
    /**
     * Checks if the board is full.
     *
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (tiles[i][j] == null ||tiles[i][j].getColor().equals(Color.NONE)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Puts a pawn on the board at the specified position.
     *
     * @param pawn the pawn to put on the board
     * @throws NullPointerException if the pawn is null
     */
    public void put(Pawn pawn) {
        Objects.requireNonNull(pawn,"pawn requis");
        tiles[pawn.getPosition().x()][pawn.getPosition().y()] = pawn;
    }
    /**
     * Gets the pawn at the specified position on the board.
     *
     * @param pos the position of the pawn
     * @return the pawn at the specified position, or null if there is no pawn
     */
    public Pawn getTile(Position pos) {
        if (pos == null) {
            return null;
        }
        return tiles[pos.x()][pos.y()];
    }

    /**
     * Checks if a position is inside the board.
     *
     * @param pos the position to check
     * @return true if the position is inside the board, false otherwise
     * @throws NullPointerException if the position is null
     */
    public boolean isInside(Position pos) {
        Objects.requireNonNull(pos,"position requis");

        return pos.x() >= 0 && pos.x() < getSize() && pos.y() >= 0 && pos.y() < getSize();
    }
    /**
     * Checks if there are possible moves for a given pawn.
     *
     * @param pawn The pawn to check.
     * @return True if there are possible moves, otherwise false.
     * @throws NullPointerException if the pawn is null
     */
    public boolean checkPossibleMoves(Pawn pawn) {
        Objects.requireNonNull(pawn,"pawn requis");

        if(tiles[pawn.getPosition().x()][pawn.getPosition().y()]!=null)
        {
            return false;
        }
        return checkDiagonale(pawn) || checkColumn(pawn) || checkRow(pawn);

    }
    /**
     * Flips pawns in all directions .
     * @param pawn The pawn representing the move.
     * @throws NullPointerException if the pawn is null
     */
    public void flipPawn(Pawn pawn)
    {
        Objects.requireNonNull(pawn,"pawn requis");
        List<List<Position>> list = new ArrayList<>();
        list.add(oponentUpPositionList(pawn));
        list.add(oponentDownPositionList(pawn));
        list.add(oponentRightPositionList(pawn));
        list.add(oponentLeftPositionList(pawn));
        list.add(oponentLeftCornerDownPositionList(pawn));
        list.add(oponentLeftCornerUpPositionList(pawn));
        list.add(oponentRightCornerDownPositionList(pawn));
        list.add(oponentRightCornerUpPositionList(pawn));
        for (List<Position> pos:
                list) {
            for (Position flipPosition:
                    pos) {
                opponentPosition.add(flipPosition);
                getTile(flipPosition).flipPawn();
            }
        }
    }
    /**
     * Returns the list of positions of opponent's pawns.
     *
     * @return The list of positions of opponent's pawns.
     */
    public List<Position> getOpponentPosition()
    {
        return opponentPosition;
    }
    /**
     * Returns a list of possible moves for black pawns.
     *
     * @return A list of possible moves for black pawns.
     */
    public List<Position> getPossibleBlackMoves() {
        for(int i = 0 ; i < getSize() ; i++) {
            for (int j = 0; j < getSize(); j++) {

                Position currentPosition = new Position(i, j);
                Pawn blackPawn = new Pawn(currentPosition, Color.BLACK);
                if (tiles[i][j] == null && checkPossibleMoves(blackPawn)) {
                    possibleBlackMoves.add(currentPosition);
                }
            }
        }
        return possibleBlackMoves;
    }

    /**
     * Returns a list of possible moves for white pawns.
     *
     * @return A list of possible moves for white pawns.
     */
    public List<Position> getPossibleWhiteMoves() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Position currentPosition = new Position(i, j);
                Pawn whitePawn = new Pawn(currentPosition, Color.WHITE);
                if (tiles[i][j] == null && checkPossibleMoves(whitePawn)) {
                    possibleWhiteMoves.add(currentPosition);
                }
            }
        }
        return possibleWhiteMoves;
    }
    /**
     * Returns a list of black pawns on the board.
     *
     * @return A list of black pawns on the board.
     */
    public List<Pawn> getBlackPawns() {
        List<Pawn> blackPions = new ArrayList<>();
        for(int i = 0 ; i < getSize() ; i++) {
            for (int j = 0; j < getSize(); j++) {
                if (tiles[i][j] != null &&tiles[i][j].getColor().equals(Color.BLACK)) {
                    blackPions.add(new Pawn(new Position(i, j), Color.BLACK));
                }
            }
        }
        return blackPions;
    }

    /**
     * Returns a list of white pawns on the board.
     *
     * @return A list of white pawns on the board.
     */
    public List<Pawn> getWhitePawns()
    {
        List<Pawn> whitePions = new ArrayList<>();
        for(int i = 0 ; i < getSize() ; i++) {
            for (int j = 0; j < getSize(); j++) {
                if (tiles[i][j] != null &&tiles[i][j].getColor().equals(Color.WHITE)) {
                    whitePions.add(new Pawn(new Position(i, j), Color.WHITE));
                }
            }
        }
        return whitePions;
    }
    /**
     * Removes the specified pawn from the board.
     *
     * @param pawn The pawn to remove.
     * @throws NullPointerException when pawn is null
     */
    public void removePawn(Pawn pawn)
    {
        Objects.requireNonNull(pawn,"pawn requis");
        this.tiles[pawn.getPosition().x()][pawn.getPosition().y()]=null;
    }



    /**
     * Counts the number of opponent pawns around a given position respecting the rule of the game.
     *
     * @param position     The position to check.
     * @param currentColor The color of the pawns being counted.
     * @param enemyColor   The color of the opponent's pawns.
     * @return The number of opponent pawns surrounding the given position.
     * @throws NullPointerException If any of the parameters are null.
     */
    int countOpponent(Position position,Color currentColor , Color enemyColor)
    {
        Objects.requireNonNull(position,"position requis");
        Objects.requireNonNull(currentColor,"position requis");
        Objects.requireNonNull(enemyColor,"position requis");
        List<List<Position>> list = new ArrayList<>();
        int count =0;
        put(new Pawn(position,currentColor));
        list.add(oponentUpPositionList(getTile(position)));
        list.add(oponentDownPositionList(getTile(position)));
        list.add(oponentRightPositionList(getTile(position)));
        list.add(oponentLeftPositionList(getTile(position)));
        list.add(oponentLeftCornerDownPositionList(getTile(position)));
        list.add(oponentLeftCornerUpPositionList(getTile(position)));
        list.add(oponentRightCornerDownPositionList(getTile(position)));
        list.add(oponentRightCornerUpPositionList(getTile(position)));
        list.removeIf(List::isEmpty);
        for (List<Position> posList:
                list) {
            for (Position pos:
                    posList) {
                if(getTile(pos).getColor().equals(enemyColor))
                {
                    count++;
                }
            }
        }
        removePawn(new Pawn(position,currentColor));
        return count;
    }
    /**
     * Finds positions of opponent pawns in a specific direction from the given pawn.
     * @param pawn The pawn to start from.
     * @param dx   The horizontal direction (+1 for down, -1 for up, 0 for no movement).
     * @param dy   The vertical direction (+1 for right, -1 for left, 0 for no movement).
     * @return A list of positions of opponent pawns in the specified direction.
     */
    private List<Position> positionOfOpponentPawn(Pawn pawn, int dx, int dy) {
        List<Position> positionList = new ArrayList<>();
        int x = pawn.getPosition().x();
        int y = pawn.getPosition().y();
        Pawn lastPawn = null;
        int i = x + dx;
        int j = y + dy;
        while (i >= 0 && i < getSize() && j >= 0 && j < getSize()) {
            if (this.tiles[i][j] == null || tiles[i][j].getColor().equals(Color.NONE)) {
                break;
            }
            lastPawn = new Pawn(new Position(i, j), getTile(new Position(i, j)).getColor());

            if (this.tiles[i][j] != null && !pawn.getColor().equals(tiles[i][j].getColor())) {
                positionList.add(tiles[i][j].getPosition());
            }

            if (this.tiles[i][j] != null && pawn.getColor().equals(tiles[i][j].getColor())) {
                break;
            }
            i += dx;
            j += dy;
        }
        if (lastPawn != null && !pawn.getColor().equals(lastPawn.getColor())) {
            positionList.clear();
        }
        return positionList;
    }

    private List<Position> oponentRightPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,0,1);
    }

    private List<Position> oponentLeftPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,0,-1);

    }
    private List<Position> oponentUpPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,-1,0);

    }
    private List<Position> oponentDownPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,1,0);

    }
    private List<Position> oponentLeftCornerUpPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,-1,-1);

    }

    private List<Position> oponentRightCornerDownPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,1,1);

    }


    private List<Position> oponentRightCornerUpPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,-1,1);

    }
    private List<Position> oponentLeftCornerDownPositionList(Pawn pawn)
    {
        return positionOfOpponentPawn( pawn,1,-1);

    }

    /**
     * Gets the pawn in the specified direction from the given pawn.
     *
     * @param pawn The pawn to start from.
     * @param dx   The horizontal direction (+1 for down, -1 for up, 0 for no movement).
     * @param dy   The vertical direction (+1 for right, -1 for left, 0 for no movement).
     * @return The pawn in the specified direction, or null if not present.
     */
    private Pawn getDirectionPawn(Pawn pawn, int dx, int dy)
    {
        int row = pawn.getPosition().x() + dx;
        int col = pawn.getPosition().y() + dy;
        Position position = new Position(row, col);

        if (isInside(position)) {
            if (getTile(position) == null) {
                return null;
            }
            return new Pawn(position, getTile(position).getColor());
        }
        return null;
    }


    private boolean checkDiagonale(Pawn pawn)
    {
        return (checkLeftCornerDown(pawn) || checkLeftCornerUP(pawn) || checkRightCornerUP(pawn) || checkRightCornerDown(pawn));

    }
    private boolean checkRow(Pawn pawn)
    {
        return checkRight(pawn) || checkLeft(pawn);
    }
    private boolean checkColumn(Pawn pawn)
    {
        return checkUp(pawn) || checkDown(pawn);
    }
    private boolean checkDirection(Pawn pawn , int dx ,int dy)
    {
        int x = pawn.getPosition().x();
        int y = pawn.getPosition().y();
        if(getDirectionPawn(pawn,dx,dy)==null)
        {
            return false;
        }
        int i = x + dx;
        int j = y + dy;
        boolean foundOpponent = false;

        while (i >= 0 && i < getSize() && j >= 0 && j < getSize()) {
            Pawn currentPawn = this.tiles[i][j];

            if (currentPawn == null || currentPawn.getColor().equals(Color.NONE)) {
                return false;
            }

            if (currentPawn.getColor().equals(pawn.getColor())) {
                return foundOpponent;
            } else {
                foundOpponent = true;
            }
            i += dx;
            j += dy;
        }

        return false;
    }
    private boolean checkUp(Pawn pawn)
    {
        return checkDirection(pawn,-1,0);
    }
    private boolean checkDown(Pawn pawn)
    {
        return checkDirection(pawn,1,0);
    }

    private boolean checkLeft(Pawn pawn)
    {
        return checkDirection(pawn,0,-1);
    }

    private boolean checkRight(Pawn pawn)
    {
        return checkDirection(pawn,0,1);
    }
    private boolean checkLeftCornerDown(Pawn pawn)
    {
        return checkDirection(pawn,1,-1);
    }
    private boolean checkLeftCornerUP(Pawn pawn)
    {
        return (checkDirection(pawn,-1,-1));
    }
    private boolean checkRightCornerDown(Pawn pawn) {
        return (checkDirection(pawn,1,1));
    }
    private boolean checkRightCornerUP(Pawn pawn) {
        return (checkDirection(pawn,-1,1));
    }


}
