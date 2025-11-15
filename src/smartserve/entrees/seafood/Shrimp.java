package smartserve.entrees.seafood;

import smartserve.Meal;

public class Shrimp extends Meal {
    @Override
    public String getDescription() {
        return "Shrimp Special";
    }

    @Override
    public double getCost() {
        return 17.50;
    }
}
