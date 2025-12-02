package smartserve.menu_items.entrees.salads;
import smartserve.Meal;

public class HouseSalad extends Meal {
    @Override
    public String getDescription() {
        return "House Salad";
    }

    @Override
    public double getCost() {
        return 9.50;
    }
}
