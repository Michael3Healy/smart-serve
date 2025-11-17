package smartserve.discounts;

public class DiscountB implements DiscountBehaviors {

    @Override
    public double discountItem(double subtotal) {
        return subtotal * 0.80; // 20% off 
    }
}