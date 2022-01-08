package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Account account) {
        LOGGER_INFO.info("create the new account: " + account.getName());
        accountDao.create(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Account account) {
        LOGGER_WARN.warn("delete account: " + account.getName());
        accountDao.delete(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        LOGGER_WARN.warn("delete account by id: " + id);
        accountDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        LOGGER_INFO.info("find account by id: " + id);
        return accountDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        LOGGER_INFO.info("find all accounts");
        return accountDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAllByUser(User user) {
        LOGGER_INFO.info("find All Accounts by User: " + user);
        return accountDao.findAllByUser(user);
    }
}
