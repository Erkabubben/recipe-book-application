import java.util.ArrayList;

public class Recipe {

    public String name;
    public double portions;
    public ArrayList<IngredientsListEntry> ingredients;
    public ArrayList<String> instructions;

    public Recipe(String n) {
        name = n;
        portions = 0;
        ingredients = new ArrayList<IngredientsListEntry>();
        instructions = new ArrayList<String>();
    }

    public Recipe(String n, double p, ArrayList<IngredientsListEntry> ingr, ArrayList<String> instr) {
        name = n;
        portions = p;
        ingredients = ingr;
        instructions = instr;
    }

    public String GetRecipeAsString(PrettyPrints pp) {
        return GetRecipeAsString(portions, pp);
    }

    public String GetRecipeAsString(double portionsAmount, PrettyPrints pp) {
        String s = pp.SurroundString(" " + name + " (" + portionsAmount + " portions) ", '¤');
        s += "\n\n" + pp.SurroundString(" Ingredients (total price: " + GetTotalPrice(portionsAmount) + ") ", '-') + "\n";
        s += GetIngredientsAsString(portionsAmount, pp);
        s += "\n\n" + pp.SurroundString(" Instructions ", '-') + "\n";
        s += GetInstructionsAsString(pp);
        s += "\n\n" + pp.SurroundString("", '¤');
        return s;
    }

    public String GetIngredientsAsString(double portionsAmount, PrettyPrints pp) {
        String s = "";
        int id = 1;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            String ingredientString = (ingredientsListEntry.amount * (portionsAmount / portions)) + " " + ingredientsListEntry.ingredient.unit + "(s) of " + ingredientsListEntry.ingredient.name;
            s += "\n" + pp.SurroundString(ingredientString, ' ');
            id++;
        }
        return s;
    }

    public String GetInstructionsAsString(PrettyPrints pp) {
        String s = "";
        int id = 1;
        for (String instruction : instructions) {
            String instructionString = id + ") " + instruction;
            s += "\n" + pp.SurroundString(instructionString, ' ');
            id++;
        }
        return s;
    }

    public double GetTotalPrice(double portionsAmount) {
        int total = 0;
        for (IngredientsListEntry ingredientsListEntry : ingredients) {
            total += (ingredientsListEntry.ingredient.price * (ingredientsListEntry.amount * portionsAmount / portions)) ;
        }
        return total;
    }

    public void AddIngredientToList (IngredientsListEntry ingredient) {
        ingredients.add(ingredient);
    }

    public boolean DeleteIngredientFromList (String ingredientName) {
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientsListEntry ingredientsListEntry = ingredients.get(i);
            if (ingredientsListEntry.ingredient.name.equals(ingredientName)) {
                ingredients.remove(i);
                return true;
            }
        }
        return false;
    }

    public void AddInstructionLine(String instructionLine) {
        instructions.add(instructionLine); 
    }

    public void AddInstructionLine(String instructionLine, int atLine) {
        instructions.add(atLine, instructionLine); 
    }

    public void DeleteInstructionLine(int atLine) {
        instructions.remove(atLine);
    }
}
