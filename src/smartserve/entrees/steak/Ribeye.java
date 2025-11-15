package smartserve.entrees.steak;

import smartserve.entrees.Entree;

public class Ribeye extends Entree {
    @Override
    public String getDescription() {
        return "Steak";
    }

    @Override
    public double getCost() {
        return 15.99;
    }
}
