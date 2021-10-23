package ua.com.alevel.dao;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;

public class UserDao {

    private final UserDB userDB = new UserDB();

    public static void create(User user) {
        UserDB.create(user);
    }

    public static void update(User user) {
        UserDB.update(user);
    }

    public static void delete(String id) {
        UserDB.delete(id);
    }

    public static User findById(String id) {
        return UserDB.findById(id);
    }

    public static User[] findAll() {
        return UserDB.findAll();
    }
}
