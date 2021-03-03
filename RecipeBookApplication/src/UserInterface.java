import java.util.ArrayList;

public abstract class UserInterface {

    public static int PrintChoices(String[] choices) {
        int choiceID = 1;
        for (String string : choices) {
            System.out.println(choiceID + ". " + string);
            choiceID++;
        }
        return choices.length;
    }

    public abstract String[] Choices();
    public abstract void MakeChoice(int choice);
    public void Before() {};
    public void After() {};
}
