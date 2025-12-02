package smartserve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import smartserve.discounts.DiscountBehaviors;

public class Cart {

    private final List<Meal> addedItems = new ArrayList<>();
    
    private double totalCost;
    
    private DiscountBehaviors strategy;

    public Cart() {
         // Start with no discount strategy by default.
        this.strategy = null;
        this.totalCost = 0.0;
    }

    /**
     * I call this when I want to change the way
     * discounts are applied to this cart.
     */
    public void setStrategy(DiscountBehaviors strategy) {
        this.strategy = strategy;
        updateTotalCost();
    }

    public void addItem(Meal meal) {
        if (meal != null) {
            addedItems.add(meal);
            updateTotalCost();
        }
    }

    public boolean removeItem(int index) {
        if (index < 0 || index >= addedItems.size()) {
            return false;
        }
        addedItems.remove(index);
        updateTotalCost();
        return true;
    }

    // replace an existing item with a new (modified) one
    public boolean modItem(int index, Meal newMeal) {
        if (index < 0 || index >= addedItems.size() || newMeal == null) {
            return false;
        }
        addedItems.set(index, newMeal);
        updateTotalCost();
        return true;
    }

    public List<Meal> getAddedItems() {
        return Collections.unmodifiableList(addedItems);
    }

    public boolean isEmpty() {
        return addedItems.isEmpty();
    }

    public void clear() {
        addedItems.clear();
        updateTotalCost();
    }

    /**
     * This is the subtotal before any discounts are applied.
     */
    public double getSubtotal() {
        double subtotal = 0.0;
        for (Meal meal : addedItems) {
            subtotal += meal.getCost();
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
        if (addedItems.isEmpty()) {
            return "Cart is empty.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addedItems.size(); i++) {
            Meal meal = addedItems.get(i);
            sb.append(i + 1)
              .append(") ")
              .append(meal.getDescription())
              .append(" - $")
              .append(String.format("%.2f", meal.getCost()))
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
