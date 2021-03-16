public class Ingredient {

    public static String CURRENCY_SIGN = "$";
    public String name;
    public double price;
    public String unit;
    public boolean divisible;

    public Ingredient(String n, double p, String u, boolean d) {
        name = n;
        price = p;
        unit = u;
        divisible = d;
    }

    public String GetDetails() {
        if (divisible) {
            return name + " ( " + price + CURRENCY_SIGN + " / " + unit + "(s) )";
        } else {
            return name + " ( " + price + CURRENCY_SIGN + " / " + unit + "(s), non-divisible )"; 
        }
    }
}
