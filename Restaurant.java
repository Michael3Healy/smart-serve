package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.datastore.InventoryRepository;
import smartserve.datastore.MenuRepository;
import smartserve.datastore.OrderRepository;
import smartserve.datastore.CustomerOrder;
import smartserve.datastore.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Subject{
	
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
	CustomerOrder customerOrder;
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
		this.customerOrder = new CustomerOrder();
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
			observer.update(customerOrder);
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
	
	// Stores Order into the Order Repository
	public void storeOrder() {
		orderRepo.addOrder(customerOrder);
		setInventory();
		customerOrder = new CustomerOrder();
	}
	
	// Customer Order becomes Cart Order, then Restaurant Stores Order
	public void setOrder(CustomerOrder order) {
		this.customerOrder = order;
		storeOrder();
	}

}
