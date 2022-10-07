import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

public class TestGameplay {

    @Test
    public void gameplay() {
        try {
            File myFile = new File("D:/Projects/java_projects/word_guess/src/words.txt");
            Scanner myReader = new Scanner(myFile);
            List<String> words = new ArrayList<>();
            int line = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data);
                line++;
            }

            myReader.close();
            String certainLine = words.get(1);
            String separator = ";";
            String[] res = certainLine.split(separator);

            String wordToGuess = res[0];
            char[] wordLetters = wordToGuess.toCharArray();
            int numOfLettersInWord = wordToGuess.length() - 1;
            int guessedNumOfLetters = 0;
            StringBuilder guesses = new StringBuilder();

            while (true) {
                for (char letter : wordLetters) {
                    String letterToStr = letter + "";
                    if (guesses.toString().contains(letterToStr)) {
                        System.out.println(letterToStr);
                    } else {
                        System.out.println("_");
                    }
                }
                if (guessedNumOfLetters == numOfLettersInWord) {
                    System.out.println("Вы угадали!");
                    return;
                }
                String guess = "клав";
                System.out.print("Введите букву: " + guess);
                if (guess.length() != 1) {
                    if (guess.equals(wordToGuess)){
                        System.out.println("Вы угадали!");
                        return;
                    }
                    else {
                        System.out.println("Вы не угадали. Вводите буквы дальше...");
                    }
                } else {
                    if (guesses.toString().contains(guess)){
                        System.out.println("Вы уже вводили такую букву");
                    } else {
                        guesses.append(guess);
                        System.out.print(guess.length());
                        if (!wordToGuess.contains(guess)) {
                            System.out.println("Неверная буква");
                        } else {
                            int count = wordToGuess.length() - wordToGuess.replaceAll(guess, "").length();
                            guessedNumOfLetters += count;
                            System.out.println("Верно!");
                        }
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }
    }
}
