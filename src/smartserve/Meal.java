package smartserve;

/**
 * Abstract component representing a Meal in the ordering system.
 */
public abstract class Meal {
    /**
     * Short description of the meal.
     */
    public abstract String getDescription();

    /**
     * Cost of the entree (before any discount).
     */
    public abstract double getCost();
}
