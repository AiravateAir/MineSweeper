package minesweeper;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );

        boolean correctInput = false;
        boolean gameEnd = false;
        int fieldInputY = 5;
        int fieldInputX = 5;

        System.out.println("How many rows do you want to have?");
        while (!correctInput) {
            if (scanner.hasNextInt()) {
                fieldInputY = scanner.nextInt();
                correctInput = true;
            }
        }

        correctInput = false;
        System.out.println("How many columns do you want to have?");
        while (!correctInput) {
            if (scanner.hasNextInt()) {
                fieldInputX = scanner.nextInt();
                correctInput = true;
            }
        }

        Board gameBoard = new Board(fieldInputY, fieldInputX);
        Board myBoard = new Board(fieldInputY, fieldInputX);

        gameBoard.generateMines(10);

        while (!gameEnd) {
            correctInput = false;
            while (!correctInput) {
                System.out.println("To set a flag use F");
                System.out.println("To click a cell use C");
                System.out.println("Use the format 'Action Row Col' to play:");
                String str = scanner.nextLine();
                String[] splitStr = str.split("\\s+");
                if (splitStr.length == 3) {
                    String action = splitStr[0];
                    String y = splitStr[1];
                    String x = splitStr[2];
                    correctInput = true;
                    if (Objects.equals(action, "F")) {
                        myBoard.setFlag(parseInt(y), parseInt(x));
                    }
                    else if (Objects.equals(action, "C")) {
                        myBoard.getCellAndNear(parseInt(y), parseInt(x), gameBoard);
                    }
                    else {
                        correctInput = false;
                    }
                }
                else {
                    System.out.println("This input wasn't correct");
                }

                myBoard.showBoard();
            }
        }

    }
}
