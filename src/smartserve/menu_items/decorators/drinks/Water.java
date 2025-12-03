package smartserve.menu_items.decorators.drinks;

import smartserve.datastore.OrderItem;

public class Water extends OrderItem {
    public Water() {
        super(5002, 1.50, "Water");
    }
}
