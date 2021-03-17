/**
 * IngredientsDataUI
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 */
import java.util.ArrayList;
/**
 * The IngredientsDataUI manages all interaction between the user and the IngredientsData.
 */
public class IngredientsDataUI extends UserInterface {

    private IngredientsData ingredientsData;
    private RecipesData recipesData;

    /* Constructor */
    public IngredientsDataUI(ValidateInput vi, PrettyPrints pp, IngredientsData ingrData, RecipesData rcpData) {
        super(vi, pp);
        ingredientsData = ingrData;
        recipesData = rcpData;
    }

    /* Returns the title of the UI class */
    protected String Title() { return "Ingredients"; }

    /* Returns a String array containing the different options that will be displayed to the user */
    protected String[] Choices() {
        return new String[]{
            "List All Ingredients",
            "Create New Ingredient",
            "Delete Ingredient"
        };
    }

    /* Contains a Switch statement that will trigger code based on what menu option the user has selected */
    protected void OnChoice(int choice) {
        switch (choice) {
            case 1:
                ListAllIngredients();
                break;
            case 2:
                CreateNewIngredient();
                break;
            case 3:
                DeleteIngredient();
                break;
            default:
                break;
        }
    }

    /* Prints a list of all ingredients in IngredientsData */
    private void ListAllIngredients() {
        prettyPrints.SurroundPrintln(" INGREDIENTS ");
        ArrayList<String> ingredients = ingredientsData.GetListOfAllIngredients();
        for (String string : ingredients) {
            System.out.println(string);
        }
        prettyPrints.SurroundPrintln("");
    }

    /* Displays the user interface for creating a new ingredient. */
    protected Ingredient CreateNewIngredient() {
        prettyPrints.SurroundPrintln(" CREATE NEW INGREDIENT ");
        String name = validIn.nextLine("Name: ");
        if (name == null || name.isBlank()) {
            prettyPrints.SurroundPrintln(" No new ingredient was created. ");
            return null;
        } else {
            double price = validIn.nextDoubleInRange("Price: ", 0, 9999999);
            String unit = validIn.next("Unit: ");
            Boolean divisible = validIn.YesOrNo("Ingredient can be divided (Y/N) ");
    
            Ingredient i = new Ingredient(name, price, unit, divisible);
            ingredientsData.AddIngredient(i);
            prettyPrints.SurroundPrintln(" A new ingredient was created: " + i.GetDetails() + " ");
            return i;
        }
    }

    /* Displays the user interface for deleting an ingredient from the ingredients collection. If recipes
       contain the ingredient, the user will be given the option to either abort or let the ingredient
       be deleted from the recipes. */
    private void DeleteIngredient() {
        String ingredientName = validIn.nextLine("Enter the name of the ingredient you want to delete: ");
        if (ingredientName != null && !ingredientName.isBlank()) {
            SearchByContainsIngredient search = new SearchByContainsIngredient(validIn);
            ArrayList<Recipe> recipesContaintingIngredient = search.GetSearchResults(recipesData.GetAllRecipes(), ingredientName);
            if (recipesContaintingIngredient.size() > 0) {
                System.out.println(ingredientName + " is listed as an ingredient in the following recipes:\n");
                for (Recipe recipe : recipesContaintingIngredient) {
                    prettyPrints.Println("- " + recipe.name);
                }
                System.out.println("\nIf you delete the ingredient, it will be automatically deleted from the recipes,");
                System.out.println("and you may need to update the instructions of each of the recipes.");
                Boolean proceed = validIn.YesOrNo("Do you want to proceed with deleting the ingredient? (Y/N) ");
                if (proceed) {
                    for (Recipe recipe : recipesContaintingIngredient) {
                        ArrayList<Integer> ingredientListEntries = recipe.FindIngredientsInList(ingredientName);
                        for (int i = ingredientListEntries.size() - 1; i >= 0; i--) {
                            recipe.DeleteIngredientFromList(ingredientListEntries.get(i));
                        }
                        System.out.println(ingredientName + " was deleted from " + recipe.name + " recipe.");
                    }
                    ProceedToDeleteIngredientFromIngredientsData(ingredientName);
                }
            } else {
                ProceedToDeleteIngredientFromIngredientsData(ingredientName);
            }
        }
    }

    /* Method used only by DeleteIngredient() */
    private void ProceedToDeleteIngredientFromIngredientsData(String ingredientName) {
        boolean ingredientWasDeleted = ingredientsData.DeleteIngredient(ingredientName);
        if (ingredientWasDeleted) {
            System.out.println("SUCCESS: " + ingredientName + " was deleted from the list of available ingredients.");
        } else {
            System.out.println("ERROR: " + ingredientName + " could not be found - did you already delete it?");
        }
    }
}
