/**
 * Singleton class managing JSON data stores for orders, menu, and inventory.
 */


package smartserve.datastore;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonDataStore {
    private static JsonDataStore instance;

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final InventoryRepository inventoryRepository;

    private JsonDataStore() {
        // Folder to store all JSON data files
        Path dataDir = Paths.get("data");

        // Initialize repositories with their respective JSON file paths (e.g. data/orders.json)
        this.orderRepository = new OrderRepository(dataDir.resolve("orders.json"));
        this.menuRepository = new MenuRepository(dataDir.resolve("menu.json"));
        this.inventoryRepository = new InventoryRepository(dataDir.resolve("ingredients.json"));
    }

    public static synchronized JsonDataStore getInstance() {
        if (instance == null) {
            instance = new JsonDataStore();
        }
        return instance;
    }

    public OrderRepository getOrderRepository() { return orderRepository; }
    public MenuRepository getMenuRepository() { return menuRepository; }
    public InventoryRepository getInventoryRepository() { return inventoryRepository; }
}

