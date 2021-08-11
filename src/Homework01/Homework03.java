package Homework01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Homework03 {
    // * Создать массив из слов
    //String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
    // "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
    // "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
    //При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
    //apple – загаданное
    //apricot - ответ игрока
    //ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    //Для сравнения двух слов посимвольно можно пользоваться:
    //String str = "apple";
    //char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
    //Играем до тех пор, пока игрок не отгадает слово.
    //Используем только маленькие буквы.
    public static void main(String[] args) {
        playTheGame();
        /*
        int [] digits = {1,2};
        Random random new Random();
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i];
        }  */

    }
    public static void playTheGame () {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        int target = random.nextInt(words.length);
        System.out.println("Target word: "+ words[target]);
        String userWord;
        char[] hiddenWord = new char[15];
        Arrays.fill(hiddenWord, (char) 35);
        do {
            System.out.print("Hidden word: ");
            print1DArray(hiddenWord);
            Scanner sc = new Scanner(System.in);
            System.out.println("Input your word: ");
            userWord = sc.nextLine();
            if (userWord.equals(words[target])) {
                System.out.println("Congratulations! U win!");
                break;
            }
            for (int i = 0; i < words[target].length(); i++) {
                if (userWord.length() > i) {
                    if (words[target].charAt(i) == userWord.charAt(i)) {
                        hiddenWord[i] = userWord.charAt(i);
                    }
                }

            }
        } while (!userWord.equals(words[target]));
        System.out.println("Good bye!");


    }

    public static void print1DArray(char [] arr) {
        for (char c : arr) {
            System.out.print(c);
        }
        System.out.println();
    }
}
