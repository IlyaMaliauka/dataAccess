package com.epam.training.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * The type Event.
 */
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
