/* 
* Represents the ingredients needed for a menu item
*
* Connects MenuItem and Ingredient entities (many-to-many relationship)
*/


package smartserve.datastore;

public class MenuItemIngredient {
    private int ingredientId;
    private double amountNeeded;

    public MenuItemIngredient() {
    }

    public MenuItemIngredient(int ingredientId, double amountNeeded) {
        this.ingredientId = ingredientId;
        this.amountNeeded = amountNeeded;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(double amountNeeded) {
        this.amountNeeded = amountNeeded;
    }
}
