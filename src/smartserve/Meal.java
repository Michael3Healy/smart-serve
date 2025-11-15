package smartserve;

/**
 * Abstract component representing an Entree in the ordering system.
 */
public abstract class Meal {
    /**
     * Short description of the entree.
     */
    public abstract String getDescription();

    /**
     * Cost of the entree (before any discount).
     */
    public abstract double getCost();
}
