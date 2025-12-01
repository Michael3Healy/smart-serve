package smartserve.datastore;

// Gson library allows easy conversion between Java objects and JSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Java standard library imports for file I/O and collections necessary for storing orders
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final Path filePath;
    private final Gson gson;
    
    // This is how gson docs specifies to handle generic types like List<CustomerOrder> (Type typeOfT = new TypeToken<Collection<Foo>>(){}.getType();)
    private Type listType = new TypeToken<List<CustomerOrder>>(){}.getType(); 

    public OrderRepository(Path filePath) {
        // Store the file path where order data will be saved/loaded
        this.filePath = filePath;

        // Create gson instance with pretty printing for easier readability
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Read order data from JSON file and turn it back into List<CustomerOrder>
    public List<CustomerOrder> loadAll() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try (Reader reader = Files.newBufferedReader(filePath)) {
            List<CustomerOrder> orders = gson.fromJson(reader, listType);
            return orders != null ? orders : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load orders from " + filePath, e);
        }
    }

    // Save List<CustomerOrder> to JSON file
    public void saveAll(List<CustomerOrder> orders) {
        try {
        Files.createDirectories(filePath.getParent());
        Writer writer = Files.newBufferedWriter(filePath);
        gson.toJson(orders, listType, writer);
        writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save orders to " + filePath, e);
        }
    }

    // Convenience method to add a new order
    public void addOrder(CustomerOrder order) {
        List<CustomerOrder> all = loadAll();
        all.add(order);
        saveAll(all);
    }
}
