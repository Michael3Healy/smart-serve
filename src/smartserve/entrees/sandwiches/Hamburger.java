package smartserve.entrees.sandwiches;

import smartserve.entrees.Entree;

public class Hamburger extends Entree {
    @Override
    public String getDescription() {
        return "Hamburger";
    }

    @Override
    public double getCost() {
        return 11.99;
    }
}
