package smartserve.decorators;

import smartserve.Meal;

/**
 * Base decorator for Entree.
 */
public abstract class EntreeDecorator extends Meal {
    protected final Meal entree;

    public EntreeDecorator(Meal entree) {
        this.entree = entree;
    }

    @Override
    public String getDescription() {
        return entree.getDescription();
    }

    @Override
    public double getCost() {
        return entree.getCost();
    }
}
