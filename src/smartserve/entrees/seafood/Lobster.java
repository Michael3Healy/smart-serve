package smartserve.entrees.seafood;

import smartserve.entrees.Entree;

public class Lobster extends Entree {
    @Override
    public String getDescription() {
        return "Lobster Tail";
    }

    @Override
    public double getCost() {
        return 22.00;
    }
}
