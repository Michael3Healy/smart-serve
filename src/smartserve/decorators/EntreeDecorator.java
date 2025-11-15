package smartserve.decorators;

import smartserve.entrees.Entree;

/**
 * Base decorator for Entree.
 */
public abstract class EntreeDecorator extends Entree {
    protected final Entree entree;

    public EntreeDecorator(Entree entree) {
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
