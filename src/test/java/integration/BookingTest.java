package integration;

import model.Category;
import model.Event;
import model.Ticket;
import model.User;
import model.impl.EventImpl;
import model.impl.TicketImpl;
import model.impl.UserImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.impl.EventServiceImpl;
import service.impl.TicketServiceImpl;
import service.impl.UserServiceImpl;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookingTest {

    private static UserServiceImpl userService;
    private static TicketServiceImpl ticketService;
    private static EventServiceImpl eventService;
    private static User testUser;
    private static Event testEvent;
    private static Ticket testTicket;

    @BeforeClass
    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        userService = context.getBean("userService", UserServiceImpl.class);
        ticketService = context.getBean("ticketService", TicketServiceImpl.class);
        eventService = context.getBean("eventService", EventServiceImpl.class);
    }

    @Test
    public void createUser() {
        testUser = new UserImpl("ivan", "ivan@mail.ru");
        userService.createUser(testUser);
        assertThat(testUser).isEqualTo(userService.getUserById(testUser.getId()));
    }

    @Test
    public void createEvent() {
        testEvent = new EventImpl("Swan Lake", new Date());
        eventService.create(testEvent);
        assertThat(testEvent.getTitle()).isEqualTo("Swan Lake");
    }

    @Test
    public void bookAndCancelTicket() {
        testTicket = ticketService.bookTicket(testUser.getId(), testEvent.getId(), 35, Category.BAR);
        assertThat(ticketService.getBookedTickets(testUser,1, 1)).isEqualTo(1);
        assertThat(ticketService.getBookedTickets(testEvent, 1,1)).isEqualTo(1);

        ticketService.cancelTicket(testTicket.getId());

        assertThat(ticketService.getBookedTickets(testUser, 1,1)).isEqualTo(0);
        assertThat(ticketService.getBookedTickets(testEvent, 1,1)).isEqualTo(0);
    }
}
