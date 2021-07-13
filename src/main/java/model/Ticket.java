package model;

import lombok.Data;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Ticket.
 */
@Data
public class Ticket {

    private long id;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
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
    public Ticket(long eventId, long userId, Category category, int place) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }
}
