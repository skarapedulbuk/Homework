package Homework01;

public class Homework02 {
    public static void main(String[] args) {
      //  Task1();
      //  Task2();
      //  Task3();
      //  Task4();
        Task5();
     //   System.out.println(Task6(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));

        int[] arr1 = {1,2,3,4,5};
        for (int o:arr1) {
            System.out.print(o + " ");
        }
        arr1 = Task7(arr1,3);
        for (int o:arr1) {
            System.out.print(o + " ");
        }

    }
    //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void Task1 () {
        int[] array = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
       for (int o :array) {
            switch (o) {
                case 0:
                    o = 1;
                    break;
                case 1:
                    o = 0;
                    break;
            }
            System.out.print(o + " ");
            }
        System.out.println();
    }
    //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static void Task2 () {
        int[] array = new int[8];
        for (int i = 0; i < 8; i++) {
            array[i] = i * 3;
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void Task3() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int o:array) {
            if (o < 6) {
                o = o * 2;
            }
            System.out.print(o + " ");
        }
        System.out.println();
    }
    //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
    // цикла(-ов) заполнить его диагональные элементы единицами;
    public static void Task4() {
        int matrixSize = 6;
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j || i == matrixSize-j-1) {
                    matrix[i][j] = 1;
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
    //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    public static void Task5() {
        int[] array = {1, 5, -3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int maxOfArray = array[0];
        int minOfArray = array[0];
        for (int o:array) {
            if (o > maxOfArray) {
                maxOfArray = o;
            } else if (o < minOfArray) {
                minOfArray = o;
            }
        }
        System.out.println("Max: " + maxOfArray);
        System.out.println("Min: " + minOfArray);
    }
    //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен
    // вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
    // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    // граница показана символами ||, эти символы в массив не входят.
    public static boolean Task6 (int[] array) {
        boolean isEqual = false;
        for (int i = 0; i < array.length; i++) {
            int sumBefore = 0;
            int sumAfter = 0;
            for (int j = 0;j <= i;j++) {
                sumBefore = sumBefore + array[j];
            }
            for (int j = i+1;j < array.length;j++) {
                sumAfter = sumAfter + array[j];
            }
           if (sumAfter == sumBefore) {
                System.out.println("Нашёл!");
                isEqual = true;
            }
        }
    return isEqual;
    }
    //7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
    // или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются
    // циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ]
    // при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
    // При каком n в какую сторону сдвиг можете выбирать сами.
    public static int[] Task7 (int[] array,int n) {
        if (n > 0) {
            System.out.println("Moving right...");
            for (int k = 0; k < n; k++) {
                System.out.println("Shift #" + (k+1));
                int temp = array[array.length-1];
                for (int i = array.length-1; i > 0; i-- ) {
                    array[i] = array[i-1];
                }
                array[0] = temp;

            }
        } else {
            System.out.println("Moving left...");
            for (int k = 0; k < -n; k++) {
                System.out.println("Shift #" + (k+1));
                int temp = array[0];
                for (int i = 0; i < array.length-1; i++) {
                    array[i] = array[i+1];
                }
                array[array.length-1] = temp;
            }
        }
        return array;
    }
}
