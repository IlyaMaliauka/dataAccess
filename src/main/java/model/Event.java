package model;

import lombok.Data;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Event.
 */
@Data
public class Event {

    private long id;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private String title;
    private Date date;

    /**
     * Instantiates a new Event.
     *
     * @param title the title
     * @param date  the date
     */
    public Event(String title, Date date) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.date = date;
    }
}
