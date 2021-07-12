package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Hash map storage.
 */
public class HashMapStorage implements Storage {

    @Override
    public Map<String, Object> getStorage() {
        return new HashMap<>();
    }
}
