import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

    private final List<String> words = new ArrayList<>();
    private String wordToGuess = "";
    private int lines = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public Gameplay() {
    }

    public void start() {
        fileReader();
        chooseWord();
        play();
    }

    private void play() {
        char[] wordLetters = wordToGuess.toCharArray();
        int numOfLettersInWord = wordToGuess.length();
        int guessedNumOfLetters = 0;
        StringBuilder guesses = new StringBuilder();

        while (true) {
            for (char letter : wordLetters) {
                String letterToStr = letter + "";
                if (guesses.toString().contains(letterToStr)) {
                    System.out.print(letterToStr + " ");
                } else {
                    System.out.print("_ ");
                }
            }

            if (guessedNumOfLetters == numOfLettersInWord) {
                System.out.println("\nВы угадали!");
                return;
            }

            System.out.print("\nВведите букву: ");
            String guess = scanner.nextLine();
            if (guess.length() != 1) {
                if (guess.length() == 0) {
                    System.out.println("Пожалуйста, введите букву");
                } else {
                    if (guess.equals(wordToGuess)) {
                        System.out.println("\nВы угадали!");
                        return;
                    } else {
                        System.out.println("\nВы не угадали. Вводите буквы дальше...");
                    }
                }
            } else {
                switch (guess) {
                    case "0" -> {
                        System.out.println("Жаль, не угадали. Загаданное слово: '" + wordToGuess + "'");
                        return;
                    }
                    case "8" -> {
                        String leftLetters = wordToGuess.replaceAll(guess, "");
                        System.out.println("Попробуйте букву '" + leftLetters.charAt(0) + "'");
                        continue;
                    }
                }
                if (guesses.toString().contains(guess)) {
                    System.out.println("Вы уже вводили такую букву");
                } else {
                    guesses.append(guess);
                    if (!wordToGuess.contains(guess)) {
                        System.out.println("Буквы в слове нет");
                    } else {
                        int count = wordToGuess.length() - wordToGuess.replaceAll(guess, "").length();
                        guessedNumOfLetters += count;
                        System.out.println("Верно!");
                    }
                }
            }
        }
    }

    private void chooseWord() {
        Random r = new Random();
        int low = 0;
        int high = lines;
        int result = r.nextInt(high - low) + low;
        String certainLine = words.get(result);
        String separator = ";";
        String[] res = certainLine.split(separator);
        System.out.println(res[1]);
        this.wordToGuess = res[0];

    }

    private void fileReader() {
        try {
            File myFile = new File("D:/Projects/java_projects/word_guess/src/words.txt");
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data);
                lines++;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }
    }
}
