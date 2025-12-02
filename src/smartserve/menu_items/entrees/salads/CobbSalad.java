package smartserve.menu_items.entrees.salads;
import smartserve.Meal;

public class CobbSalad extends Meal{
    @Override
    public String getDescription() {
        return "Cobb Salad";
    }

    @Override
    public double getCost() {
        return 12.75;
    }
}
