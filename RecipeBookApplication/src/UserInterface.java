public abstract class UserInterface {

    protected ValidateInput validIn;
    protected PrettyPrints prettyPrints;

    public UserInterface(ValidateInput vi, PrettyPrints pp) {
        validIn = vi;
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
            if (lastChoice == amountOfChoices + 1) {
                break;
            }
        }
    }

    protected void DisplayMenu(String title) {
        prettyPrints.SurroundPrintln(" " + title + " ", '=');
        System.out.println("");
        int amountOfChoices = PrintChoices();
        prettyPrints.Println((amountOfChoices + 1) + ". Exit");
        prettyPrints.Println("");
    }

    protected int DisplayInputRequest() {
        return validIn.nextIntInRange("Select an option: ", 1, amountOfChoices());
    }

    protected int amountOfChoices() {
        return Choices().length + 1;
    }

    protected abstract int Main();
    protected abstract String[] Choices();
    protected abstract void OnChoice(int choice);
}
