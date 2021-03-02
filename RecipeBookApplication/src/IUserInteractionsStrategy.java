import java.util.ArrayList;

public interface IUserInteractionsStrategy {

    public static void PrintChoices(String[] choices) {
        int choiceID = 1;
        for (String string : choices) {
            System.out.println(choiceID + ". " + string);
            choiceID++;
        }
    }

    public String[] Choices();
    public void MakeChoice(int choice);
}
