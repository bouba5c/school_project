package g55870.atl.othello.view_console;

import g55870.atl.othello.model.Model;
import g55870.atl.othello.model.Position;

import java.util.Scanner;

public class MyView implements View{

    private final Model model;

    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println(" ================================== ");
        System.out.println(" ||          Othello             || ");
        System.out.println(" ||    Auteur : Baldé Boubacar   || ");
        System.out.println(" ================================== ");
        System.out.println("""
                Listes des commandes :
                pour lancer le jeu : {start} {gameMode 1 local 2 ia} {niveau de difficulté true difficile false sinon} {size}
                pour mettre un pion: {put} {row} {column}
                pour avoir les règles du jeu : {help}
                pour quitter la partie:  {exit}
                pour abandonner : {surrender}
                pour annuler : {undo}
                pour refaire : {redo}
                pour voir la liste des commandes : {listCommands}
                PS : quand le jeu est terminé vous pouvez commencer une nouvelle partie
                """);

    }
    public void displayCommands()
    {
        System.out.println("""
                Listes des commandes :
                pour lancer le jeu : {start} {gameMode 1 local 2 ia} {difficulté: true difficile false sinon} {size}
                pour mettre un pion: {put} {row} {column}
                pour avoir les règles du jeu : {help}
                pour quitter la partie:  {exit}
                pour abandonner : {surrender}
                pour annuler : {undo}
                pour refaire : {redo}
                pour voir la liste des commandes : {listCommands}
                PS : quand le jeu est terminé vous pouvez commencer une nouvelle partie
                """);
    }

    @Override
    public void displayGame() {
        model.update();
        displayBoard();
        displayCurrentPlayer();
        displayScore();
    }
    @Override
    public void displayWinner() {
        int winner = this.model.getWinner();
        if(winner==-1)
        {
            System.out.println("Match nul");
        }
        else
        {
            System.out.println("Le vainqueur est le joueur : " + winner);
        }
    }

    @Override
    public void displayHelp() {
        System.out.println("""
                Othello est un jeu de plateau opposant deux joueurs.
                Il est composé :
                - d’un plateau de 8 cases sur 8, appelé othellier ;
                - de 64 pions bicolores possédant chacun une face blanche et une face noire.
                En début de partie, quatre pions sont placés au centre de l’othellier
                En début de partie 4 pièces sont posées au préalable sur l’othellier.
                À tour de rôle, chaque joueur pose un pion de sa couleur sur l’othellier.
                Noir commence toujours la partie.
                Un pion ne peut être posé que s’il entraine la capture d’au moins un pion de l’adversaireSi un joueur ne peut pas capturer de pions adverses, il est forcé de passer son tour. Si
                aucun des deux joueurs ne peut jouer, ou si l’othellier ne comporte plus de case vide, la
                partie est terminée.
                Le joueur ayant le plus grand nombre de pions de sa couleur sur l’othellier a gagné.""");
    }

    @Override
    public String askCommand() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Veuillez entrer une commande valide: ");
        return clavier.nextLine();
    }

    @Override
    public void displayScore() {
        System.out.println("score du joueur 1 : " +  model.getBlackScore() +" pions noir.");
        System.out.println("score du joueur 2 : " +  model.getWhiteScore() + " pions blanc. " );

    }

    @Override
    public void displayError(String s) {
        System.err.println(s);
    }

    /**
     * displays the current player
     */
    private void displayCurrentPlayer() {
        System.out.println("Tour joueur "+ this.model.getCurrentPlayerId() + " :");
    }
    /**
     * display the board
     */
    private void displayBoard() {
        if(model.getBoardSize()<=10)
        {
            for (int i = 0; i < model.getBoardSize(); i++) {
                System.out.print("  " +i);
            }
            System.out.println();
            for (int i = 0; i < model.getBoardSize(); i++) {
                System.out.print(i);

                for (int j = 0; j < model.getBoardSize(); j++) {


                    if (model.getPawn(new Position(i,j)) == null) {
                        System.out.print(" - ");
                    } else {
                        System.out.print(" "+model.getPawn(new Position(i,j)).toString() + " ");
                    }

                }
                System.out.println();
            }

        }
        else{
            for (int i = 0; i < model.getBoardSize(); i++) {
                System.out.print(" " +i + " ");
            }
            System.out.println();
            for (int i = 0; i < model.getBoardSize(); i++) {
                System.out.print(i);
                for (int j = 0; j < model.getBoardSize(); j++) {
                    if (model.getPawn(new Position(i,j)) == null) {
                        if(i<10)
                        {
                            System.out.print("  -");
                        }
                        else{
                            System.out.print(" - ");

                        }
                    }
                    else {
                        System.out.print("  "+model.getPawn(new Position(i,j)).toString());
                    }
                }
                System.out.println();
            }
        }
    }
}
