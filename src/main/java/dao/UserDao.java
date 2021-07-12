package dao;

import model.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao extends Dao<User> {

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    User getUser(long id);
}
