package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(User user) {
        LOGGER_INFO.info("created the new user: " + user.getFirstName() + " " + user.getLastName());
        userDao.create(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(User user) {
        LOGGER_WARN.warn("delete user: " + user.getFirstName()+ " " + user.getLastName());
        userDao.delete(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        LOGGER_WARN.warn("delete user by id: " + id);
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        LOGGER_INFO.info("find user by id: " + id);
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        LOGGER_INFO.info("find all users");
        return userDao.findAll();
    }
}
