package smartserve;

import smartserve.datastore.CustomerOrder;
import smartserve.datastore.OrderItem;
import smartserve.datastore.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<OrderItem> items = new ArrayList<>();

    public void addItem(MenuItem menuItem, int quantity) {
        OrderItem item = new OrderItem(menuItem.getMenuItemId(),
                quantity, menuItem.getPrice() * quantity);
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    /** Called when the customer is done; creates a CustomerOrder from current cart contents. */
    public CustomerOrder checkout() {
        CustomerOrder order = new CustomerOrder(new ArrayList<>(items));
        order.calcTotals();
        return order;
    }
}
