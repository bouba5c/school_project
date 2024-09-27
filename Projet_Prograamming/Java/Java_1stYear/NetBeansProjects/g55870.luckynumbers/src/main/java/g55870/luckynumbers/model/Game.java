package g55870.luckynumbers.model;

import java.util.Collections;
import java.util.List;

import static g55870.luckynumbers.model.State.*;
import java.util.ArrayList;

public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

    public Game() {
        this.state = NOT_STARTED;

    }

    @Override
    public void start(int playerCount) {
        if (!(this.state == NOT_STARTED || this.state == GAME_OVER)) {
            throw new IllegalStateException("The state is not started and not or game over");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("the number of player is not beetween 2 and 4");
        }
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        this.deck = new Deck(playerCount);
        for (int i = 0; i < playerCount; i++) {
            this.boards[i] = new Board();
            putTileDiag(i);

        }
        this.currentPlayerNumber = 0;
        this.state = PICK_TILE;

    }

    // test method without random diagonal
    void startTest(int playerCount) {
        if (!(this.state == NOT_STARTED || this.state == GAME_OVER)) {
            throw new IllegalStateException("The state is not started and not or game over");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("the number of player is not beetween 2 and 4");
        }
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        this.deck = new Deck(playerCount);
        for (int i = 0; i < playerCount; i++) {
            this.boards[i] = new Board();
        }
        this.currentPlayerNumber = 0;
        this.state = PICK_TILE;
    }

    //test method
    Tile pickFaceDownTile(int value) {
        if (this.state != PICK_TILE) {
            throw new IllegalStateException("State is not Pick Tile");
        }
        this.pickedTile = new Tile(value);
        this.state = PLACE_TILE;
        return this.pickedTile;
    }

    @Override
    public int getBoardSize() {
        return this.boards[this.currentPlayerNumber].getSize();
    }

    /**
     * will pick 4 tiles in the pick face down count
     *
     * @return the list of 4 random tiles and will sort it
     */
    private List<Tile> pickTiles() {
        var listOf4Tiles = new ArrayList<Tile>();
        for (int i = 0; i < 4; i++) {
            listOf4Tiles.add(deck.pickFaceDown());
        }
        Collections.sort(listOf4Tiles, (Tile o1, Tile o2) -> o1.getValue() - o2.getValue());
        return listOf4Tiles;
    }

    /**
     * Will put the 4 tiles diagonally in the board.
     *
     * @param player : we will put the 4 tiles diagonally in the board of that
     * player.
     */
    private void putTileDiag(int player) {
        List<Tile> listOf4Tiles = pickTiles();
        for (int i = 0; i < 4; i++) {
            this.deck.pickFaceUp(listOf4Tiles.get(i));
            this.boards[player].put(listOf4Tiles.get(i), new Position(i, i));
        }
    }

    @Override
    public void putTile(Position pos) {
        if (!(this.state == PLACE_TILE || this.state == PLACE_OR_DROP_TILE)) {
            throw new IllegalStateException("The state is not Place Tile!");
        }
        if (!canTileBePut(pos)) {
            throw new IllegalArgumentException("The tile cannot be put in that position!");
        }

        if (this.boards[this.currentPlayerNumber].getTile(pos) != null) {
            deck.putBack(this.boards[this.currentPlayerNumber].getTile(pos));
            this.boards[this.currentPlayerNumber].put(this.pickedTile, pos);

        } else {
            this.deck.pickFaceUp(this.pickedTile);
            this.boards[this.currentPlayerNumber].put(this.pickedTile, pos);
        }

        this.state = (this.boards[this.currentPlayerNumber].isFull() || (this.deck.faceDownCount() == 0 && checkPlayerBoard())) ? GAME_OVER : TURN_END;
    }

    /**
     * will compare the currentplayernumber board's cells and the other player
     * board's cell. if the currentPlayer board has more tile or equals than the
     * other players there are several winner : the result will be true if the
     * currentPlayer is inferior than the other player : the result will be
     * false
     *
     * @return the result
     */
    private boolean checkPlayerBoard() {
        int cellCount = this.boards[currentPlayerNumber].countOccupiedCell();
        boolean result = false;
        for (int i = 0; i < this.boards.length && i != currentPlayerNumber; i++) {
            if (this.boards[i].countOccupiedCell() <= cellCount) {
                result = true;
            } else if (this.boards[i].countOccupiedCell() > cellCount) {
                return false;
            }
        }
        return result;
    }

    @Override
    public void nextPlayer() {
        if (this.state != TURN_END) {
            throw new IllegalStateException("State is not Turn End!");
        }
        this.currentPlayerNumber = ++this.currentPlayerNumber % this.playerCount;
        this.state = PICK_TILE;

    }

    @Override
    public int getPlayerCount() {
        if (this.state == NOT_STARTED) {
            throw new IllegalStateException("State is Not started!");
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (this.state == NOT_STARTED || this.state == GAME_OVER) {
            throw new IllegalStateException("State is Not Started or Game Over!");
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (!(this.state == PLACE_TILE || this.state == PLACE_OR_DROP_TILE)) {
            throw new IllegalStateException("The state is a not Place Tile nor PLACE OR DROP TILE!");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return this.boards[this.currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (!(this.state == PLACE_TILE || this.state == PLACE_OR_DROP_TILE)) {
            throw new IllegalStateException("The state is a not Place Tile nor PLACE OR DROP TILE!");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position is outside the board!");
        }
        return this.boards[this.currentPlayerNumber].canBePut(this.pickedTile, pos);
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (this.state == NOT_STARTED) {
            throw new IllegalStateException("Game is Not Started !");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("The position is outside the board ");
        }

        if (playerNumber < playerCount && playerNumber > playerCount) {
            throw new IllegalArgumentException("The player number is outside of range");
        }
        return this.boards[playerNumber].getTile(pos);
    }

    @Override
    public ArrayList<Integer> getWinners() {
        if (this.state != GAME_OVER) {
            throw new IllegalStateException("Game state is not Game Over!");
        }
        var winners = new ArrayList<Integer>();
        for (int i = 0; i < this.boards.length; i++) {
            if (this.boards[i].isFull()) {
                winners.add(i);  // index of the winner
            }
        }
        return winners;
    }

    @Override
    public Tile pickFaceDownTile() {
        if (this.state != PICK_TILE) {
            throw new IllegalStateException("State is not Pick Tile");
        }
        this.state = PLACE_OR_DROP_TILE;
        this.pickedTile = this.deck.pickFaceDown();
        return this.pickedTile;
    }

    @Override
    public void pickFaceUpTile(Tile tile) {
        if (this.state != PICK_TILE) {
            throw new IllegalStateException("State is not Pick Tile");
        }
        if (!deck.hasFaceFaceUp(tile)) {
            throw new IllegalArgumentException("The picked tile doesn't exist or is not visible in the deck");
        }
        this.state = PLACE_TILE;
        this.pickedTile = tile;
        this.deck.pickFaceUp(this.pickedTile);

    }

    @Override
    public void dropTile() {
        if (this.state != PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("State is not Place or drop Tile");
        }

        this.state = TURN_END;
        deck.putBack(this.pickedTile);
    }

    @Override
    public int faceDownTileCount() {
        return this.deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        return this.deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        return Collections.unmodifiableList(this.deck.getAllFaceUp());
    }

    public List<Tile> getAllfaceDownTiles() {
        return Collections.unmodifiableList(this.deck.getAllFaceDown());
    }
}

// fast test class with a main 
/*    public static void main(String[] args) {
        Model game = new Game();
        game.start(2);
        System.out.println(game.getPlayerCount());
        game.pickTile();
        Position pos = new Position(0, 0);
        game.putTile(pos);
        game.nextPlayer();
        System.out.println(game.getCurrentPlayerNumber());

}*/
