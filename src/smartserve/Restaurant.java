package smartserve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant implements Subject{
	
	/* Author: Ryan Page
	 * Version: 1
	 * Date Last Modified: 11/15/2025
	 * Description: RESTAURANT CLASS
	 * 				Outline for the base Restaurant
	 * 
	 */
	
	// Offered Meals = Currently Available to Order
	public HashMap<Meal, Boolean> offeredMeals;
	public HashMap<MealDecorator, Boolean> offeredDecorators;
	// Sales = Number of each Item sold in the current Cart/Order
	public HashMap<Meal, Integer> mealSales;
	public HashMap<MealDecorator, Integer> decoratorSales;
	// Observers for Observer Pattern
	private List<Observer> observers;
	
	public Restaurant() {
		//this.outOfStockDecorators = new ArrayList<MealDecorator>();
		//this.outOfStockMeals = new ArrayList<Meal>();
		this.mealSales = new HashMap<Meal, Integer>();
		this.offeredMeals = new HashMap<Meal, Boolean>();
		this.decoratorSales = new HashMap<MealDecorator, Integer>();
		this.offeredDecorators = new HashMap<MealDecorator, Boolean>();
		this.observers = new ArrayList<Observer>();
	}
	
	// Observer Pattern Methods
	// Adds Observer to Subscriber List
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	// Remove Observer from Subscriber List
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	// Updates Registered Observers
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.updateInventory(mealSales, decoratorSales);
		}
	}
	
	public void inventoryChanged() {
		notifyObservers();
	}
	
	// Notifies Observers of Inventory Changes
	public void setInventory() {
		inventoryChanged();
	}
	
	// Restaurant Methods
	// Adds Meal to Offered Meals, and applies its Current Inventory Amount
	// ?ONLY USE AFTER resetOrder()
	public void addItem(Meal meal) {
		// resetOrder(); Questioning if Need to do for Each addItem()
		mealSales.put(meal, 0);
		offeredMeals.put(meal, true);
		//setInventory(); Questioning if Need to do for Each addItem()
	}
	
	// Adds Decorator to Offered Decorators
	// ?ONLY USE AFTER resetOrder()
	public void addItem(MealDecorator decorator) {
		// resetOrder(); Questioning if Need to do for Each addItem()
		decoratorSales.put(decorator, 0);
		offeredDecorators.put(decorator, true);
		//setInventory(); Questioning if Need to do for Each addItem()
	}
	
	// Removes Meal from Offered Meals
	public void removeItem(Meal meal) {
		// resetOrder(); Questioning if Need to do for Each removeItem()
		mealSales.remove(meal);
		offeredMeals.remove(meal);
		//setInventory(); Questioning if Need to do for Each removeItem()
	}
	
	// Removes Decorator from Offered Meal Decorators
	public void removeItem(MealDecorator decorator) {
		// resetOrder(); Questioning if Need to do for Each removeItem()
		decoratorSales.remove(decorator);
		offeredDecorators.remove(decorator);
		//setInventory(); Questioning if Need to do for Each removeItem()
	}
	
	// Resets the Current Order's Sale Amounts for each Item
	public void resetOrder() {
		// For each Meal, set its Sold Amount to 0
		for (Map.Entry<Meal, Integer> entry : mealSales.entrySet()) {
			Meal meal = entry.getKey();
			mealSales.put(meal, 0);
		}
		
		// For each Decorator, set its Sold Amount to 0
		for (Map.Entry<MealDecorator, Integer> entry : decoratorSales.entrySet()) {
			MealDecorator decorator = entry.getKey();
			decoratorSales.put(decorator, 0);
		}
	}
	
	// PENDING DATASTORE IMPLEMENTATION
	public void storeOrder() {
		// WRITE ORDER TO DATASTORE
		// setInventory();
		// resetOrder();
	}
	
	// Returns the currently Offered Items for the Menu to Display
	public void returnOfferedItems() {
		// GIVES LIST OF OFFERED MEALS, DECORATORS
		// GIVES LIST OF OUT OF STOCK MEALS, DECORATORS
	}

}
