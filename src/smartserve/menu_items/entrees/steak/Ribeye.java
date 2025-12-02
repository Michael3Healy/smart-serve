package smartserve.menu_items.entrees.steak;

import smartserve.Meal;

public class Ribeye extends Meal {
    @Override
    public String getDescription() {
        return "Steak";
    }

    @Override
    public double getCost() {
        return 15.99;
    }
}
