
/* Represents an ingredient used in the restaurant, including its current stock and reorder information. */

package smartserve.datastore;

public class Ingredient {
    private int ingredientId;
    private String name;
    private double quantityOnHand;
    private double reorderThreshold;
    private String unit;

    // No-arg constructor because gson needs to call it to convert from JSON back to class
    public Ingredient() {
    }

    public Ingredient(int ingredientId, String name, double quantityOnHand,
                      double reorderThreshold, String unit) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.quantityOnHand = quantityOnHand;
        this.reorderThreshold = reorderThreshold;
        this.unit = unit;
    }

    // --- getters and setters ---

    public int getIngredientId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public double getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(double quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public double getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(double reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    public String getUnit() {
        return unit;
    }

}
