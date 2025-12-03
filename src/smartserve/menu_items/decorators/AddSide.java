package smartserve.menu_items.decorators;

import smartserve.menu_items.decorators.sides.Side;
import smartserve.datastore.OrderItem;

/** Decorator that adds a Side (e.g., Fries) to an Entree. */
public class AddSide extends MealDecorator {
    private final Side side;

    public AddSide(OrderItem entree, Side side) {
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
