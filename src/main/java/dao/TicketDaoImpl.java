package dao;

import model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Ticket dao.
 */
@Component
public class TicketDaoImpl extends BaseDao implements TicketDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);
    private static final String STORAGE_PREFIX = "TICKET";
    private Map<String, Object> ticketStorage = storage.getStorage();

    @Override
    public List<Ticket> getAll() {
        List<Ticket> allTickets = new ArrayList<>();
        ticketStorage.keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allTickets.add((Ticket) ticketStorage.get(key)));
        return allTickets;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        try {
            ticketStorage.remove(removeKey);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed to delete event with id {}", id);
            return false;
        }
    }

    @Override
    public Ticket create(Ticket ticket) {
        String key = STORAGE_PREFIX + ticket.getId();
        return (Ticket) ticketStorage.put(key, ticket);
    }
}
