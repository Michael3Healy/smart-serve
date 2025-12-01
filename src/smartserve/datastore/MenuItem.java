/*
* Represents a menu item in the restaurant's menu, such as a burger or drink.
*/


package smartserve.datastore;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private int menuItemId;
    private String name;
    private String description;
    private String category;
    private double price;
    private boolean isAvailable;
    private List<MenuItemIngredient> ingredients = new ArrayList<>(); // Holds all ingredient requirements. MenuItemIngredient has ingredientId and amountNeeded. Initialized to avoid null checks (can add ingredients without fear of NullPointerException).

    // No-arg constructor because gson needs to call it to convert from JSON back to class
    public MenuItem() {
    }

    public MenuItem(int menuItemId, String name, String description,
                    String category, double price, boolean isAvailable) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public void addIngredientRequirement(MenuItemIngredient req) {
        ingredients.add(req);
    }

    // --- getters and setters ---

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<MenuItemIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<MenuItemIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
