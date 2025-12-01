package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.InventoryTracker;
import smartserve.Cart;
import smartserve.CustomerInteraction;
import smartserve.datastore.DataSeeder;

public class Main {
    public static void main(String[] args) {
        JsonDataStore ds = JsonDataStore.getInstance();
        DataSeeder.seedIngredientsIfNeeded(ds);
        DataSeeder.seedMenuIfNeeded(ds);

        InventoryTracker tracker = new InventoryTracker();
        Cart cart = new Cart();
        CustomerInteraction ui = new CustomerInteraction(cart, tracker);

        ui.start();
    }
}
