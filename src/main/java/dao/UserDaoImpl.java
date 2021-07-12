package dao;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type User dao.
 */
@Component
public class UserDaoImpl extends BaseDao implements UserDao {

    private final String STORAGE_PREFIX = "USER";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private Map<String, Object> userStorage = storage.getStorage();

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        userStorage.keySet().stream()
                .filter(key -> key.contains(STORAGE_PREFIX))
                .forEach(key -> allUsers.add((User) userStorage.get(key)));
        return allUsers;
    }

    @Override
    public boolean delete(long id) {
        String removeKey = STORAGE_PREFIX + id;
        try {
            userStorage.remove(removeKey);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed to delete event with id {}", id);
            return false;
        }
    }

    @Override
    public User create(User user) {
        String key = STORAGE_PREFIX + user.getId();
        return (User) userStorage.put(key, user);
    }

    @Override
    public User getUser(long id) {
        String key = STORAGE_PREFIX + id;
        return (User) userStorage.get(key);
    }
}
