import model.User;
import model.impl.UserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class Test {

    @org.junit.Test
    public void sampleTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean("userService", UserService.class);
        User user = context.getBean("testUser", UserImpl.class);
        userService.createUser(user);
        System.out.println(userService.getUserByEmail("Email"));
    }
}
