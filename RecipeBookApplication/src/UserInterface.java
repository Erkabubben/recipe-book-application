import java.util.Scanner;
import java.util.ArrayList;

public abstract class UserInterface {

    protected Scanner in;
    protected PrettyPrints prettyPrints;

    public UserInterface(Scanner scanner, PrettyPrints pp) {
        in = scanner;
        prettyPrints = pp;
    }

    public int PrintChoices() {
        int choiceID = 1;
        for (String string : Choices()) {
            System.out.println(choiceID + ". " + string);
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

    public abstract int Main();
    public abstract String[] Choices();
    public abstract void OnChoice(int choice);
}
