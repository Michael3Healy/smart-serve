package smartserve.menu_items.decorators;

import smartserve.Meal;
import smartserve.datastore.OrderItem;

/**
 * Base decorator for Meal.
 */
public abstract class MealDecorator extends Meal {
    protected final OrderItem entree;

    public MealDecorator(OrderItem entree) {
        this.entree = entree;
    }

    @Override
    public String getDescription() {
        return entree.getDescription();
    }

    @Override
    public double getCost() {
        return entree.getCost();
    }
}
