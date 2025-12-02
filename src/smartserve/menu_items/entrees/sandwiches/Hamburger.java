package smartserve.menu_items.entrees.sandwiches;

import smartserve.Meal;

public class Hamburger extends Meal {
    @Override
    public String getDescription() {
        return "Hamburger";
    }

    @Override
    public double getCost() {
        return 11.99;
    }
}
