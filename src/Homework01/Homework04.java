package Homework01;
/*
  TODO 1.  Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать.
  FIXME 2.  Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов. Необходимо учесть, что такая проверка должна быть универсальной для любого размера поля, 1х1, 5х5, 100х100 и так далее.
  FIXME 3.  * Попробовать переписать логику проверки победы, чтобы она работала для поля 3х3 и 5х5. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
  TODO 4.  *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
*/

import java.util.Arrays;
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
        int[] lastPlayerMove;
        int[] lastAIMove;
        do {
            lastPlayerMove = doPlayerMove(field);
            drawField(field);
   //         System.out.println("Your last move: " + (lastMove[0]+1) + " " + (lastMove[1]+1));
            if (isWin(field, lastPlayerMove,'X')) {
                System.out.println("Congrats!!! You are winner :)");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw.");
                break;
            }
            lastAIMove = doAIMove(field, lastPlayerMove);
            drawField(field);
            if (isWin(field, lastAIMove,'O')) {
                System.out.println("Sorry!!! You are looser :(");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw.");
                break;
            }
        } while (true);
    }

    static char[][] createField(int size) {
        char[][] array = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = '-';
            }

        }
        drawField(array);
        return array;
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

    static int getCoordinate(int lastIndex, char coordName) {
        Scanner scanner = new Scanner(System.in);
        int coord;
        do {
            System.out.printf("Please enter %s-coordinate ... [1-3]%n", coordName);
            coord = scanner.nextInt() - 1;
        } while (coord < 0 || coord > lastIndex);
        return coord;
    }
    static int[] doPlayerMove(char[][] field) {
        int h, v;
        do {
            h = getCoordinate(field.length - 1, 'h');
            v = getCoordinate(field.length - 1, 'v');
        } while (isNotFreeCell(field, h, v));

        field[h][v] = 'X';
        return new int[] {h, v};
    }
    static int[] doRandomAIMove (char[][] field) {
        int h, v;
        Random random = new Random();
        System.out.println("Absolutely random turn!");
        do {
            h = random.nextInt(field.length);
            v = random.nextInt(field.length);
        } while (isNotFreeCell(field, h, v));
        return new int[]{h, v};
    }

    static boolean isFreeCell(char[][] field, int h, int v) {
        return field[h][v] == '-';
    }
    static boolean isNotFreeCell(char[][] field, int h, int v) {
        return !isFreeCell(field, h, v);
    }
    static boolean isXOCell(char[][] field, int h, int v,char symbol) {
        return field[h][v] == symbol;
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
    static boolean isWin(char[][]field, int[] turn, char symbol) {
        return countV(field, turn, symbol) == field.length
                || countH(field, turn, symbol) == field.length
                || countDiag1(field, turn, symbol) == field.length
                || countDiag2(field, turn, symbol) == field.length;
    }

    static int countDiag1 (char[][] field, int[] turn, char symbol) {
        int counter=0;
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == symbol) {
                counter++;
            }
        }
        return counter;
    }
    static int countDiag2 (char[][] field, int[] turn, char symbol) {
        int counter=0;
     //   boolean isDiag=false;
            for (int i = 0; i < field.length; i++) {
                if (field[i][field.length-i-1] == symbol) {
              //      if (turn[0] == i && turn[1] == field.length - i -1) {
                 //       isDiag = true;
                //    }
                    counter++;
                }
            }

   //     if (isDiag) return counter;
        return counter;
    }
    static int countH(char[][] field, int[] turn, char symbol) {
        int colCounter=0;
        for (int i = 0; i < field.length; i++) {
            if (isXOCell(field, i, turn[1], symbol)) {
                colCounter++;
            }
        }
    //    System.out.printf("Number of X-cells at col %s: %s %n", turn[1], colCounter);
        return colCounter;
    }
    static int countV(char[][] field, int[] turn, char symbol) {
        int rowCounter=0;
        for (int i = 0; i < field.length; i++) {
            if (isXOCell(field,turn[0], i, symbol)) {
                rowCounter++;
            }
        }
    //    System.out.printf("Number of X-cells at row %s: %s %n", turn[0], rowCounter);
        return rowCounter;
    }

    static int[] doAIMove(char[][] field, int[] turn) {
        int[] move = new int[2];
        System.out.println("Твой ход был "+ (turn[0]+1) + " " + (turn[1]+1));
        //  System.out.printf("По линии %s:%n X:%s%n O:%s%n -:%s%n",turn[0]+1,countRow(field, turn,'X'),countRow(field, turn,'O'), countRow(field, turn,'-'));
        int[] maxX = new int[4];
        if (countV(field, turn, 'O') == 0) {
            maxX[0]= countV(field, turn,'X');
        }
        if (countH(field, turn, 'O') == 0) {
            maxX[1]= countH(field, turn,'X');
        }
        if (countDiag1(field, turn, 'O') == 0) {
            maxX[2]=countDiag1(field, turn,'X');
        }
        if (countDiag2(field, turn, 'O') == 0) {
            maxX[3]=countDiag2(field, turn,'X');
        }
        int maxDanger = Arrays.stream(maxX).max().getAsInt();
        //System.out.println(maxDanger);
        //System.out.println(countDiag2(field, turn,'X'));
        //System.out.println(countDiag2(field, turn,'-'));
        //System.out.println(countDiag2(field, turn,'O'));
        if (countV(field, turn,'X') == maxDanger &&
                countV(field, turn, '-') > 0 &&
                countV(field, turn, 'O') == 0) {
            System.out.println("Блокируем горизонтальную линию "+ (turn[0]+1));
            move[0] = turn[0];
            Random random = new Random();
            do {
                move[1] = random.nextInt(field.length);
            } while (isNotFreeCell(field, move[0], move[1]));
        } else if (countH(field, turn, 'X') == maxDanger &&
                countH(field, turn, '-') > 0 &&
                countH(field, turn, 'O') == 0) {
            System.out.println("Блокируем вертикальную линию " + (turn[1] + 1));
            move[1] = turn[1];
            Random random = new Random();
            do {
                move[0] = random.nextInt(field.length);
            } while (isNotFreeCell(field, move[0], move[1]));
        } else if (countDiag1(field, turn, 'X') == maxDanger &&
                countDiag1(field, turn, '-') > 0 &&
                countDiag1(field, turn, 'O') == 0) {
            System.out.println("Блокируем диагональную линию 1");
            Random random = new Random();
            do {
                move[1] = move[0] = random.nextInt(field.length);
                //  move[1] = field.length - random.nextInt(field.length) - 1;
            } while (isNotFreeCell(field, move[0], move[1]));
        } else if (countDiag2(field, turn, 'X') == maxDanger &&
                countDiag2(field, turn, '-') > 0 &&
                countDiag2(field, turn, 'O') == 0) {
            System.out.println("Блокируем диагональную линию 2");
            Random random = new Random();
            do {
                move[0] = random.nextInt(field.length);
                move[1] = field.length - move[0] - 1;
            } while (isNotFreeCell(field, move[0], move[1]));
        } else {
            move = doRandomAIMove(field);
        }
        System.out.printf("Мой ход %s %s %n",move[0]+1,move[1]+1);
        field[move[0]][move[1]] = 'O';
        return new int[] {move[0],move[1]};
    }



    /**
     * field[0][0] = 'X'
     * isFreeCell(field, 0, 0) -> FALSE
     * isNotFreeCell(field, 0, 0) -> !FALSE -> TRUE
     */

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
