package com.epam.training.controller;

import lombok.extern.slf4j.Slf4j;
import com.epam.training.model.Category;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.epam.training.service.EventService;
import com.epam.training.service.TicketService;
import com.epam.training.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    private UserService userService;
    private EventService eventService;

    @PostMapping
    public ModelAndView bookTicket(@RequestParam long userId,
                                   @RequestParam long eventId,
                                   @RequestParam int place,
                                   @RequestParam Category category) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);
        if (Objects.nonNull(event) && Objects.nonNull(user)) {
            Ticket ticket = ticketService.bookTicket(user, event, place, category);
            modelAndView.addObject("entities", ticket);
            modelAndView.addObject("message", "ticket booked");
        } else {
            modelAndView.addObject("message", "ticket not booked");
        }
        return modelAndView;
    }

    @GetMapping(value = "/{entity}/{id}")
    public ModelAndView getBookedTickets(@PathVariable long id,
                                         @PathVariable String entity,
                                         @RequestParam(required = false, defaultValue = "100") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Ticket> tickets = getTickets(id, entity, pageSize, pageNum);
        if (!tickets.isEmpty()) {
            modelAndView.addObject("entities", tickets);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView cancelTicket(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = ticketService.cancelTicket(id);
        if (isDeleted) {
            modelAndView.addObject("message", "delete entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    private List<Ticket> getTickets(long id, String entity, int pageSize, int pageNum) {
        List<Ticket> tickets = new ArrayList<>();
        if ("users".equalsIgnoreCase(entity)) {
            User user = userService.getUserById(id);
            tickets = ticketService.getBookedTickets(user, pageSize, pageNum);
        }
        if ("events".equalsIgnoreCase(entity)) {
            Event event = eventService.getEventById(id);
            tickets = ticketService.getBookedTickets(event, pageSize, pageNum);
        }
        return tickets;
    }

}