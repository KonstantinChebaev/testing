import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static void readFile (){
        String line = "";
        File file = new File("res/lng.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line= br.readLine();
            while (line != null) {
                GroupFinder.addNums(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
