package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Hash map storage.
 */
public class Storage {

    private static Map<String, Object> instance;

    private Storage() {
    }

    public static Map<String, Object> getStorage() {
        if (instance == null) {
            instance = new HashMap<>();
        }
        return instance;
    }
}
