package ru.sellersp.api.modules;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.jvnet.hk2.annotations.Service;
import ru.sellersp.api.beans.User;

import javax.inject.Named;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Sanle on 09.05.2017.
 */

public class Users {
    private Map<String, User> userMap = new HashMap<>();
    private User user = null;

    public void addUser(String token, User user) {
        this.userMap.put(token, user);
    }

    public User getUser(String token) {
        return this.userMap.get(token);
    }

    public User getUserByID(int id) {
        for (User user : userMap.values()) {
            if (user.getUserVkId() == id) {
                this.user = user;
                break;
            }
        }
        return user;
    }
}
