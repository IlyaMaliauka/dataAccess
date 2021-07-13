import model.User;
import model.impl.UserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;
import org.junit.*;

/**
 * The type Test.
 */
public class Test {
    /**
     * Sample test.
     */
    @org.junit.Test
    public void sampleTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean("userService", UserService.class);
        User user = context.getBean("testUser", UserImpl.class);
        userService.createUser(user);
        Assert.assertEquals(user, userService.getUserByEmail("ivan@mail.ru"));
    }
}
