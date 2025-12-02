package smartserve.menu_items.decorators.sides;

public class Fries extends Side {
    @Override
    public String getDescription() {
        return "Fries";
    }

    @Override
    public double getCost() {
        return 2.50;
    }
}
