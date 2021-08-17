package Homework01;
/*
  1.  Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать.
  2.  Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов. Необходимо учесть, что такая проверка должна быть универсальной для любого размера поля, 1х1, 5х5, 100х100 и так далее.
  3.  * Попробовать переписать логику проверки победы, чтобы она работала для поля 3х3 и 5х5. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
  4.  *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.

*/

import java.util.Random;
import java.util.Scanner;

public class Homework04 {
    public static void main(String[] args) {
        start();
    }
    static void start() {
        Scanner scanner =  new Scanner(System.in);
        int fieldSize;
        do {
            System.out.println("Please enter field size");
            fieldSize = scanner.nextInt();
        } while (fieldSize <= 0);
        char[][] field = createField(fieldSize);

        do {
            doPlayerMove(field);
            drawField(field);
            if (isWin(field, 'X')) {
                System.out.println("Congrats!!! You are winner :)");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw.");
                break;
            }

         //   doAIMove(field);
         //   drawField(field);
         //   if (isWin(field, 'O')) {
         //       System.out.println("Sorry!!! You are looser :(");
         //       break;
         //   }
            if (isDraw(field)) {
                System.out.println("This is draw.");
                break;
            }
        } while (true);
    }


    static void doPlayerMove(char[][] field) {
        int h, v;
        do {
            h = getCoordinate(field.length - 1, 'h');
            v = getCoordinate(field.length - 1, 'v');
        } while (isNotFreeCell(field, h, v));

        field[h][v] = 'X';
    }
    static int getCoordinate(int lastIndex, char coordName) {
        Scanner scanner = new Scanner(System.in);
        int coord;
        do {
            System.out.printf("Please enter %s-coordinate ... [1-3]%n", coordName);
            coord = scanner.nextInt() - 1;
        } while (coord < 0 || coord > lastIndex);
        return coord;
    }

    static void drawField(char[][] field) {
        for (char[] chars : field) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(chars[j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }
    static boolean isDraw(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (isFreeCell(field, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // методы подлежащие корректировке:
    static char[][] createField(int size) {
        char[][] array = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = '-';
            }

        }
        return array;
    }

    //

    static boolean isWin(char[][] field, char symbol) {
        // horizontal
/*
        for (int i = 0; i < field.length; i++) {
            System.out.println("checking raw " + i);
            for (int j = 0; j < field.length; j++) {
                System.out.println("checking column " + j);

            }

        }
        if (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol) {
            return true;
        }
        if (field[1][0] == symbol && field[1][1] == symbol && field[1][2] == symbol) {
            return true;
        }
        if (field[2][0] == symbol && field[2][1] == symbol && field[2][2] == symbol) {
            return true;
        }

        // vertical
        /*
        if (field[0][0] == symbol && field[1][0] == symbol && field[2][0] == symbol) {
            return true;
        }
        if (field[0][1] == symbol && field[1][1] == symbol && field[2][1] == symbol) {
            return true;
        }
        if (field[0][2] == symbol && field[1][2] == symbol && field[2][2] == symbol) {
            return true;
        }
*//*
        boolean isColumn = false;
        int i = 0;
            do {
                for (int j=0; j< field.length;j++) {

                    System.out.println("check " + i + ", " + j);
                if (field[i][j] != symbol) {
                    isColumn = false;
                } else {
                    isColumn = true;
                }

                }
                i++;
            } while (isColumn && i<field.length);

        System.out.println(isColumn);
        if (isColumn) {return isColumn;}
*/
        // diagonals
        boolean isDiag = false;
        boolean isDiag2 = false;
        int i = 0;
        do {
            if (field[i][i] != symbol) {
                isDiag = false;
            } else {
                isDiag = true;
            }
            System.out.print(isDiag + " ");
            i++;
        } while (isDiag && i < field.length);
        System.out.println(isDiag);
        if (isDiag) {return isDiag;}

        i = 0;
        do {
            if (field[i][field.length-i-1] != symbol) {
                isDiag2 = false;
            } else {
                isDiag2 = true;
            }
            System.out.print(isDiag2 + " ");
            i++;
        } while (isDiag2 && i < field.length);
        System.out.println(isDiag2);
        if (isDiag2) {return isDiag2;}

   /*     if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) {
            return true;
        }
        if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) {
            return true;
        }
*/
        return false;
    }
    static void doAIMove(char[][] field) {
        int h, v;
        Random random = new Random();

        do {
            h = random.nextInt(field.length);
            v = random.nextInt(field.length);
        } while (isNotFreeCell(field, h, v));

        field[h][v] = 'O';
    }

    static boolean isFreeCell(char[][] field, int h, int v) {
        return field[h][v] == '-';
    }

    /**
     * field[0][0] = 'X'
     * isFreeCell(field, 0, 0) -> FALSE
     * isNotFreeCell(field, 0, 0) -> !FALSE -> TRUE
     */
    static boolean isNotFreeCell(char[][] field, int h, int v) {
        return !isFreeCell(field, h, v);
    }

    /**
     * For FUTURE
     * static void doMove(char[][] field, Supplier<Integer> hFunc, Supplier<Integer> vFunc) {
     * int h, v;
     * do {
     *  h = hFunc.get();
     *  v = vFunc.get();
     * } while (field[h][v] != '-');
     * }
     */

}
