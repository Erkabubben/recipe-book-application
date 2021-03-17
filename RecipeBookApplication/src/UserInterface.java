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
            lastChoice = Menu();
            OnChoice(lastChoice);
            if (lastChoice == amountOfChoices + 1) {
                break;
            }
        }
    }

    /* By default displays the main menu of the UI - can also be overridden for more flexibility. */
    protected int Menu() {
        DisplayMenuWithTitleAndOptions(Title());
        return validIn.nextIntInRange("Select an option: ", 1, amountOfChoices());
    }
    
    /* Takes a Choices array and prints its set of options, along with their ID numbers (1-based index).
       Returns the size of the Choices array. */
    protected int PrintChoicesArray(String[] choices) {
        int choiceID = 1;
        for (String string : choices) {
            prettyPrints.Println(choiceID + ". " + string);
            choiceID++;
        }
        return choices.length;
    }

    /* Displays the title defined in Title() and the contents of the Choices array returned by Choices(),
       along with the default Exit option */
    protected void DisplayMenuWithTitleAndOptions(String title) {
        prettyPrints.SurroundPrintln(" " + title + " ", '=');
        System.out.println("");
        int amountOfChoices = PrintChoicesArray(Choices());
        prettyPrints.Println((amountOfChoices + 1) + ". Exit");
        prettyPrints.Println("");
    }

    /* Returns the size of the Choices array */
    protected int amountOfChoices() {
        return Choices().length + 1;
    }

    /* Returns the title of the UI class */
    protected abstract String Title();

    /* Returns a String array containing the different options that will be displayed to the user */
    protected abstract String[] Choices();

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected abstract void OnChoice(int choice);
}
