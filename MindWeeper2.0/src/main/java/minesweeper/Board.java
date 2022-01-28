package minesweeper;

import java.util.Random;

/**
 * Die Klasse Board kontrolliert die Eingaben vom Benutzer.
 * Ist ausserdem auch für den Ablauf des Spiels zuständig.
 *
 * @author Julia Hutmacher
 * @version 0.8
 */

public class Board {
    Random rnd = new Random();

    public Object[][] field;

    /**
     * Konstruktor
     * @param y Anzahl Reihe
     * @param x Anzahl Spalte
     */

    public Board(int y, int x){
        this.field = new Object[y][x];
    }

    /**
     * Zeigt das Spielfeld mit alle Minen an
     */

    public void showEndBoard(){
        System.out.print("    ");

        for (int i = 1; i <= field.length; i++){
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int row = 0; row < field.length; row++){
            if (row + 1 < 10) {
                System.out.print(row + 1 + "   ");
            } else {
                System.out.print(row + 1 + "  ");
            }

            for (int col = 0; col < field[row].length; col++){
                if (field[row][col] instanceof Mine){
                    System.out.print("*  ");
                } else if (field[row][col] instanceof Flag){
                    System.out.print("F  ");
                } else if (field[row][col] instanceof Integer) {
                    System.out.print(field[row][col] + "  ");
                } else {
                    System.out.print("   ");
                }
            }

            System.out.println();
        }
        System.out.println();
    }

    /**
     * Zeigt das Spielfeld ohne Minen an. Wird als Spielersicht verwendet.
     */

    public void showBoard() {
        System.out.print("    ");

        for (int i = 1; i <= field.length; i++){
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int row = 0; row < field.length; row++){
            if (row + 1 < 10) {
                System.out.print(row + 1 + "   ");
            } else {
                System.out.print(row + 1 + "  ");
            }

            for (int col = 0; col < field[row].length; col++){
                if (field[row][col] instanceof Flag){
                    System.out.print("F  ");
                } else if (field[row][col] instanceof Integer) {
                    System.out.print(field[row][col] + "  ");
                } else {
                    System.out.print("   ");
                }
            }

            System.out.println();
        }
        System.out.println();
    }

    /**
     * Generiert die Minen.
     * @param numberOfMines Bestimmt wieviele Minen gesetzt werden sollte
     */

    public void generateMines(int numberOfMines){
        Mine mine = new Mine();

        for (int i = 0; i < numberOfMines; i++){
            int randomY = rnd.nextInt(field.length);
            int randomX = rnd.nextInt(field[0].length);

            if (field[randomY][randomX] != mine){
                field[randomY][randomX] = mine;
            } else {
                i --;
            }
        }
    }

    /**
     * Deckt alle Felder, inklusive den ausgewählten Feld, um den Feld auf.
     * Es werden nur die Felder aufgedeckt, welcher keine Minen in der Nähe haben
     * @param y ist die Reihe des Feldes
     * @param x ist die Spalte des Feldes
     * @param gameBoard ist das Feld, welches als Master dient
     */

    public void getCellAndNear(int y, int x, Board gameBoard){
        Cell cell = new Cell();
        int inputY = y - 1;
        int inputX = x - 1;


        if (gameBoard.field[inputY][inputX] instanceof Mine){
        }
        else {
            this.field[inputY][inputX] = cell.isNearMines(y, x, gameBoard);


            int intToTest = (int)field[inputY][inputX];

            if (intToTest == 0) {


                for (int row = 0; row < this.field.length; row++){
                    for (int col = 0; col < this.field[row].length; col++){
                        if (row == inputY || (inputY !=0 && row == inputY - 1) || row == inputY + 1){
                            if (col == inputX || (inputX !=0 && col == inputX - 1) || col == inputX + 1){
                                if (!(gameBoard.field[row][col] instanceof Mine || this.field[row][col] instanceof Flag || this.field[row][col] instanceof Integer)){
                                    if (!(row == inputY && col == inputX)){
                                        int paraRowY = row + 1;
                                        int paraRowX = col + 1;
                                        this.field[row][col] = cell.isNearMines(paraRowY, paraRowX, gameBoard);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    /**
     * Setzt eine Flage
     * @param y ist die Reihe des Feldes
     * @param x ist die Spalte des Feldes
     */

    public void setFlag(int y, int x){
        Flag flag = new Flag();
        field[y - 1][x - 1] = flag;
    }

}
