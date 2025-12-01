package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.datastore.OrderRepository;
import smartserve.datastore.CustomerOrder;

public class Customer_Interaction_Example {

    private final Cart cart;
    // private final InventoryTracker inventoryTracker;
    private final OrderRepository orderRepo;

    public Customer_Interaction_Example(Cart cart, InventoryTracker inventoryTracker) {
        this.cart = cart;
        // this.inventoryTracker = inventoryTracker;
        this.orderRepo = JsonDataStore.getInstance().getOrderRepository();
    }

    public void start() {
        // loop, show menu, call cart.addItem(...), etc.

        // When customer chooses "checkout":
        CustomerOrder order = cart.checkout();

        // 1. Save order to datastore
        orderRepo.addOrder(order);

        // 2. Update inventory
        // inventoryTracker.applyOrder(order);

        // 3. Clear cart for next customer
        cart.clear();
    }
}
