package smartserve.menu_items.decorators;

import smartserve.Meal;
import smartserve.menu_items.decorators.drinks.Drink;

/** Decorator that adds a Drink to an Entree (generic, accepts any Drink). */
public class AddDrink extends MealDecorator {
    private final Drink drink;

    public AddDrink(Meal entree, Drink drink) {
        super(entree);
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return entree.getDescription() + ", " + drink.getDescription();
    }

    @Override
    public double getCost() {
        return entree.getCost() + drink.getCost();
    }
}
