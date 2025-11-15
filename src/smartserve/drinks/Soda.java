package smartserve.drinks;

public class Soda extends Drink {
    @Override
    public String getDescription() {
        return "Soda";
    }

    @Override
    public double getCost() {
        return 1.99;
    }
}
