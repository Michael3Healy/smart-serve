package smartserve;

import smartserve.datastore.MenuItem;
import java.util.*;

public class Menu {
	
	/* Author: Ryan Page
	 * Version: 1
	 * Date Last Modified: 11/30/2025
	 * Description: MENU
	 * 		Displays the Menu for viewing
	 */
	
	public Menu() {}
	
	// Displays Welcome Message and Options
	public void displayWelcome() {
		System.out.println("===============================");
		System.out.println("   Welcome to SmartServe POS   ");
		System.out.println("===============================");
		System.out.println("1) Order Items");
		System.out.println("2) View Cart");
		System.out.println("3) Checkout");
		System.out.println("4) Exit");
		System.out.println();
	}
	
	public void displayMeals(List<MenuItem> menuItems) {
		System.out.println("-------------MENU-------------");
		
		int i = 1;
		for (MenuItem item : menuItems) {
			int id = item.getMenuItemId();
			if ((1000 < id) && (id < 4000)) {
				String name = item.getName();
				double price = item.getPrice();
				System.out.printf("%d) %-16s %.2f\n", i, name, price);
				i++;
			}
		}
		
		System.out.println();
	}
	
	public void displaySides(List<MenuItem> menuItems) {
		System.out.println("-------------SIDES-------------");
		
		int i = 1;
		for (MenuItem item : menuItems) {
			int id = item.getMenuItemId();
			if ((4000 < id) && (id < 5000)) {
				String name = item.getName();
				double price = item.getPrice();
				System.out.printf("%d) %-16s %.2f\n", i, name, price);
				i++;
			}
		}
		
		System.out.println();
	}
	
	public void displayDrinks(List<MenuItem> menuItems) {
		System.out.println("------------DRINKS------------");
		
		int i = 1;
		for (MenuItem item : menuItems) {
			int id = item.getMenuItemId();
			if ((5000 < id) && (id < 6000)) {
				String name = item.getName();
				double price = item.getPrice();
				System.out.printf("%d) %-16s %.2f\n", i, name, price);
				i++;
			}
		}
		System.out.println();
	}
	
	public void displayDesserts(List<MenuItem> menuItems) {
		System.out.println("-----------DESSERTS-----------");
		
		int i = 1;
		for (MenuItem item : menuItems) {
			int id = item.getMenuItemId();
			if ((6000 < id) && (id < 7000)) {
				String name = item.getName();
				double price = item.getPrice();
				System.out.printf("%d) %-16s %.2f\n", i, name, price);
				i++;
			}
		}
		System.out.println();
	}
}
