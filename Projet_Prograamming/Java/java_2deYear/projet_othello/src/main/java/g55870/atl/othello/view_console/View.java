package g55870.atl.othello.view_console;

/**
 * Interface for the console view of the Othello game.
 */
public interface View {
    /**
     * Displays the welcome message.
     */
    void displayWelcome();

    /**
     * Displays the game.
     */
    void displayGame();

    /**
     * Displays the winner of the game.
     */
    void displayWinner();

    /**
     * Displays the help information.
     */
    void displayHelp();

    /**
     * Asks the user for a command and returns the input.
     *
     * @return The user input command.
     */
    String askCommand();

    /**
     * Displays the current score of the game.
     */
    void displayScore();

    /**
     * Displays an error message.
     *
     * @param s The error message to display.
     */
    void displayError(String s);

    /**
     * Displays the available commands.
     */
    void displayCommands();
}