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
