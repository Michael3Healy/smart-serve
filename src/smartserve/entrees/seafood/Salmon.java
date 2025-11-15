package smartserve.entrees.seafood;

import smartserve.Meal;

public class Salmon extends Meal {
    @Override
    public String getDescription() {
        return "Grilled Salmon";
    }

    @Override
    public double getCost() {
        return 16.25;
    }
}
