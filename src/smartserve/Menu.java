package smartserve;

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
	
	// Displays Entree Options
	public void displayMeals() {
		System.out.println("-------------MENU-------------");
		
		System.out.println("1: Hamburger");
		System.out.println("2: Chicken Sandwich");
		System.out.println("3: Steak");
		System.out.println("4: Grilled Salmon");
		System.out.println("5: Shrimp Special");
		System.out.println("6: Lobster Tail");
	}
	
	// Displays Side Options
	public void displaySides() {
		System.out.println("-------------SIDES-------------");
		
		System.out.println("1: Fries");
		System.out.println("2: None");
	}
	
	// Displays Drink Options
	public void displayDrinks() {
		System.out.println("------------DRINKS------------");
		
		System.out.println("1: Fountain Soda");
		System.out.println("2: Bottled Water");
		System.out.println("3: Glass of Wine");
		System.out.println("4: None");
	}
	
	// Displays Dessert Options
	public void displayDesserts() {
		System.out.println("-----------DESSERTS-----------");
		
		System.out.println("1: Ice Cream");
		System.out.println("2: Cheesecake");
		System.out.println("3: Chocolate Brownie");
		System.out.println("4: None");
	}
}
