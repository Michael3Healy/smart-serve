package smartserve.entrees.seafood;

import smartserve.entrees.Entree;

public class Shrimp extends Entree {
    @Override
    public String getDescription() {
        return "Shrimp Special";
    }

    @Override
    public double getCost() {
        return 17.50;
    }
}
