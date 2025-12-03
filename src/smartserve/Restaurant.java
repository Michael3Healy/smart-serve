package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.datastore.InventoryRepository;
import smartserve.datastore.MenuRepository;
import smartserve.datastore.OrderRepository;
import smartserve.datastore.CustomerOrder;
import smartserve.datastore.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Subject {
	
	/* Author: Ryan Page
	 * Version: 2
	 * Date Last Modified: 12/1/2025
	 * Description: RESTAURANT CLASS
	 * 				Outline for the base Restaurant
	 * 
	 */
	
	InventoryRepository inventoryRepo;
	MenuRepository menuRepo;
	List<MenuItem> menuItems;
	OrderRepository orderRepo;
	Menu menuDisplay;
	// Observers for Observer Pattern
	private List<Observer> observers;
	
	public Restaurant() {
		JsonDataStore ds = JsonDataStore.getInstance();
        this.inventoryRepo = ds.getInventoryRepository();
        this.menuRepo = ds.getMenuRepository();
        this.orderRepo = ds.getOrderRepository();
		this.observers = new ArrayList<Observer>();
		this.menuItems = menuRepo.loadAll();
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
	public void notifyObservers(CustomerOrder order) {
		for (Observer observer : observers) {
			observer.update(order);
		}
	}
	
	// Restaurant Methods
	// Displays Welcome Message
	public void welcome() {
		menuDisplay.displayWelcome();
	}
	
	// Displays Meals/Entree Menu
	public void meals() {
		menuDisplay.displayMeals(menuItems);
	}
	
	// Displays Sides Menu
	public void sides() {
		menuDisplay.displaySides(menuItems);
	}
	
	// Displays Drinks Menu
	public void drinks() {
		menuDisplay.displayDrinks(menuItems);
	}
	
	// Displays Dessert Menu
	public void desserts() {
		menuDisplay.displayDesserts(menuItems);
	}
	
	// Stores Order into the Order Repository and notifies Inventory Tracker
	public void placeOrder(CustomerOrder order) {
		orderRepo.addOrder(order);
		notifyObservers(order);
	}
}
