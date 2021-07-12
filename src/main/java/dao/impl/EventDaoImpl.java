package dao.impl;

import dao.EventDao;
import model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Event dao.
 */
public class EventDaoImpl implements EventDao {

    private Storage eventStorage;
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDaoImpl.class);
    private final String STORAGE_PREFIX = "EVENT";

    /**
     * Sets storage.
     *
     * @param eventStorage the storage
     */
    public void setEventStorage(Storage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public List<Event> getAll() {
        List<Event> allEvents = new ArrayList<>();
        eventStorage.getStorage().keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allEvents.add((Event) eventStorage.getStorage().get(key)));
        return allEvents;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        try {
            eventStorage.getStorage().remove(removeKey);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed to delete event with id {}", id);
            return false;
        }
    }

    @Override
    public Event create(Event event) {
        String key = STORAGE_PREFIX + event.getId();
        return (Event) eventStorage.getStorage().put(key, event);
    }

    @Override
    public Event get(long id) {
        String key = STORAGE_PREFIX + id;
        return (Event) eventStorage.getStorage().get(key);
    }
}
