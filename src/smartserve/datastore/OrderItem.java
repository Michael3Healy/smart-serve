/*
* Individual item in an order (ex. 1 burger or 1 drink)
*/


package smartserve.datastore;

import java.util.Random;

public class OrderItem {
    private int orderItemId;
    private int menuItemId;
    private int quantity;
    private double lineTotal;

    public OrderItem() {
    }

    public OrderItem(int menuItemId, int quantity,
                     double lineTotal) {
        this.orderItemId = new Random().nextInt(1000000); // generate a random ID
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
    }

    // -- Getters and Setters --
    
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
}
