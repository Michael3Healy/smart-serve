package smartserve.menu_items.entrees.sandwiches;

import smartserve.Meal;

public class ChickenSandwich extends Meal {
    @Override
    public String getDescription() {
        return "Chicken Sandwich";
    }

    @Override
    public double getCost() {
        return 12.99;
    }
}
