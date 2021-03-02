import java.util.ArrayList;

public interface IUserInteractionsStrategy {

    public static int PrintChoices(String[] choices) {
        int choiceID = 1;
        for (String string : choices) {
            System.out.println(choiceID + ". " + string);
            choiceID++;
        }
        return choices.length;
    }

    public static String SurroundString(String content, int length, char c) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(c);
        }
        int start = (length / 2) - (content.length() / 2);
        text.replace(start, start + content.length(), content);
        return text.toString();
    }

    public String[] Choices();
    public void MakeChoice(int choice);
}
