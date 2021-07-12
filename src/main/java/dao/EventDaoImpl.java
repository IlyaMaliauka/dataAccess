package dao;

import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Event dao.
 */
@Component
public class EventDaoImpl extends BaseDao implements EventDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoImpl.class);
    private Map<String, Object> eventStorage = storage.getStorage();
    private final String STORAGE_PREFIX = "EVENT";

    @Override
    public List<Event> getAll() {
        List<Event> allEvents = new ArrayList<>();
        eventStorage.keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allEvents.add((Event) eventStorage.get(key)));
        return allEvents;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        try {
            eventStorage.remove(removeKey);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed to delete event with id {}", id);
            return false;
        }
    }

    @Override
    public Event create(Event event) {
        String key = STORAGE_PREFIX + event.getId();
        return (Event) eventStorage.put(key, event);
    }

    @Override
    public Event get(long id) {
        String key = STORAGE_PREFIX + id;
        return (Event) eventStorage.get(key);
    }
}
