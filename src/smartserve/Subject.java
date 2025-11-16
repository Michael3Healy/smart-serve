package smartserve;

public interface Subject {
	
	/* Author: Ryan Page
	 * Version: 1
	 * Date Last Modified: 11/15/2025
	 * Part of Observer Design Pattern
	 * Description: SUBJECT INTERFACE
	 */
	
	// Add Observer to Subscriber List
	public void registerObserver(Observer o);
	
	// Remove Observer from Subscriber List
	public void removeObserver(Observer o);
	
	// Notify Observers on Subscriber List
	public void notifyObservers();

}
