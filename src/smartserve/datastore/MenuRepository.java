package smartserve.datastore;

// Gson library allows easy conversion between Java objects and JSON
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Java standard library imports for file I/O and collections necessary for storing menu items
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final Path filePath;
    private final Gson gson;
    
    // This is how gson docs specifies to handle generic types like List<MenuItem> (Type typeOfT = new TypeToken<Collection<Foo>>(){}.getType();)
    private final Type listType = new TypeToken<List<MenuItem>>(){}.getType();

    public MenuRepository(Path filePath) {
        // Store the file path where menu data will be saved/loaded
        this.filePath = filePath;

        // Create gson instance with pretty printing for easier readability
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Read menu data from JSON file and turn it back into List<MenuItem>
    public List<MenuItem> loadAll() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try (Reader reader = Files.newBufferedReader(filePath)) {
            List<MenuItem> menuItems = gson.fromJson(reader, listType);
            return menuItems != null ? menuItems : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu from " + filePath, e);
        }
    }

    // Save List<MenuItem> to JSON file
    public void saveAll(List<MenuItem> menuItems) {
        try {
            Files.createDirectories(filePath.getParent());
        } catch (IOException ignored) { }

        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(menuItems, listType, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save menu to " + filePath, e);
        }
    }
}
