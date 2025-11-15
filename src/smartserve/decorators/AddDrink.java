package smartserve.decorators;

import smartserve.drinks.Drink;
import smartserve.entrees.Entree;

/** Decorator that adds a Drink to an Entree (generic, accepts any Drink). */
public class AddDrink extends EntreeDecorator {
    private final Drink drink;

    public AddDrink(Entree entree, Drink drink) {
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
