package dao.impl;

import dao.TicketDao;
import model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ticket dao.
 */
public class TicketDaoImpl implements TicketDao {

    private Storage ticketStorage;
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);
    private static final String STORAGE_PREFIX = "TICKET";

    /**
     * Sets ticket storage.
     *
     * @param ticketStorage the ticket storage
     */
    public void setTicketStorage(Storage ticketStorage) {
        this.ticketStorage = ticketStorage;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> allTickets = new ArrayList<>();
        ticketStorage.getStorage().keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allTickets.add((Ticket) ticketStorage.getStorage().get(key)));
        return allTickets;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        try {
            ticketStorage.getStorage().remove(removeKey);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed to delete event with id {}", id);
            return false;
        }
    }

    @Override
    public Ticket create(Ticket ticket) {
        String key = STORAGE_PREFIX + ticket.getId();
        return (Ticket) ticketStorage.getStorage().put(key, ticket);
    }
}
