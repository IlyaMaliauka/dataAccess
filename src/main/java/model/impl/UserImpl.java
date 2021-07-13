package model.impl;

import lombok.Data;
import model.User;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type User.
 */
@Data
public class UserImpl implements User {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private long id;
    private String name;
    private String email;

    /**
     * Instantiates a new User.
     *
     * @param name  the name
     * @param email the email
     */
    public UserImpl(String name, String email) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.email = email;
    }
}
