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
        Menu menuView = new Menu();
        Restaurant restaurant = new Restaurant();
        CustomerInteraction ui = new CustomerInteraction(cart, menuView, restaurant);

        ui.start();
    }
}
