package smartserve.entrees.seafood;

import smartserve.Meal;

public class Lobster extends Meal {
    @Override
    public String getDescription() {
        return "Lobster Tail";
    }

    @Override
    public double getCost() {
        return 22.00;
    }
}
