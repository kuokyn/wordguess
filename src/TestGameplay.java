import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

public class TestGameplay {

    @Test
    public void thisLetterIsInWord() {
        String actual = gamePLayForOneLoop("к", "");
        String expected = "Верно!";
        assertEquals(expected, actual);
    }

    @Test
    public void thisLetterIsNotInWord() {
        String actual = gamePLayForOneLoop("ж", "");
        String expected = "Неверная буква";
        assertEquals(expected, actual);
    }

    @Test
    public void thisLetterIsEmpty() {
        String actual = gamePLayForOneLoop("", "");
        String expected = "Пожалуйста, введите букву";
        assertEquals(expected, actual);
    }

    @Test
    public void thisLetterWasAlreadyGuessed() {
        String actual = gamePLayForOneLoop("к", "к");
        String expected = "Вы уже вводили такую букву";
        assertEquals(expected, actual);
    }

    @Test
    public void guessWholeWordRight() {
        String actual = gamePLayForOneLoop("клавиатура", "");
        String expected = "Вы угадали!";
        assertEquals(expected, actual);
    }

    @Test
    public void guessWholeWordNotRight() {
        String actual = gamePLayForOneLoop("микрофон", "");
        String expected = "Вы не угадали. Вводите буквы дальше...";
        assertEquals(expected, actual);
    }

    @Test
    public void giveUp() {
        String actual = gamePLayForOneLoop("0", "");
        String expected = "Жаль, не угадали. Загаданное слово: клавиатура";
        assertEquals(expected, actual);
    }

    @Test
    public void hint() {
        String actual = gamePLayForOneLoop("8", "");
        String expected = "Попробуйте букву к";
        assertEquals(expected, actual);
    }

    @Test
    public void tabCheck() {
        String actual = gamePLayForOneLoop("\t", "");
        String expected = "Табуляцию не надо. Вводите буквы, пожалуйста";
        assertEquals(expected, actual);
    }

    @Test
    public void fileReader() {
        List<String> words = new ArrayList<>();
        String actual = "";
        try {
            File myFile = new File("D:/Projects/java_projects/word_guess/src/words.txt");
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                actual += myReader.nextLine();
                words.add(actual);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }

        String expected = "клавиатура;устройство ввода" +
                "камердинер;домашний слуга" +
                "очковтирательство;обман, введение в заблуждение" +
                "пропоганда;манипулирование общественным сознанием через распространение заведо ложных сведений, клеветы и слухов" +
                "мазохизм;умышленное нанесение вреда самому себе" +
                "оникс;минерал, разновидность кварца, в котором примеси создают плоскопараллельные окрашенные слои";
        assertEquals(expected,actual);
    }

    private String gamePLayForOneLoop(String guess, String guessed) {
        String wordToGuess = "клавиатура";
        StringBuilder guesses = new StringBuilder();
        guesses.append(guessed);
        while (true) {
            if (guess.length() != 1) {
                if (guess.length() == 0) {
                    return ("Пожалуйста, введите букву");
                } else {
                    if (guess.equals(wordToGuess)) {
                        return ("Вы угадали!");
                    } else {
                        return ("Вы не угадали. Вводите буквы дальше...");
                    }
                }
            } else {
                switch (guess) {
                    case "0":
                        return ("Жаль, не угадали. Загаданное слово: клавиатура");
                    case "8":
                        return ("Попробуйте букву 'к'");
                    case "\t": {
                        return ("Табуляцию не надо. Вводите буквы, пожалуйста");
                    }
                }
                if (guesses.toString().contains(guess)) {
                    return ("Вы уже вводили такую букву");
                } else {
                    guesses.append(guess);
                    if (!wordToGuess.contains(guess)) {
                        return ("Неверная буква");
                    } else {
                        return ("Верно!");
                    }
                }
            }
        }
    }

}
