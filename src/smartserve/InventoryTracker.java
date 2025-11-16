package smartserve;

import java.util.*;

public class InventoryTracker implements Observer {

	/*
	 * Author: Ryan Page Version: 1 Date Last Modified: 11/15/2025 Part of Observer
	 * Design Pattern Description: INVENTORYTRACKER, Eager Singleton
	 */

	private static InventoryTracker inventoryTracker = new InventoryTracker(); // Eager Singleton
	HashMap<Meal, Integer> meals; // Maintains Meals and their Inventory Amount
	HashMap<MealDecorator, Integer> decorators; // Maintains Decorators and their Inventor Amount
	private Restaurant restaurant;

	private InventoryTracker() {
		// Instantiates both HashMaps
		this.meals = new HashMap<Meal, Integer>();
		this.decorators = new HashMap<MealDecorator, Integer>();
	}

	public static InventoryTracker getInstance() {
		// Singleton
		return inventoryTracker;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void getInventory() {
		// SETS INVENTORY AMOUNTS 
		// EITHER MANUALLY OR FROM DATASTORE
	}

	// Updates Tracked Meal and MealDecorator Inventory Amounts
	public void updateInventory(HashMap<Meal, Integer> mealSales, HashMap<MealDecorator, Integer> decoratorSales) {
		// For each Meal in the passed MealInventory, Update its Inventory Counter
		// Iteration Code from:
		// https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
		for (Map.Entry<Meal, Integer> entry : mealSales.entrySet()) {
			Meal meal = entry.getKey();
			int mealsSold = entry.getValue();
			int currentMealInventory = meals.get(meal);
			int newMealInventory = currentMealInventory - mealsSold;
			meals.put(meal, newMealInventory);

			// Checks if the Item is OutOfStock, and Updates its InStock/OutOfStock State
			if (newMealInventory <= 0) {
				restaurant.offeredMeals.put(meal, false);
			} else {
				restaurant.offeredMeals.put(meal, true);
			}
		}
		
		// For each Decorator in the passed DecoratorInventory, Updates its Inventory Counter
		for (Map.Entry<MealDecorator, Integer> entry : decoratorSales.entrySet()) {
			MealDecorator decorator = entry.getKey();
			int decoratorsSold = entry.getValue();
			int currentDecoratorInventory = decorators.get(decorator);
			int newDecoratorInventory = currentDecoratorInventory - decoratorsSold;
			decorators.put(decorator, newDecoratorInventory);

			// Checks if the Decorator is OutOfStock, and Updates its InStock/OutOfStock State
			if (newDecoratorInventory <= 0) {
				restaurant.offeredDecorators.put(decorator, false);
			} else {
				restaurant.offeredDecorators.put(decorator, true);
			}
		}
	}

}
