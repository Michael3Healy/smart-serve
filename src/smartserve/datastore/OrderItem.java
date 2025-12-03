/*
* Individual item in an order (ex. 1 burger or 1 drink)
*/


package smartserve.datastore;

import java.util.Random;

public class OrderItem {
    private int orderItemId;
    private int menuItemId;
    private double cost;
    private String description;

    public OrderItem() {
    }

    public OrderItem(int menuItemId, double cost, String description) {
        this.orderItemId = new Random().nextInt(1000000); // generate a random ID
        this.menuItemId = menuItemId;
        this.cost = cost;
        this.description = description;
    }

    // -- Getters and Setters --
    
    public int getOrderItemId() {
        return orderItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}