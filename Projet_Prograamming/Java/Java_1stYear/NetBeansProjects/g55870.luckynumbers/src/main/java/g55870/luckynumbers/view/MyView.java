package g55870.luckynumbers.view;

import g55870.luckynumbers.model.Model;
import g55870.luckynumbers.model.Position;
import g55870.luckynumbers.model.Tile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
The class MyView contains it s like the user interface in other words all that the player will see.
 */
public class MyView implements View {

    private final Model model;

    /**
     * Constructor of the class MyView
     *
     * @param model the game's model
     */
    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println("  ================================== ");
        System.out.println("  ||          Bienvenue au        || ");
        System.out.println("  ||      Lucky Numbers game      || ");
        System.out.println("  ||    Auteur : Balde Boubacar   || ");
        System.out.println("  ||   Version 01 : 02-04-2021    || ");
        System.out.println("  ================================== ");

    }

    @Override
    public void displayGame() {
        System.out.println();
        System.out.print("Plateau ");
        displayCurrentNumber();
        displayBoard();
        displayPickedTile();

    }

    @Override
    public void displayWinner() {
        String message = "Les vainqueurs sont les joueurs : ";
        var winners = this.model.getWinners();
        for (int winner : winners) {
            System.out.printf("%s : %d", message, winner + 1);
        }
        System.out.println();
    }

    @Override
    public int askPlayerCount() {
        Scanner sc = new Scanner(System.in);
        int playerCount;
        while (true) {
            System.out.println("Veuillez donner le nombre de joueur :  ");
            try {
                playerCount = sc.nextInt();
                if (playerCount <= 4 && playerCount >= 2) {
                    return playerCount;
                } else {
                    displayError("Le nombre de joueur doit être compris entre 2 et 4");
                }
            } catch (InputMismatchException e) {
                displayError("format invalide : Entrer un nombre compris entre 2 et 4.");
                sc.next();
            }
            System.out.println();
        }
    }

    @Override
    public Position askPosition() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choix :");
        while (true) {
            try {
                System.out.print("ligne : ");
                int row = (sc.nextInt() - 1);
                System.out.print("colonne : ");
                int column = (sc.nextInt() - 1);
                return new Position(row, column);
            } catch (InputMismatchException e) {
                displayError("Format invalide : entrer un nombre compris entre 1 et 4 ");
                sc.next();
            }
            System.out.println();
        }
    }

    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

    /**
     * display the current number start with 1
     */
    private void displayCurrentNumber() {
        System.out.println("Joueur " + (this.model.getCurrentPlayerNumber() + 1) + " :");
    }

    /**
     * display the board of the current player and the tile to place. if the
     * board is null or the position is empty the value will be clover. else the
     * the position is occupied it will show the value of the tile.
     */
    private void displayBoard() {
        displayColumn();
        System.out.println(" ---------------------");
        for (int row = 0; row < this.model.getBoardSize(); row++) {
            System.out.print((row + 1) + "|");
            for (int column = 0; column < this.model.getBoardSize(); column++) {
                String tile = model.getTile(model.getCurrentPlayerNumber(), new Position(row, column)) == null ? "♧" : String.valueOf(this.model.getTile(model.getCurrentPlayerNumber(), new Position(row, column)).getValue());
                System.out.printf("%4s ", tile);
            }
            System.out.println();
        }
        System.out.println(" ---------------------");
        System.out.println();
    }

    /**
     * display number column 1 2 3 4 will be used for DisplayBoard method
     */
    private void displayColumn() {
        for (int i = 0; i < model.getBoardSize() + 1; i++) {
            if (i == 0) {
                System.out.print("   ");
            } else {
                System.out.print("  " + i + "  ");
            }
        }
        System.out.println();
    }

    /**
     * display the picked tile
     */
    private void displayPickedTile() {
        System.out.println(" Tuile Choisie  : " + this.model.getPickedTile().getValue());
    }

    @Override
    public Tile askTile() {
        displayCurrentNumber();
        System.out.println();
        displayAllFaceUpTiles();
        System.out.println();
        displayFaceDownCount();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        if (this.model.faceUpTileCount() == 0) {
            System.out.println("Commande c : Pour piocher une tuile face caché ");
        } else {
            System.out.println("Option v : Pour piocher une tuile face visible. ");
            System.out.println("Option c : Pour piocher une tuile face caché ");
        }

        Tile tile = null;
        char option = sc.nextLine().charAt(0);
        while (option != 'v' && option != 'c') {
            displayError("Commande invalide : veuillez entrer la bonne commande : ");
            option = sc.nextLine().charAt(0);
        }
        while (option == 'v' && this.model.faceUpTileCount() == 0) {
            System.out.println("Veuillez entrer la commande 'c' car il n'y pas de pioche face visible : ");
            option = sc.nextLine().charAt(0);
        }
        if (option == 'v') {
            tile = askPickFaceUp();
        } else if (option == 'c') {
            tile = this.model.pickFaceDownTile();
        }
        return tile;
    }

    @Override
    public char askDropOrPlace() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Option r : Pour replacer une tuile face visible dans la pioche ");
        System.out.println("Option p : Pour placer la tuile dans le plateau ");
        return sc.nextLine().charAt(0);

    }

    /**
     * Will ask the pick face up tile to a user.
     *
     * @return the pick face tile
     */
    private Tile askPickFaceUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("piocher une tuile face visible :");
        displayAllFaceUpTiles();
        while (true) {
            try {
                System.out.print("tuile : ");
                int value = sc.nextInt();
                Tile tile = new Tile(value);
                this.model.pickFaceUpTile(tile);
                return tile;
            } catch (InputMismatchException e) {
                displayError("Format invalide : entrer un nombre ");
                sc.next();
            } catch (IllegalArgumentException e) {
                displayError("La tuile est inexistante ou elle n'est pas visible ");
            }
            System.out.println();
        }
    }

    /**
     * Display the faceDownCount : the number of the tile face down
     */
    private void displayFaceDownCount() {
        System.out.println(this.model.faceDownTileCount() + " tuile caché (en stock)");
    }

    /*
    Display the list of the tile face up.
     */
    private void displayAllFaceUpTiles() {
        System.out.println("TABLE :");
        System.out.print("[");
        var list = new ArrayList<Tile>();
        for (Tile tiles : this.model.getAllfaceUpTiles()) {
            list.add(tiles);

        }
        Collections.sort(list, (Tile o1, Tile o2) -> o1.getValue() - o2.getValue());
        for (Tile sortedList : list) {
            System.out.printf("%2s ", sortedList.getValue());
        }
        System.out.println("]");
        System.out.println("");
        System.out.println("il y a " + this.model.faceUpTileCount() + " tuile visible ");

    }
}
