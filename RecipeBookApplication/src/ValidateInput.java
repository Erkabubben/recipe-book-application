/**
 * ValidateInput
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.Scanner;
import java.util.InputMismatchException;
/**
* ValidateInput provides methods for safely requesting input of different types from the user.
* It uses try-catch statements to handle exceptions and avoid crashes - when receiving an invalid
* input, it displays an error message and re-queries the user.
*/
public class ValidateInput {

    private Scanner in;

    /* Constructor */
    public ValidateInput(Scanner scanner) {
        in = scanner;
    }

    /* Constructor */
    public ValidateInput() {
        in = new Scanner(System.in);
    }

    /* Requests the user to input an integer within the given range. */
    public int nextIntInRange(String prefix, int start, int end) {
        do {
            in = new Scanner(System.in);
            if (prefix != null) {
                System.out.print(prefix);
            }
            try {
                int number = in.nextInt();
                if (number < start || number > end) {
                    throw new IllegalArgumentException();
                }
                return number;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Input must be within range " + start + " to " + end + ".");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.");
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input.");
            }
        } while (true);
    }

    public int nextIntInRange(int start, int end) {
        return nextIntInRange(null, start, end);
    }

    /* Requests the user to input a line of text. */
    public String nextLine(String prefix) {
        do {
            in = new Scanner(System.in);
            if (prefix != null) {
                System.out.print(prefix);
            }
            try {
                return in.nextLine();
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input.");
            }
        } while (true);
    }

    public String nextLine() {
        return nextLine(null);
    }

    /* Requests the user to input a text token. */
    public String next(String prefix) {
        do {
            in = new Scanner(System.in);
            if (prefix != null) {
                System.out.print(prefix);
            }
            try {
                return in.next();
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input.");
            }
        } while (true);
    }

    public String next() {
        return next(null);
    }

    /* Requests the user to make a binary decision of 'yes' or 'no' and returns a boolean based on the choice. */
    public boolean YesOrNo(String prefix) {
        do {
            in = new Scanner(System.in);
            if (prefix != null) {
                System.out.print(prefix);
            }
            try {
                String s = in.next();
                if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("y")) {
                    return true;
                } else if (s.toLowerCase().equals("no") || s.toLowerCase().equals("n")) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input.");
            }
        } while (true);
    }

    /* Requests the user to input a double within the given range - allows for use of either commas or
       periods as decimal sign. */
    public Double nextDoubleInRange(String prefix, double start, double end) {
        do {
            in = new Scanner(System.in);
            if (prefix != null) {
                System.out.print(prefix);
            }
            try {
                String numberString = in.nextLine();
                numberString = numberString.replace(',', '.');
                double number = Double.parseDouble(numberString);
                if (number < start || number > end) {
                    throw new IllegalArgumentException();
                }
                return number;
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Input must be within range " + start + " to " + end + ".");
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Invalid input.");
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input.");
            }
        } while (true);
    }

    public Double nextDoubleInRange(double start, double end) {
        return nextDoubleInRange(null, start, end);
    }
}
