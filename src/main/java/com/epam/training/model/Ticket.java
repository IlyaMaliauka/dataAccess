package com.epam.training.model;

import lombok.Data;
import com.epam.training.model.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Ticket.
 */
@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }
}
