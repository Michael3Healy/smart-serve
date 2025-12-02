package smartserve;

public interface Subject {
	// Add Observer to Subscriber List
	public void registerObserver(Observer o);
	
	// Remove Observer from Subscriber List
	public void removeObserver(Observer o);
	
	// Notify Observers on Subscriber List
	public void notifyObservers();
}
