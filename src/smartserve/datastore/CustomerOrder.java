/* Represents a customer's order, including items, status, and pricing details. */


package smartserve.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CustomerOrder {

    // Enum for order status for consistency
    public enum OrderStatus {
        NEW, READY, COMPLETED
    }

    private int orderId;
    private String timestamp;  
    private OrderStatus status;
    private double subtotal;
    final private double tax = 0.04;
    private double total;
    private Integer discountId;
    private List<OrderItem> items = new ArrayList<>(); // Holds all items in the order. Initialized to avoid null checks.

    // No-arg constructor because gson needs to call it to convert from JSON back to class
    public CustomerOrder() {
    }

    public CustomerOrder(List<OrderItem> initialItems) {
        this.orderId = new Random().nextInt(1000000);
        this.timestamp = java.time.Instant.now().toString();
        this.status = OrderStatus.NEW;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }


    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Calculate subtotal and total based on items and tax
    public void calcTotals() {
        double sum = 0;
        
        for (OrderItem item : items) {
        sum += item.getLineTotal();
        }

        subtotal = sum;
        total = subtotal + (subtotal * tax);
    }

}
