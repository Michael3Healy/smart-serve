/* Represents a customer's order, including items, status, and pricing details. */


package smartserve.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CustomerOrder {

    private int orderId;
    private String timestamp;
    private List<OrderItem> items = new ArrayList<>(); // Holds all items in the order. Initialized to avoid null checks.

    // No-arg constructor because gson needs to call it to convert from JSON back to class. Also called by Cart when initializing.
    public CustomerOrder() {
        this.orderId = new Random().nextInt(1, 1_000_000); // Random order ID for simplicity
    }

    public CustomerOrder(List<OrderItem> initialItems) {
        this.orderId = new Random().nextInt(1, 1_000_000); // Random order ID for simplicity
        this.items = initialItems;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void modItem(int index, OrderItem newItem) {
        items.set(index, newItem);
    }

}
