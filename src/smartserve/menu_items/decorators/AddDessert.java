package smartserve.menu_items.decorators;

import smartserve.Meal;
import smartserve.menu_items.decorators.desserts.Dessert;

/**
 * Attach a specific Dessert to an Entree. The Dessert instance provides its
 * own description and cost so multiple dessert types can be added.
 */
public class AddDessert extends MealDecorator {
    private final Dessert dessert;

    public AddDessert(Meal entree, Dessert dessert) {
        super(entree);
        this.dessert = dessert;
    }

    @Override
    public String getDescription() {
        return entree.getDescription() + ", " + dessert.getDescription();
    }

    @Override
    public double getCost() {
        return entree.getCost() + dessert.getCost();
    }
}
