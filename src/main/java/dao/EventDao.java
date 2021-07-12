package dao;

import model.Event;

/**
 * The interface Event dao.
 */
public interface EventDao extends Dao<Event> {

    /**
     * Get event.
     *
     * @param id the id
     * @return the event
     */
    Event get(long id);
}
