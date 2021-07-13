package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Hash map storage.
 */
public class Storage {

    private static final Map<String, Object> storage = new HashMap<>();

    public Map<String, Object> getStorage() {
        return storage;
    }
}
