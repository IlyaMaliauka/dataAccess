package service.impl;

import dao.UserDao;
import model.User;
import service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * Instantiates a new User service.
     *
     * @param userDao the user dao
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Gets user by its id.
     *
     * @param userId
     * @return User.
     */
    @Override
    public User getUserById(long userId) {
        return userDao.getUser(userId);
    }

    /**
     * Gets user by its email. Email is strictly matched.
     *
     * @param email
     * @return User.
     */
    //TODO add logging and adequate exception
    @Override
    public User getUserByEmail(String email) {
        return userDao.getAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     *
     * @param name     Users name or it's part.
     * @param pageSize Pagination param. Number of users to return on a page.
     * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
     * @return List of users.
     */
    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getAll().stream().filter(user -> user.getName().contains(name)).collect(Collectors.toList());
    }

    /**
     * Creates new user. User id should be auto-generated.
     *
     * @param user User data.
     * @return Created User object.
     */
    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    /**
     * Updates user using given data.
     *
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    @Override
    public User updateUser(User user) {
        userDao.delete(user.getId());
        return userDao.create(user);
    }

    /**
     * Deletes user by its id.
     *
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    @Override
    public boolean deleteUser(long userId) {
        return userDao.delete(userId);
    }
}
