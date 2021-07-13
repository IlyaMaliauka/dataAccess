package model;

import lombok.Data;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type User.
 */
@Data
public class User {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private long id;
    private String name;
    private String email;

    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param email the email
     */
    public User(String name, String email) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.email = email;
    }
}
