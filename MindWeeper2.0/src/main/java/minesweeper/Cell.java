package minesweeper;

public class Cell {

    public int isNearMines(int inputY , int inputX, Board fieldToTest) {
        int count = 0;
        int fieldY = inputY - 1;
        int fieldX = inputX - 1;

        for (int row = 0; row < fieldToTest.field.length; row++){
            for (int col = 0; col < fieldToTest.field[row].length; col++){
                if (row == fieldY || (fieldY !=0 && row == fieldY - 1) || row == fieldY + 1){
                    if (col == fieldX || (fieldX !=0 && col == fieldX - 1) || col == fieldX + 1){
                        if (fieldToTest.field[row][col] instanceof Mine){
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

}
