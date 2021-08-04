package com.epam.training.repository;

import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepo extends CrudRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);

    List<Ticket> findByEvent(Event event);
}
