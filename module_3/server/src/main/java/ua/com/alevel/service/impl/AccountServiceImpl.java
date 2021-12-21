package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Account account) {
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
        accountDao.delete(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAllByUserId(Long userId) {
        return accountDao.findAllByUserId(userId);
    }
}
