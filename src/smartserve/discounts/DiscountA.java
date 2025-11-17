package smartserve.discounts;

public class DiscountA implements DiscountBehaviors {

    @Override
    public double discountItem(double subtotal) {
        return subtotal * 0.90;  // 10% off
    }
}
