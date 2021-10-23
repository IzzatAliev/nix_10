package ua.com.alevel.service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void create(User user) {
        UserDao.create(user);
    }

    public void update(User user) {
        UserDao.update(user);
    }

    public void delete(String id) {
        UserDao.delete(id);
    }

    public User findById(String id) {
        return UserDao.findById(id);
    }

    public User[] findAll() {
        return UserDao.findAll();
    }
}
