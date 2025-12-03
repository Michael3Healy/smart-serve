package smartserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import smartserve.discounts.*;
import smartserve.datastore.*;
import smartserve.menu_items.decorators.*;
import smartserve.menu_items.decorators.sides.*;
import smartserve.menu_items.decorators.drinks.*;
import smartserve.menu_items.decorators.desserts.*;

/**
 * CustomerInteraction:
 */
public class CustomerInteraction {

    private final Cart cart;
    private final Menu menuView;
    private final Scanner scanner;
    private final List<MenuItem> menuItems = new ArrayList<>();

    public CustomerInteraction(Cart cart, Menu menuView) {
        this.cart = cart;
        this.menuView = menuView;
        this.scanner = new Scanner(System.in);
        loadMenuFromDatastore();
    }

    private void loadMenuFromDatastore() {
        try {
            JsonDataStore dataStore = JsonDataStore.getInstance();
            MenuRepository menuRepo = dataStore.getMenuRepository();
            List<MenuItem> loaded = menuRepo.loadAll();

            menuItems.clear();
            if (loaded != null) {
                menuItems.addAll(loaded);
            }
            if (menuItems.isEmpty()) {
                System.out.println("Warning: No menu items found in datastore.");
            }

        } catch (Exception e) {
            System.out.println("Error loading menu from datastore: " + e.getMessage());
        }
    }

     /**
     * Main interaction loop.
     */
    public void start() {
        boolean running = true;

        while (running) {
            menuView.displayWelcome();   // use Ryan's Menu for the welcome screen
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": // Order Items
                    addMeal();
                    break;
                case "2": // View Cart
                    System.out.println(cart);
                    break;
                case "3": // Checkout with discount selection
                    chooseDiscount();
                    checkout();
                    break;
                case "4": // Exit
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addMeal() {
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }
        // Show only entree-style items (1000–3999) using Menu helper
        menuView.displayMeals(menuItems);

        // Build a list in the same order Menu.displayMeals uses
        List<MenuItem> entreeOptions = new ArrayList<>();
        for (MenuItem item : menuItems) {
            int id = item.getMenuItemId();
            if (1000 < id && id < 4000) {
                entreeOptions.add(item);
            }
        }

        if (entreeOptions.isEmpty()) {
            System.out.println("No entree items available.");
            return;
        }

        System.out.print("Enter the number of the item to add: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (index < 0 || index >= menuItems.size()) {
            System.out.println("Invalid choice. No item added.");
            return;
        }

        MenuItem selected = entreeOptions.get(index);

        OrderItem meal = new OrderItem(selected.getMenuItemId(),
                                       selected.getPrice(),
                                       selected.getName());

        cart.addItem(meal);
        int cartIndex = cart.getAddedItems().size() - 1;

        System.out.println("Added: " + meal.getDescription() +
                           " - $" + String.format("%.2f", meal.getCost()));
        
        // If they want to decorate this meal
        System.out.print("Would you like to customize this item with sides/drinks/desserts? (y/n): ");
        String customize = scanner.nextLine().trim().toLowerCase();

        if (customize.equals("y")) {
            customizeMealAtIndex(cartIndex);
        }

    }
    
    /**
     * Decorate the Meal at the given cart index using the Decorator pattern.
     */
    private void customizeMealAtIndex(int cartIndex) {
        if (cartIndex < 0 || cartIndex >= cart.getAddedItems().size()) {
            System.out.println("Unable to customize: invalid cart index.");
            return;
        }

        OrderItem base = cart.getAddedItems().get(cartIndex);
        boolean customizing = true;

        while (customizing) {
            System.out.println("\nCustomizing: " + base.getDescription() +
                    " - $" + String.format("%.2f", base.getCost()));
            System.out.println("1) Add Side");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Dessert");
            System.out.println("0) Done customizing");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    base = addSideDecorator(base);
                    break;
                case "2":
                    base = addDrinkDecorator(base);
                    break;
                case "3":
                    base = addDessertDecorator(base);
                    break;
                case "0":
                    customizing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            // Update the cart after each decorator
            cart.modItem(cartIndex, base);
        }
    }

    /**
     * Use Menu.displaySides() for output, then wrap the Meal with AddSide.
     */
    private OrderItem addSideDecorator(OrderItem base) {
        List<MenuItem> sideOptions = new ArrayList<>();
        for (MenuItem item : menuItems) {
            int id = item.getMenuItemId();
            if (4000 < id && id < 5000) { // sides range
                sideOptions.add(item);
            }
        }

        if (sideOptions.isEmpty()) {
            System.out.println("No sides available.");
            return base;
        }

        menuView.displaySides(menuItems);

        System.out.print("Enter the number of the side to add: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. No side added.");
            return base;
        }

        if (index < 0 || index >= sideOptions.size()) {
            System.out.println("Invalid choice. No side added.");
            return base;
        }

        MenuItem selected = sideOptions.get(index);
        String name = selected.getName();

        OrderItem decorated = base;
        if ("Fries".equalsIgnoreCase(name)) {
            decorated = new AddSide(base, new Fries());

        } else if ("Asparagus".equalsIgnoreCase(name)) {
            decorated = new AddSide(base, new Asparagus());
        } else {
            System.out.println("Unknown side type for '" + name + "'. No side added.");
            return base;
        }

        System.out.println("Added side: " + name);
        return decorated;
    }

    /**
     * Use Menu.displayDrinks() and wrap with AddDrink.
     */
    private OrderItem addDrinkDecorator(OrderItem base) {
        List<MenuItem> drinkOptions = new ArrayList<>();
        for (MenuItem item : menuItems) {
            int id = item.getMenuItemId();
            if (5000 < id && id < 6000) { // drinks range
                drinkOptions.add(item);
            }
        }

        if (drinkOptions.isEmpty()) {
            System.out.println("No drinks available.");
            return base;
        }

        menuView.displayDrinks(menuItems);

        System.out.print("Enter the number of the drink to add: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. No drink added.");
            return base;
        }

        if (index < 0 || index >= drinkOptions.size()) {
            System.out.println("Invalid choice. No drink added.");
            return base;
        }

        MenuItem selected = drinkOptions.get(index);
        String name = selected.getName();

        OrderItem decorated = base;
        if ("Soda".equalsIgnoreCase(name)) {
            decorated = new AddDrink(base, new Soda());
        } else if ("Water".equalsIgnoreCase(name)) {
            decorated = new AddDrink(base, new Water());
        } else if ("Wine".equalsIgnoreCase(name) || "Glass of Wine".equalsIgnoreCase(name)) {
            decorated = new AddDrink(base, new Wine());
        } else {
            System.out.println("Unknown drink type for '" + name + "'. No drink added.");
            return base;
        }

        System.out.println("Added drink: " + name);
        return decorated;
    }

    /**
     * Use Menu.displayDesserts() and wrap with AddDessert.
     */
    private OrderItem addDessertDecorator(OrderItem base) {
        List<MenuItem> dessertOptions = new ArrayList<>();
        for (MenuItem item : menuItems) {
            int id = item.getMenuItemId();
            if (6000 < id && id < 7000) { // desserts range
                dessertOptions.add(item);
            }
        }

        if (dessertOptions.isEmpty()) {
            System.out.println("No desserts available.");
            return base;
        }

        menuView.displayDesserts(menuItems);

        System.out.print("Enter the number of the dessert to add: ");
        String input = scanner.nextLine().trim();

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. No dessert added.");
            return base;
        }

        if (index < 0 || index >= dessertOptions.size()) {
            System.out.println("Invalid choice. No dessert added.");
            return base;
        }

        MenuItem selected = dessertOptions.get(index);
        String name = selected.getName();

        OrderItem decorated = base;
        if ("Brownie".equalsIgnoreCase(name)) {
            decorated = new AddDessert(base, new Brownie());
        } else if ("Cheesecake".equalsIgnoreCase(name)) {
            decorated = new AddDessert(base, new Cheesecake());
        } else if ("Ice Cream".equalsIgnoreCase(name) || "IceCream".equalsIgnoreCase(name)) {
            decorated = new AddDessert(base, new IceCream());
        } else {
            System.out.println("Unknown dessert type for '" + name + "'. No dessert added.");
            return base;
        }

        System.out.println("Added dessert: " + name);
        return decorated;
    }


    private void chooseDiscount() {
        System.out.println("Choose a discount:");
        System.out.println("1) 10% off");
        System.out.println("2) 20% off");
        System.out.println("3) $5 off");
        System.out.println("4) no discount");
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

        System.out.println("Discount applied. Current total: $" +
                String.format("%.2f", cart.getTotalCost()));
    }

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
            System.out.println("Order confirmed!");
            // Persist the order to the datastore via Restaurant -> OrderRepository
            cart.submitOrder();
            // Clear the cart display and reset its internal CustomerOrder
            cart.clear();
            cart.resetOrder();
        } else {
            System.out.println("Checkout cancelled.");
        }
    }

}