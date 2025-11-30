package smartserve;

/**
 * Connects the CustomerInteraction with a new Cart instance.
 */
public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        CustomerInteraction ui = new CustomerInteraction(cart);
        ui.start();
    }
}
