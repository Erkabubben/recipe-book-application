import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class FileOperations {

    public void SaveText(String path, String text) {
        try {
            File outFile = new File(path);
            PrintWriter printer = new PrintWriter(outFile);
            printer.print(text);
            printer.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public String LoadFromString() {
        StringBuilder text = new StringBuilder();

        try {
            File file = new File("");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                text.append(str + "\n");
            }
            scan.close();
        } catch (IOException e) { e.printStackTrace(); }

        return "";
    }
}
