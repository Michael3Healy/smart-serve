package smartserve;

import java.util.Scanner;
import smartserve.discounts.*;

import smartserve.entrees.sandwiches.Hamburger; 
import smartserve.entrees.sandwiches.ChickenSandwich; 

/**
 * This class is the main point of interaction for the customer.
 * It lets the user add a meal to the cart, view the cart, choose a discount, and checkout.
 */
public class CustomerInteraction {

    private Cart cart;
    private Scanner scanner;

    public CustomerInteraction(Cart cart) {
        this.cart = cart;
        this.scanner = new Scanner(System.in);
    }

    /**
     * This starts the main loop of the customer interface.
     */
    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- SmartServe Menu ---");
            System.out.println("1) Add meal to cart");
            System.out.println("2) View cart");
            System.out.println("3) Choose discount");
            System.out.println("4) Checkout");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addMeal();
                    break;
                case "2":
                    System.out.println(cart);
                    break;
                case "3":
                    chooseDiscount();
                    break;
                case "4":
                    checkout();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Choose between Hamburger or ChickenSandwich as the meal for now.
     * This just shows how meals get added to the Cart.
     */
    private void addMeal() {
    System.out.println("Choose a meal:");
    System.out.println("1) Hamburger");
    System.out.println("2) Chicken Sandwich");
    System.out.print("Enter choice: ");

    String choice = scanner.nextLine().trim();
    Meal meal;

    switch (choice) {
        case "1":
            meal = new Hamburger();
            break;
        case "2":
            meal = new ChickenSandwich();
            break;
        default:
            System.out.println("Invalid choice. No meal added.");
            return;
    }

    cart.addItem(meal);
    System.out.println("Added: " + meal.getDescription());
}

    

    /**
     * Allows the customer to choose which discount strategy to apply.
     */
    private void chooseDiscount() {
        System.out.println("Choose a discount:");
        System.out.println("1) DiscountA (10% off)");
        System.out.println("2) DiscountB (20% off)");
        System.out.println("3) DiscountC ($5 off)");
        System.out.println("4) DiscountD (no discount)");
        System.out.print("Enter choice: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                cart.setStrategy(new DiscountA());
                break;
            case "2":
                cart.setStrategy(new DiscountB());
                break;
            case "3":
                cart.setStrategy(new DiscountC());
                break;
            case "4":
                cart.setStrategy(new DiscountD());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Discount applied. New total: $" +
                String.format("%.2f", cart.getTotalCost()));
    }

    /**
     * Handles checkout and calls updateInventory()
     */
    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("\n--- Checkout ---");
        System.out.println(cart);
        System.out.print("Confirm purchase? (y/n): ");

        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            cart.updateInventory(); 
            System.out.println("Order confirmed!");
            cart.clear();
        } else {
            System.out.println("Checkout cancelled.");
        }
    }
}
