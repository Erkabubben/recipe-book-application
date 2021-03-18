/**
 * RecipeEditorUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * The RecipeEditorUI manages interactions with the user whenever a new
 * recipe is being created, or when an existing recipe is being edited.
 */
public class RecipeEditorUI extends UserInterface {

    private Recipe recipe;

    /* Constructor */
    public RecipeEditorUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData, Recipe r) {
        super(vi, pp, ingrData, rcpData);
        recipe = r;
    }

    /* Overrides the Menu method of the parent class to go to CreateNewRecipe if creating a whole
       new recipe, otherwise displays the Editing Recipe menu */
    @Override protected int Menu() {
        if (recipe.name == "") { // If recipe has no name, go directly to CreateNewRecipe
            return CreateNewRecipe();
        } else { // Otherwise, display menu for editing existing recipe
            System.out.println(recipe.GetRecipeAsString(recipe.portions, prettyPrints, ingredientsData.CURRENCY) + "\n");
            DisplayMenuWithTitleAndOptions(Title());
            return validIn.nextIntInRange("Select an option: ", 1, AmountOfChoices());
        }
    }

    /* Returns the title of the UI class */
    protected String Title() { return "Editing Recipe: " + recipe.name; }

    /* Returns a String array containing the different options that will be displayed to the user */
    protected String[] Choices() {
        return new String[]{
            "Change Recipe Name",
            "Change Default Portions Amount",
            "Add Ingredients To Recipe",
            "Create New Ingredient And Add To Recipe",
            "Delete Ingredients From Recipe",
            "Add Instructions",
            "Delete Instruction Line"
        };
    }

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected void OnChoice(int choice) {
        switch (choice) {
            case 1:
                SetName();
                break;
            case 2:
                SetPortions();
                break;
            case 3:
                AddIngredients();
                break;
            case 4:
                CreateNewIngredientAndAddToRecipe();
                break;
            case 5:
                DeleteIngredients();
                break;
            case 6:
                AddInstructions();
                break;
            case 7:
                DeleteInstructions();
                break;
            default:
                break;
        }
    }

    /* Displays the user interface for creating a new recipe. */
    private int CreateNewRecipe() {
        prettyPrints.SurroundPrintln(" Create New Recipe ", '=');
        SetName();
        if (recipe.name == "") {
            return Choices().length + 1;
        }
        SetPortions();
        AddIngredients();
        AddInstructions();
        return Choices().length + 1;
    }

    /* Displays the user interface for setting the recipe name. */
    private void SetName() {
        recipe.name = validIn.nextLine("Name: ");
    }

    /* Displays the user interface for setting default portions amount. */
    private void SetPortions() {
        double newPortions = validIn.nextDoubleInRange("Portions: ", 0, 9999999);
        // Only ask whether to adjust ingredient amounts if editing a recipe that contains ingredients.
        if (recipe.ingredients.size() > 0) {
            boolean adjustIngredients = validIn.YesOrNo("Adjust ingredient amounts to new amount of portions? (Y/N) ");
            if (adjustIngredients) {
                for (IngredientsListEntry ingredientsListEntry : recipe.ingredients) {
                    ingredientsListEntry.amount = ingredientsListEntry.amount * (newPortions / recipe.portions);
                }
            }
        }
        recipe.portions = newPortions;
    }

    /* Displays the user interface for creating a new ingredient and adding it both to the IngredientsData and to the recipe.
       Uses the CreateNewIngredient method of the IngredientsDataUI. */
    private void CreateNewIngredientAndAddToRecipe() {
        IngredientsDataUI ingredientsDataUI = new IngredientsDataUI(validIn, prettyPrints, ingredientsData, recipesData);
        Ingredient newIngredient = ingredientsDataUI.CreateNewIngredient();
        if (newIngredient != null) {
            IngredientsListEntry ingredientsListEntry = CreateIngredientsListEntry(newIngredient);
            if (ingredientsListEntry != null) {
                recipe.AddIngredientToList(ingredientsListEntry);
                System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                + " was added to the ingredients list of the recipe.");
            }
        }
    }

    /* Displays the user interface for adding ingredients to the recipe. */
    private void AddIngredients() {
        while (true) {
            prettyPrints.Println(recipe.GetIngredientsListAsString(recipe.portions, prettyPrints));
            prettyPrints.SurroundPrintln(" ADD INGREDIENT ", '=');
            String ingredientName = validIn.nextLine("Ingredient (leave empty to exit): ");
            if (ingredientName == null || ingredientName.equals("")) {
                break;
            } else {
                Ingredient ingredient = ingredientsData.GetIngredient(ingredientName);
                if (ingredient == null) {
                    System.out.println("\nIngredient not found.");
                } else {
                    IngredientsListEntry ingredientsListEntry = CreateIngredientsListEntry(ingredient);
                    recipe.AddIngredientToList(ingredientsListEntry);
                    System.out.println(ingredientsListEntry.amount + " " + ingredientsListEntry.ingredient.unit + "(s)" + " of " + ingredientsListEntry.ingredient.name
                    + " was added to ingredients list.");
                }
            }
        }
    }

    /* Displays the user interface for creating a new ingredients list entry. */
    private IngredientsListEntry CreateIngredientsListEntry(Ingredient ingredient) {
        Double amount;
        // Ask for different input type depending on whether the ingredient is divisible
        if (ingredient.divisible) {
            amount = validIn.nextDoubleInRange("Amount: ", 0, 9999999);
        } else {
            amount = Double.valueOf(validIn.nextIntInRange("Amount (must be an even number as ingredient is non-divisible): ", 0, 9999999));
        }
        String comment = validIn.nextLine("Comment: ");
        System.out.println("");
        return new IngredientsListEntry(ingredient, amount, comment);
    }

    /* Displays the user interface for adding instructions to the recipe. */
    private void AddInstructions() {
        prettyPrints.SurroundPrintln(" ADD INSTRUCTIONS ", '=');
        while (true) {
            String instruction = validIn.nextLine("Instruction (leave empty to exit): ");
            if (instruction != null && !instruction.isBlank()) {
                recipe.AddInstructionLine(instruction);
                System.out.println("\"" + instruction + "\"" + " was added to recipe instructions.");
            } else {
                break;
            }
        }
    }

    /* Displays the user interface for deleting entries from the ingredients list of the recipe. */
    private void DeleteIngredients() {
        prettyPrints.SurroundPrintln(" DELETE INGREDIENTS ", '=');
        while (true) {
            String ingredientName = validIn.nextLine("Ingredient to delete from recipe (leave empty to exit): ");
            if (ingredientName != null && !ingredientName.isBlank()) {
                ArrayList<Integer> ingredientsFound = recipe.FindIngredientsInList(ingredientName);
                if (ingredientsFound.size() == 0) {
                    prettyPrints.Println(ingredientName + " wasn't found in recipe ingredients list.");
                } else if (ingredientsFound.size() == 1) {
                    recipe.DeleteIngredientFromList(ingredientsFound.get(0));
                    prettyPrints.Println(ingredientName + " was deleted from recipe.");
                } else {
                    prettyPrints.Println("Which ingredients list entry do you want to delete?\n");
                    int choiceID = 1;
                    for (int i : ingredientsFound) {
                        prettyPrints.Println(choiceID + ". " + recipe.ingredients.get(i).GetDetails());
                        choiceID++;
                    }
                    prettyPrints.Println(choiceID + ". Exit");
                    prettyPrints.Println("");
                    int ingredientToDeleteID = validIn.nextIntInRange("Select an option: ", 1, ingredientsFound.size() + 1);
                    if (ingredientToDeleteID == ingredientsFound.size() + 1) {
                        break;
                    } else {
                        recipe.DeleteIngredientFromList(ingredientsFound.get(ingredientToDeleteID - 1));
                        prettyPrints.Println(ingredientName + " was deleted from recipe.");
                    }
                }
            } else {
                break;
            }
        }
    }

    /* Displays the user interface for deleting instructions from the recipe. */
    private void DeleteInstructions() {
        prettyPrints.SurroundPrintln(" DELETE INSTRUCTIONS ", '=');
        do {
            System.out.println("\n" + recipe.GetInstructionsAsString(prettyPrints) + "\n");
            int choice = validIn.nextIntInRange("Enter the index of the instruction you want to delete (enter 0 to exit): ", 0, recipe.instructions.size());
            if (choice == 0) {
                break;
            } else {
                recipe.DeleteInstructionLine(choice - 1);
                prettyPrints.Println("The instruction was deleted from the recipe.");
            }
        } while (true);
    }
}
