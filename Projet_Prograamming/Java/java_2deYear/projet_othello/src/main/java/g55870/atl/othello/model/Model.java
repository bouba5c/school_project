package g55870.atl.othello.model;
/**
 * Interface defining the methods for managing the game model.
 */
public interface Model {
   /**
    * Starts the game with the specified game mode and board size.
    *
    * @param gameMode the mode of the game
    * @param h        the size of the board
    */
   void start(int gameMode, int h);

   /**
    * Gets the winner of the game.
    *
    * @return the ID of the winning player, or -1 if the game is not over
    */
   int getWinner();

   /**
    * Gets the ID of the current player.
    *
    * @return the ID of the current player
    */
   int getCurrentPlayerId();

   /**
    * Gets the color of the current player.

    * @return the color of the current player
    */
   Color getCurrentPlayerColor();

   /**
    * Gets the state of the game.
    * @return the state of the game
    */
   State getState();

   /**
    * Gets the pawn at the specified position on the board.
    *
    * @param position the position of the pawn
    * @return the pawn at the specified position
    */
   Pawn getPawn(Position position);

   /**
    * Puts a pawn on the board.
    *
    * @param pawn the pawn to put on the board
    */
   void putPawn(Pawn pawn);
    int getGameMode();
   /**
    * Moves to the next player's turn.
    */
   void nextPlayer();

   /**
    * Gets the size of the board.
    * @return the size of the board
    */
   int getBoardSize();

   /**
    * Gets the score of the black player.

    * @return the score of the black player
    */
   int getBlackScore();

   /**
    * Gets the score of the white player.
    * @return the score of the white player
    */
   int getWhiteScore();

   /**
    * Updates the all players information and the game element like possible moves list .
    */
   void update();

   /**
    * Surrenders the game, causing it to enter the GAME_OVER state.
    */
   void surrender();

   /**
    * Sets the state of the undo action.
    *
    * @param clicked true if the undo action was clicked, false otherwise
    */
   void setUndo(boolean clicked);

   /**
    * Undoes the last action in the game.
    *
    * @param isJavaFx true if the method is called from a JavaFX application, false otherwise
    */
   void undo(boolean isJavaFx);

   /**
    * Redoes the last undone action in the game.
    */
   void redoJavaFx();

   /**
    * Restarts the game, allowing players to play again when the game is finished.
    */
   void playAgain();

   /**
    * Sets the state of the redo action.
    * @param clicked true if the redo action was clicked, false otherwise
    */
   void setRedo(boolean clicked);

   /**
    * Sets the state of the game.
    * @param state the state to set
    */
   void setState(State state);

   /**
    * Adds an observer to the game model.
    * @param observer the observer to add
    */
   void addObserver(Observer observer);

   /**
    * Notifies all observers of changes in the game model.
    */
   void notifyObservers();

}
