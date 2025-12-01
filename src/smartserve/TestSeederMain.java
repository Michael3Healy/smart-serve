package smartserve;

import smartserve.datastore.DataSeeder;
import smartserve.datastore.JsonDataStore;

public class TestSeederMain {
    public static void main(String[] args) {
        JsonDataStore ds = JsonDataStore.getInstance();
        DataSeeder.seedIngredientsIfNeeded(ds);
        DataSeeder.seedMenuIfNeeded(ds);
        System.out.println("Seeder test complete!");
    }
}
