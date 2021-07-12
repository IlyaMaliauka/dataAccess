package service.impl;

import dao.EventDao;
import model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import service.EventService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Event service.
 */
public class EventServiceImpl implements EventService {

    private EventDao eventDao;

    public EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * Gets event by its id.
     *
     * @param eventId
     * @return Event.
     */
    @Override
    public Event getEventById(long eventId) {
        return eventDao.get(eventId);
    }

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param title    Event title or it's part.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.getAll().stream().filter(event -> event.getTitle().contains(title)).collect(Collectors.toList());
    }

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     *
     * @param day      Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of events.
     */
    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventDao.getAll().stream().filter(event -> event.getDate().equals(day)).collect(Collectors.toList());
    }

    /**
     * Creates new event. Event id should be auto-generated.
     *
     * @param event Event data.
     */
    @Override
    public Event create(Event event) {
        return eventDao.create(event);
    }

    /**
     * Updates event using given data.
     *
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    @Override
    public Event updateEvent(Event event) {
        return eventDao.create(event);
    }

    /**
     * Deletes event by its id.
     *
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    @Override
    public boolean delete(long eventId) {
        return eventDao.delete(eventId);
    }
}
