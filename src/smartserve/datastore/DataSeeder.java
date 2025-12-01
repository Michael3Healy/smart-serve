package smartserve.datastore;

import java.util.ArrayList;
import java.util.List;

/**
 * Seeds initial menu and ingredients into the JSON data store if the data files are empty. (Only happens on first run, when restaurant opens for the first time.)
 */
public class DataSeeder {

    // Seed ingredients only if none exist
    public static void seedIngredientsIfNeeded(JsonDataStore ds) {

        // Datastore for ingredients
        var invRepo = ds.getInventoryRepository();

        // If ingredients already exist, no need to seed
        if (!invRepo.loadAll().isEmpty()) return;

        List<Ingredient> seed = List.of(
            // Sandwich / burger ingredients
            new Ingredient(101, "Burger Bun", 120, 40, "units"),
            new Ingredient(102, "Beef Patty", 80, 20, "units"),
            new Ingredient(103, "Cheese Slice", 200, 50, "units"),
            new Ingredient(104, "Lettuce", 300, 50, "grams"),
            new Ingredient(105, "Tomato", 300, 50, "grams"),
            new Ingredient(106, "Chicken Breast", 60, 20, "units"),

            // Sides
            new Ingredient(201, "Fries", 20000, 2000, "grams"),
            new Ingredient(202, "Ketchup", 5000, 500, "grams"),

            // Condiments / seasonings
            new Ingredient(203, "Steak Sauce", 200, 50, "units"),
            new Ingredient(204, "Butter", 5000, 500, "grams"),
            new Ingredient(205, "Lemon", 200, 50, "units"),
            new Ingredient(206, "Cocktail Sauce", 1500, 300, "grams"),
            new Ingredient(207, "Salt", 10000, 2000, "grams"),
            new Ingredient(208, "Pepper", 5000, 1000, "grams"),

            // Drinks
            new Ingredient(301, "Soda Syrup", 150, 50, "liters"),
            new Ingredient(302, "Water", 500, 100, "liters"),
            new Ingredient(303, "Wine Bottle", 100, 20, "units"),

            // Seafood
            new Ingredient(401, "Salmon Fillet", 40, 10, "units"),
            new Ingredient(402, "Shrimp", 200, 50, "units"),
            new Ingredient(403, "Lobster Tail", 20, 5, "units"),

            // Desserts
            new Ingredient(501, "Ice Cream Scoop", 300, 50, "units"),
            new Ingredient(502, "Cheesecake Slice", 100, 20, "units"),
            new Ingredient(503, "Brownie", 120, 20, "units")
        );

        invRepo.saveAll(seed);
        System.out.println("Seeded ingredients.json");
    }

    // Seed menu only if none exist
    public static void seedMenuIfNeeded(JsonDataStore ds) {
        var menuRepo = ds.getMenuRepository();

        // If menu items already exist, no need to seed
        if (!menuRepo.loadAll().isEmpty()) return;

        List<MenuItem> seedMenu = new ArrayList<>();

        // Sandwiches
        MenuItem hamburger = new MenuItem(1001, "Hamburger", "Classic beef hamburger with bun", "sandwich", 11.99, true);
        hamburger.addIngredientRequirement(new MenuItemIngredient(101, 1)); // bun
        hamburger.addIngredientRequirement(new MenuItemIngredient(102, 1)); // patty
        hamburger.addIngredientRequirement(new MenuItemIngredient(103, 1)); // cheese
        hamburger.addIngredientRequirement(new MenuItemIngredient(104, 15)); // lettuce (grams)
        hamburger.addIngredientRequirement(new MenuItemIngredient(105, 30)); // tomato (grams)
        hamburger.addIngredientRequirement(new MenuItemIngredient(207, 1)); // salt (pinch)
        seedMenu.add(hamburger);

        MenuItem chickenSandwich = new MenuItem(1003, "Chicken Sandwich", "Grilled chicken sandwich", "sandwich", 12.99, true);
        chickenSandwich.addIngredientRequirement(new MenuItemIngredient(101, 1));
        chickenSandwich.addIngredientRequirement(new MenuItemIngredient(106, 1));
        chickenSandwich.addIngredientRequirement(new MenuItemIngredient(104, 15)); // lettuce
        chickenSandwich.addIngredientRequirement(new MenuItemIngredient(105, 30)); // tomato
        seedMenu.add(chickenSandwich);

        // Steak
        MenuItem steak = new MenuItem(2001, "Steak", "Grilled steak entree", "steak", 15.99, true);
        steak.addIngredientRequirement(new MenuItemIngredient(102, 1)); // meat placeholder
        steak.addIngredientRequirement(new MenuItemIngredient(203, 1)); // steak sauce
        steak.addIngredientRequirement(new MenuItemIngredient(204, 10)); // butter (grams)
        steak.addIngredientRequirement(new MenuItemIngredient(207, 1)); // salt
        steak.addIngredientRequirement(new MenuItemIngredient(208, 1)); // pepper
        seedMenu.add(steak);

        // Seafood
        MenuItem salmon = new MenuItem(3001, "Grilled Salmon", "Grilled salmon fillet", "seafood", 16.25, true);
        salmon.addIngredientRequirement(new MenuItemIngredient(401, 1));
        salmon.addIngredientRequirement(new MenuItemIngredient(204, 10)); // butter
        salmon.addIngredientRequirement(new MenuItemIngredient(205, 1)); // lemon
        salmon.addIngredientRequirement(new MenuItemIngredient(207, 1)); // salt
        seedMenu.add(salmon);

        MenuItem shrimp = new MenuItem(3002, "Shrimp Special", "Seasoned shrimp plate", "seafood", 13.50, true);
        shrimp.addIngredientRequirement(new MenuItemIngredient(402, 6));
        shrimp.addIngredientRequirement(new MenuItemIngredient(206, 30)); // cocktail sauce (grams)
        shrimp.addIngredientRequirement(new MenuItemIngredient(205, 1)); // lemon
        shrimp.addIngredientRequirement(new MenuItemIngredient(207, 1)); // salt
        seedMenu.add(shrimp);

        MenuItem lobster = new MenuItem(3003, "Lobster Tail", "Butter poached lobster tail", "seafood", 22.00, true);
        lobster.addIngredientRequirement(new MenuItemIngredient(403, 1));
        lobster.addIngredientRequirement(new MenuItemIngredient(204, 15)); // butter
        lobster.addIngredientRequirement(new MenuItemIngredient(205, 1)); // lemon
        seedMenu.add(lobster);

        // Sides
        MenuItem fries = new MenuItem(4001, "Fries", "Crispy french fries", "side", 2.50, true);
        fries.addIngredientRequirement(new MenuItemIngredient(201, 200)); // grams
        fries.addIngredientRequirement(new MenuItemIngredient(202, 15)); // ketchup (grams)
        fries.addIngredientRequirement(new MenuItemIngredient(207, 1)); // salt
        seedMenu.add(fries);

        // Drinks
        MenuItem soda = new MenuItem(5001, "Soda", "Fountain soda", "drink", 1.99, true);
        soda.addIngredientRequirement(new MenuItemIngredient(301, 0.25)); // liters of syrup per serving approximation
        seedMenu.add(soda);

        MenuItem water = new MenuItem(5002, "Bottled Water", "Bottled water", "drink", 0.00, true);
        water.addIngredientRequirement(new MenuItemIngredient(302, 0.5));
        seedMenu.add(water);

        MenuItem wine = new MenuItem(5003, "Glass of Wine", "Red or white wine", "drink", 6.50, true);
        wine.addIngredientRequirement(new MenuItemIngredient(303, 0.2)); // liters per glass
        seedMenu.add(wine);

        // Desserts
        MenuItem iceCream = new MenuItem(6001, "Ice Cream", "Single scoop ice cream", "dessert", 2.99, true);
        iceCream.addIngredientRequirement(new MenuItemIngredient(501, 1));
        seedMenu.add(iceCream);

        MenuItem cheesecake = new MenuItem(6002, "Cheesecake", "Slice of cheesecake", "dessert", 3.75, true);
        cheesecake.addIngredientRequirement(new MenuItemIngredient(502, 1));
        seedMenu.add(cheesecake);

        MenuItem brownie = new MenuItem(6003, "Brownie", "Chocolate brownie", "dessert", 3.25, true);
        brownie.addIngredientRequirement(new MenuItemIngredient(503, 1));
        seedMenu.add(brownie);

        menuRepo.saveAll(seedMenu);
        System.out.println("Seeded menu.json");
    }
}
