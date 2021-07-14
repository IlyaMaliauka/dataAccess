package dao.impl;

import dao.TicketDao;
import model.Ticket;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ticket dao.
 */
public class TicketDaoImpl implements TicketDao {

    private Storage ticketStorage;
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
        return ticketStorage.getStorage().remove(removeKey) !=null;
    }

    @Override
    public Ticket create(Ticket ticket) {
        String key = STORAGE_PREFIX + ticket.getId();
        return (Ticket) ticketStorage.getStorage().put(key, ticket);
    }
}
