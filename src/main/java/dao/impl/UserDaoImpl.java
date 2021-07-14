package dao.impl;

import dao.UserDao;
import model.User;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User dao.
 */
public class UserDaoImpl implements UserDao {

    private final String STORAGE_PREFIX = "USER";
    private Storage userStorage;

    /**
     * Sets storage.
     *
     * @param userStorage the storage
     */
    public void setUserStorage(Storage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        userStorage.getStorage().keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allUsers.add((User) userStorage.getStorage().get(key)));
        return allUsers;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        return userStorage.getStorage().remove(removeKey) != null;
    }

    @Override
    public User create(User user) {
        String key = STORAGE_PREFIX + user.getId();
        return (User) userStorage.getStorage().put(key, user);
    }

    @Override
    public User getUser(long id) {
        String key = STORAGE_PREFIX + id;
        return (User) userStorage.getStorage().get(key);
    }
}
