package smartserve;

import java.util.HashMap;

public interface Observer {
	
	/* Author: Ryan Page
	 * Version: 1
	 * Date Last Modified: 11/15/2025
	 * Part of Observer Design Pattern
	 * Description: OBSERVER INTERFACE
	 * 
	 */
	
	// Update Observer's Tracked Inventory Values
	public void updateInventory(HashMap<Meal, Integer> mealSales, HashMap<MealDecorator, Integer> decoratorSales);

}
