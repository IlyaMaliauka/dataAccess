package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Hash map storage.
 */
public class Storage {

    public static final String ENTITIES_JSON = "src/main/resources/entities.json";
    private static Map<String, Object> storage = new HashMap<>();

    /**
     * Gets storage.
     *
     * @return the storage
     */
    public Map<String, Object> getStorage() {
        return storage;
    }

    private void initStorageFromJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        storage
                = objectMapper.readValue(new File(ENTITIES_JSON), new TypeReference<Map<String,Object>>(){});
    }

    private void clearStorage() {
        storage = null;
    }
}
