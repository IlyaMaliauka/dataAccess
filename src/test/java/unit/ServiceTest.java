package unit;

import model.Event;
import model.Ticket;
import model.User;
import model.impl.UserImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import service.impl.EventServiceImpl;
import service.impl.TicketServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceTest {

    private static UserServiceImpl userService;
    private static EventServiceImpl eventService;
    private static TicketServiceImpl ticketService;
    private User testUser;
    private Event testEvent;
    private Ticket testTicket;

    @BeforeClass
    public static void init() {
        userService = Mockito.mock(UserServiceImpl.class);
        eventService = Mockito.mock(EventServiceImpl.class);
        ticketService = Mockito.mock(TicketServiceImpl.class);
    }

    @Test
    public void createUserTest() {
        testUser = new UserImpl("Ivan", "ivan@mail.ru");
        Mockito.when(userService.getUserById(1)).thenReturn(testUser);
        String testName = userService.getUserById(1).getName();
        Assert.assertEquals("Ivan", testName);
    }
}
