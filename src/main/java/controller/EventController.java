package controller;

import lombok.extern.slf4j.Slf4j;
import model.Event;
import model.impl.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.EventService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ModelAndView createEvent(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = new EventImpl(title, parseDate(date));
        modelAndView.addObject("entities", event);
        modelAndView.addObject("message", "create entities");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateEvent(@PathVariable long id,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event oldEvent = eventService.getEventById(id);
        if (Objects.nonNull(oldEvent)) {
            Event newEvent = new EventImpl(title, parseDate(date));
            newEvent = eventService.updateEvent(oldEvent, newEvent);
            modelAndView.addObject("entities", newEvent);
            modelAndView.addObject("message", "update entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteEvent(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = eventService.delete(id);
        if (isDeleted) {
            modelAndView.addObject("message", "delete entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEventById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = eventService.getEventById(id);
        if (Objects.nonNull(event)) {
            modelAndView.addObject("entities", event);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title,
                                         @RequestParam(required = false, defaultValue = "100") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = eventService.getEventsByTitle(title, pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/date/{date}")
    public ModelAndView getEventsByDate(@PathVariable String date,
                                        @RequestParam(required = false, defaultValue = "100") int pageSize,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = eventService.getEventsForDay(parseDate(date), pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    private Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}