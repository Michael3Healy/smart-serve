package smartserve.desserts;

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
