import java.util.Scanner;
import java.util.ArrayList;

public abstract class UserInterface {

    protected Scanner in;
    protected PrettyPrints prettyPrints;

    public UserInterface(Scanner scanner, PrettyPrints pp) {
        in = scanner;
        prettyPrints = pp;
    }

    protected int PrintChoices() {
        int choiceID = 1;
        for (String string : Choices()) {
            prettyPrints.Println(choiceID + ". " + string);
            choiceID++;
        }
        return Choices().length;
    }

    public void Enter() {
        int lastChoice = -1;
        while (true) {
            int amountOfChoices = Choices().length;
            lastChoice = Main();
            OnChoice(lastChoice);
            if (lastChoice == amountOfChoices + 1 || lastChoice == amountOfChoices + 1) {
                break;
            }
        }
    }

    protected void DisplayMenu(String title) {
        prettyPrints.SurroundPrintln(" " + title + " ", '=');
        System.out.println("");
        int amountOfChoices = PrintChoices();
        prettyPrints.Println((amountOfChoices + 1) + ". Exit");
        System.out.print("\nSelect an option: ");
    }

    public abstract int Main();
    public abstract String[] Choices();
    public abstract void OnChoice(int choice);
}
