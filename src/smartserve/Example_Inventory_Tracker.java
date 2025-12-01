package smartserve;

import smartserve.datastore.JsonDataStore;
import smartserve.datastore.InventoryRepository;
import smartserve.datastore.MenuRepository;
import smartserve.datastore.CustomerOrder;
import smartserve.datastore.OrderItem;
import smartserve.datastore.MenuItem;
import smartserve.datastore.MenuItemIngredient;
import smartserve.datastore.Ingredient;

import java.util.List;

public class Example_Inventory_Tracker {

    private final InventoryRepository inventoryRepo;
    private final MenuRepository menuRepo;

    // In-memory snapshots
    private final List<Ingredient> ingredients;
    private final List<MenuItem> menuItems;

    public Example_Inventory_Tracker() {
        JsonDataStore ds = JsonDataStore.getInstance();
        this.inventoryRepo = ds.getInventoryRepository();
        this.menuRepo = ds.getMenuRepository();

        this.ingredients = inventoryRepo.loadAll(); // from ingredients.json
        this.menuItems = menuRepo.loadAll();        // from menu.json
    }

    public void applyOrder(CustomerOrder order) {
        for (OrderItem item : order.getItems()) {
            MenuItem menuItem = findMenuItemById(item.getMenuItemId());

            // For each ingredient required by this menu item,
            // subtract from the Ingredient’s quantityOnHand
            for (MenuItemIngredient req : menuItem.getIngredients()) {
                Ingredient ing = findIngredientById(req.getIngredientId());
                double newQty = ing.getQuantityOnHand() - req.getAmountNeeded() * item.getQuantity();
                ing.setQuantityOnHand(newQty);
            }
        }

        // After updating in memory, persist the new snapshot:
        inventoryRepo.saveAll(ingredients);
    }

    private MenuItem findMenuItemById(int id) {
        for (MenuItem mi : menuItems) {
            if (mi.getMenuItemId() == id) {
                return mi;
            }
        }
        throw new IllegalArgumentException("No menu item: " + id);
    }

    private Ingredient findIngredientById(int id) {
        for (Ingredient i : ingredients) {
            if (i.getIngredientId() == id) {
                    return i;
                }
            }
        throw new IllegalArgumentException("No ingredient: " + id);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
