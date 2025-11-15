package smartserve.entrees.steak;
import smartserve.Meal;

public class NYStrip extends Meal {

    @Override
    public String getDescription() {
        return "NY Strip Steak";
    }

    @Override
    public double getCost() {
        return 28.99;
    }
}