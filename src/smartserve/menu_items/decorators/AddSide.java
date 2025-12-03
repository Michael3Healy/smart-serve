package smartserve.menu_items.decorators;

import smartserve.datastore.OrderItem;

/** Decorator that adds a Side to an Entree. */
public class AddSide extends OrderItem {

    public AddSide(OrderItem entree, OrderItem side) {
        super(
            entree.getMenuItemId(),
            // Total cost is entree cost + side cost
            entree.getCost() + side.getCost(),
            // Description is entree description + side description
            entree.getDescription() + ", " + side.getDescription()
        );
    }
}
