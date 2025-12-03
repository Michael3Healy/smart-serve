package smartserve.menu_items.decorators;

import smartserve.datastore.OrderItem;

/** Decorator that adds a Dessert to an Entree. */
public class AddDessert extends OrderItem {

    public AddDessert(OrderItem entree, OrderItem dessert) {
        super(
            entree.getMenuItemId(),
            // Total cost is entree cost + dessert cost
            entree.getCost() + dessert.getCost(),
            // Description is entree description + dessert description
            entree.getDescription() + ", " + dessert.getDescription()
        );
    }
}
