package model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The type Event.
 */
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private Date date;

    /**
     * Instantiates a new Event.
     *
     * @param title the title
     * @param date  the date
     */
    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
