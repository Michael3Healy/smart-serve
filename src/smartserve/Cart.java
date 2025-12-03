package smartserve;

import java.util.List;

import smartserve.datastore.CustomerOrder;
import smartserve.datastore.OrderItem;
import smartserve.discounts.DiscountBehaviors;

public class Cart {

    private CustomerOrder customerOrder = new CustomerOrder();
    
    private double totalCost;
    
    private DiscountBehaviors strategy;

    private Restaurant restaurant;

    public Cart(Restaurant restaurant) {
         // Start with no discount strategy by default.
        this.strategy = null;
        this.totalCost = 0.0;
        this.restaurant = restaurant;
    }

    /**
     * I call this when I want to change the way
     * discounts are applied to this cart.
     */
    public void setStrategy(DiscountBehaviors strategy) {
        this.strategy = strategy;
        updateTotalCost();
    }

    public void submitOrder() {
        customerOrder.setTimestamp(java.time.Instant.now().toString());
        restaurant.placeOrder(customerOrder);
    }

    public void addItem(OrderItem orderItem) {
        if (orderItem != null) {
            updateTotalCost();
            customerOrder.addItem(orderItem);
        }
    }

    public boolean removeItem(int index) {
        if (index < 0 || index >= customerOrder.getItemCount()) {
            return false;
        }
        customerOrder.removeItem(index);
        updateTotalCost();
        return true;
    }

    // replace an existing item with a new (modified) one
    public boolean modItem(int index, OrderItem newItem) {
        if (index < 0 || index >= customerOrder.getItemCount() || newItem == null) {
            return false;
        }
        customerOrder.modItem(index, newItem);
        updateTotalCost();
        return true;
    }

    public List<OrderItem> getAddedItems() {
        return customerOrder.getItems();
    }

    public boolean isEmpty() {
        return customerOrder.getItems().isEmpty();
    }

    public void clear() {
        customerOrder = new CustomerOrder();
        updateTotalCost();
    }

    /**
     * Reset the customer order object so a fresh order is started after submit/clear.
     */
    public void resetOrder() {
        this.customerOrder = new CustomerOrder();
    }

    /**
     * This is the subtotal before any discounts are applied.
     */
    public double getSubtotal() {
        double subtotal = 0.0;
        for (OrderItem item : customerOrder.getItems()) {
            subtotal += item.getCost();
        }
        return subtotal;
    }
    /**
     * This is the total after the current discount strategy runs.
     */
    public double getTotalCost() {
        return totalCost;
    }

    private void updateTotalCost() {
        double subtotal = getSubtotal();
        if (strategy != null) {
            totalCost = strategy.discountItem(subtotal);
        } else {
            totalCost = subtotal;
        }
    }


    @Override
    public String toString() {
        if (customerOrder.getItems().isEmpty()) {
            return "Cart is empty.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < customerOrder.getItemCount(); i++) {
            OrderItem item = customerOrder.getItems().get(i);
            sb.append(i + 1)
              .append(") ")
              .append(item.getDescription())
              .append(" - $")
              .append(String.format("%.2f", item.getCost()))
              .append(System.lineSeparator());
        }
        sb.append("Subtotal: $")
          .append(String.format("%.2f", getSubtotal()))
          .append(System.lineSeparator())
          .append("Total (after discounts): $")
          .append(String.format("%.2f", getTotalCost()));
        return sb.toString();
    }
}
