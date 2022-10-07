import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

public class TestClass {

    @Test
    public void fileReader() {
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
            String actual = res[1];
            String expected = "устройство ввода";

            assertEquals(expected, actual);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }
    }
}
