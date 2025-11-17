package smartserve.discounts;

public class DiscountD implements DiscountBehaviors {

    @Override
    public double discountItem(double subtotal) {
        return subtotal; // no discount
    }
}