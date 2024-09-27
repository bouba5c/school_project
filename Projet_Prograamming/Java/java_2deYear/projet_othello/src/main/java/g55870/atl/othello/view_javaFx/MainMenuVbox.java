package g55870.atl.othello.view_javaFx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MainMenuVbox extends VBox {
    private final MenuItem helpItem;
    private final MenuItem exitItem;
    private final InputGridMainMenu inputGrid ;
    private final MenuBar menuBar;


    /**
     * Constructs a MainMenuVbox object.
     */
    public MainMenuVbox()
    {
        menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        helpItem = new MenuItem("help");
        exitItem = new MenuItem("exit");
        inputGrid = new InputGridMainMenu();
        menuFile.getItems().addAll(helpItem,exitItem);
        getChildren().addAll(menuBar,inputGrid);
        setSpacing(50);
    }
    /**
     * Gets the menu bar containing file options.
     * @return The MenuBar object.
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }
    /**
     * Gets the help menu item.
     * @return The MenuItem object for help.
     */
    public MenuItem getHelpItem() {
        return helpItem;
    }
    /**
     * Gets the exit menu item.
     * @return The ExitItem object for exit.
     */
    public MenuItem getExitItem() {
        return exitItem;
    }
    /**
     * Gets the input grid for game settings.
     * @return The InputGridMainMenu object.
     */
    public InputGridMainMenu getInputGrid() {
        return inputGrid;
    }
}
