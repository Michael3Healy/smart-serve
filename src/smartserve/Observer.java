package smartserve;

import smartserve.datastore.CustomerOrder;

public interface Observer {

	// Update Observer's Tracked Inventory Values
	public void update(CustomerOrder order);
}
