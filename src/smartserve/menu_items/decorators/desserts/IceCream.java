package smartserve.menu_items.decorators.desserts;

public class IceCream extends Dessert {
    @Override
    public String getDescription() {
        return "Ice Cream";
    }

    @Override
    public double getCost() {
        return 2.99;
    }
}
