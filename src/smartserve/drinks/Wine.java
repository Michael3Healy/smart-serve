package smartserve.drinks;

public class Wine extends Drink {
    @Override
    public String getDescription() {
        return "Wine";
    }

    @Override
    public double getCost() {
        return 8.00;
    }
}