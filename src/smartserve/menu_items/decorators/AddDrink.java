package smartserve.menu_items.decorators;

import smartserve.datastore.OrderItem;

/** Decorator that adds a Drink to an Entree. */
public class AddDrink extends OrderItem {

    public AddDrink(OrderItem entree, OrderItem drink) {
        super(
            entree.getMenuItemId(),
            // Total cost is entree cost + drink cost
            entree.getCost() + drink.getCost(),
            // Description is entree description + drink description
            entree.getDescription() + ", " + drink.getDescription()
        );
    }
}
