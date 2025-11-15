package smartserve.entrees.seafood;

import smartserve.entrees.Entree;

public class Salmon extends Entree {
    @Override
    public String getDescription() {
        return "Grilled Salmon";
    }

    @Override
    public double getCost() {
        return 16.25;
    }
}
