import java.util.Scanner;
import java.util.InputMismatchException;

public class ValidateInput {

    private Scanner in;

    public ValidateInput(Scanner scanner) {
        in = scanner;
    }

    public ValidateInput() {
        in = new Scanner(System.in);
    }

    public int nextIntInRange(String prefix, int start, int end) {
        System.out.print(prefix);
        return nextIntInRange(start, end);
    }

    public int nextIntInRange(int start, int end) {
        do {
            try {
                int number = in.nextInt();
                if (number < start || number > end) {
                    throw new IllegalArgumentException();
                }
                return number;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Input must be within range " + start + " to " + end + ".");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input." + start + " to " + end + ".");
            }
        } while (true);
    }

    public String nextLine() {
        return in.nextLine();
    }

    public String next() {
        return in.next();
    }

    public Double nextDouble() {
        return in.nextDouble();
    }
}
