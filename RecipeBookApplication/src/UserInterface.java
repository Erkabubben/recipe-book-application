/**
 * UserInterface
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * The UserInterface provides methods for displaying menus and requesting the user
 * to select an option. By instantiating a class deriving from UserInterface and calling
 * its Enter method, the logic contained in the Main method of the derived class will be
 * triggered. Usually, the class will call DisplayMenu, which will display the menu options
 * defined in the Choices method.
 * Selecting a menu option will trigger the code contained in the corresponding index in
 * the OnChoice method's Switch statement.
 */
public abstract class UserInterface {

    protected ValidateInput validIn;
    protected PrettyPrints prettyPrints;

    /* Constructor */
    public UserInterface(ValidateInput vi, PrettyPrints pp) {
        validIn = vi;
        prettyPrints = pp;
    }

    /* The method to be called to enter the UserInterface.  */
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
    
    /* Prints the choices defined in the Choices method along with their ID numbers (1-based index) */
    protected int PrintChoices() {
        int choiceID = 1;
        for (String string : Choices()) {
            prettyPrints.Println(choiceID + ". " + string);
            choiceID++;
        }
        return Choices().length;
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
