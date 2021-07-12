import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class Test {

    @org.junit.Test
    public void sampleTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = context.getBean(UserService.class);
        User user = new User("Ivan", "Email");
        userService.createUser(user);
        System.out.println(userService.getUserByEmail("Email"));
    }
}
