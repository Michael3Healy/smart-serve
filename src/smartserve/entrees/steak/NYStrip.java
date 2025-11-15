package smartserve.entrees.steak;
import smartserve.entrees.Entree;

public class NYStrip extends Entree {

    @Override
    public String getDescription() {
        return "NY Strip Steak";
    }

    @Override
    public double getCost() {
        return 28.99;
    }
}