package smartserve.menu_items.decorators;

import smartserve.Meal;
import smartserve.menu_items.decorators.sides.Side;

/** Decorator that adds a Side (e.g., Fries) to an Entree. */
public class AddSide extends MealDecorator {
    private final Side side;

    public AddSide(Meal entree, Side side) {
        super(entree);
        this.side = side;
    }

    @Override
    public String getDescription() {
        return entree.getDescription() + ", " + side.getDescription();
    }

    @Override
    public double getCost() {
        return entree.getCost() + side.getCost();
    }
}
