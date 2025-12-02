package smartserve.menu_items.decorators.drinks;

public class Water extends Drink {
    @Override
    public String getDescription() {
        return "Water";
    }

    @Override
    public double getCost() {
        return 0.00;
    }
}
