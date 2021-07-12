package service;


import dao.TicketDaoImpl;
import model.Event;
import model.Ticket;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import util.Category;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Ticket service.
 */
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketDaoImpl ticketDao;

    /**
     * Book ticket for a specified event on behalf of specified user.
     *
     * @param userId   User Id.
     * @param eventId  Event Id.
     * @param place    Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws IllegalStateException if this place has already been booked.
     */
    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        return ticketDao.create(new Ticket(eventId, userId, category, place));
    }

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     *
     * @param user     User
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.getAll().stream().filter(ticket -> ticket.getUserId()== user.getId()).collect(Collectors.toList());
    }

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     *
     * @param event    Event
     * @param pageSize Pagination param. Number of tickets to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of Ticket objects.
     */
    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.getAll().stream().filter(ticket -> ticket.getEventId()==event.getId()).collect(Collectors.toList());
    }

    /**
     * Cancel ticket with a specified id.
     *
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.delete(ticketId);
    }
}
