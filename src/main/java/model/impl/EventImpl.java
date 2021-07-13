package model.impl;

import lombok.Data;
import model.Event;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Event.
 */
@Data
public class EventImpl implements Event {

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
    public EventImpl(String title, Date date) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.date = date;
    }
}
