import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
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
            System.out.println(res[1]);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }
    }
}