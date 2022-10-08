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
        menu();
        fileReader();
        chooseWord();
        play();
    }

    private void menu() {
        System.out.print("Введите имя игрока: ");
        String gamerName = scanner.nextLine().replaceAll(" ", "").replaceAll("\t", "");
        if (gamerName.equals(""))
            gamerName = "No name";
        System.out.println("\n====================Добро пожаловать в 'Поле чудес' !====================");
        System.out.println(" - Вам даётся описание загаданного слова");
        System.out.println(" - Попробуйте его угадать, вводя по одной букве");
        System.out.println(" - Если вы догадались, то можете ввести слово полностью");
        System.out.println(" - Строка из нескольких символов будет считаться как догадка полного слова");
        System.out.println(" - Если вы хотите получить подсказку, введите '8'");
        System.out.println(" - Если вы хотите сдаться, введите '0'");
        System.out.println("===========================================================================\n");
        System.out.println("Удачи, " + gamerName + " !\n");

    }

    private void play() {
        int guessedNumOfLetters = 0;
        StringBuilder guesses = new StringBuilder();

        while (true) {
            for (char letter : wordToGuess.toCharArray()) {
                if (guesses.toString().contains(letter + "")) {
                    System.out.print(letter + " ");
                } else {
                    System.out.print("_ ");
                }
            }

            if (guessedNumOfLetters == wordToGuess.length()) {
                System.out.println("\nВы угадали! Загаданное слово: '" + wordToGuess + "'");
                return;
            }

            System.out.print("\nВведите букву: ");
            String guess = scanner.nextLine().toLowerCase().replaceAll(" ", "").replaceAll("\t", "");

            if (guess.length() != 1) {
                if (guess.length() == 0) {
                    System.out.println("Пожалуйста, введите букву");
                } else {
                    if (guess.equals(wordToGuess)) {
                        System.out.println("\nВы угадали! Загаданное слово: '" + wordToGuess + "'");
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
                    case "\t" -> {
                        System.out.println("Табуляцию не надо. Вводите буквы, пожалуйста");
                        continue;
                    }
                    case "8" -> {
                        String leftLetters = wordToGuess;
                        for (char letter : guesses.toString().toCharArray()) {
                            leftLetters = leftLetters.replaceAll(letter + "", "");
                        }
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
        int result = r.nextInt(lines);
        String[] res = words.get(result).split(";");
        System.out.println(res[1]);
        this.wordToGuess = res[0];
    }

    private void fileReader() {
        try {
            File myFile = new File("src/words.txt");
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
