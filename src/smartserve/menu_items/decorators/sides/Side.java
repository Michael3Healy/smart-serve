package smartserve.menu_items.decorators.sides;
import smartserve.datastore.OrderItem;

public abstract class Side extends OrderItem {
    public abstract String getDescription();
    public abstract double getCost();
}
