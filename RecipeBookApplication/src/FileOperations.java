/**
 * FileOperations
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
/**
 * Used for simplifying the process of saving and loading text data.
 */
public class FileOperations {

    /* Saves a string to a text file at a given path. */
    public void SaveTextToFile(String path, String text) {
        try {
            File outFile = new File(path);
            PrintWriter printer = new PrintWriter(outFile);
            printer.print(text);
            printer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /* Loads a text file at a given path and returns its contents as a string.
       Returns an empty string if file doesn't exist. */
    public String LoadToString(String path) {
        StringBuilder text = new StringBuilder();

        try {
            File file = new File(path);
            if (!file.exists()) {
                return "";
            }
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                text.append(str + "\n");
            }
            scan.close();
        } catch (IOException e) { e.printStackTrace(); }

        return text.toString();
    }
}
