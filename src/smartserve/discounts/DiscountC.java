package smartserve.discounts;

public class DiscountC implements DiscountBehaviors {

    @Override
    public double discountItem(double subtotal) {
        double discounted = subtotal - 5.00;
        return Math.max(discounted, 0.0);
    }
}