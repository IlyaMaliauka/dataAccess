package model.impl;

import lombok.Data;
import model.Category;
import model.Ticket;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Ticket.
 */
@Data
public class TicketImpl implements Ticket {

    private long id;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    /**
     * Instantiates a new Ticket.
     *
     * @param eventId  the event id
     * @param userId   the user id
     * @param category the category
     * @param place    the place
     */
    public TicketImpl(long eventId, long userId, Category category, int place) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }
}
