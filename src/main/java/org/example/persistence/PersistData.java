package org.example.persistence;

import org.example.exception.InvalidPurchaseException;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

public class PersistData {

    /*
        For test purposes I have created this class to act as a way to persist hardcoded users
     */

    public PersistData() {
    }

    public List<User> users = new ArrayList<>();

    public List<User> createUsers() {
        users.add(new User(1L,1000));
        users.add(new User(-1L,100));
        users.add(new User(2L,0));
        users.add(new User(3L,400));

        return users;
    }

    public User findUser(List<User> users, Long id) {
        for (User user: users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new InvalidPurchaseException("User does not exist");
    }
}
