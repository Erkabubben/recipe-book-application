/**
 * Ingredient
 * 
 * @version 1.0 17 Mar 2021
 * @author Erik Lindholm
 * 
 * An ingredient to be stored in the IngredientsData of the RecipeBookApplication.
 */
public class Ingredient {

    public static String CURRENCY_SIGN = "$";
    public String name;
    public double price;
    public String unit;
    public boolean divisible;

    /* Constructor */
    public Ingredient(String n, double p, String u, boolean d) {
        name = n;
        price = p;
        unit = u;
        divisible = d;
    }

    /* Returns the details of the ingredient as a string to be displayed to the user */
    public String GetDetails() {
        if (divisible) {
            return name + " ( " + price + CURRENCY_SIGN + " / " + unit + "(s) )";
        } else {
            return name + " ( " + price + CURRENCY_SIGN + " / " + unit + "(s), non-divisible )"; 
        }
    }
}
