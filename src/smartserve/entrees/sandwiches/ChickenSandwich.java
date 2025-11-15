package smartserve.entrees.sandwiches;

import smartserve.entrees.Entree;

public class ChickenSandwich extends Entree {
    @Override
    public String getDescription() {
        return "Chicken Sandwich";
    }

    @Override
    public double getCost() {
        return 12.99;
    }
}
