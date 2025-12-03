package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.datastore.DataSeeder;

public class Main {
    public static void main(String[] args) {
        JsonDataStore ds = JsonDataStore.getInstance();
        DataSeeder.seedIngredientsIfNeeded(ds);
        DataSeeder.seedMenuIfNeeded(ds);

        InventoryTracker tracker = new InventoryTracker();
        Restaurant restaurant = new Restaurant();
        Cart cart = new Cart(restaurant);
        Menu menuView = new Menu();
        restaurant.registerObserver(tracker);
        CustomerInteraction ui = new CustomerInteraction(cart, menuView);

        ui.start();
    }
}
