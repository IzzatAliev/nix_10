package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final TransactionDao transactionDao;
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Transaction transaction) {
//        Transaction tran = new Transaction();
//        tran.setAccounts(transaction.getAccounts());
//        tran.setCategories(transaction.getCategories());
//        tran.setAmount(transaction.getAmount());
        LOGGER_INFO.info("create the new transaction: " + transaction.getAccounts() + " " + transaction.getCategories() + " " + transaction.getAmount());
        transactionDao.create(transaction);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Transaction transaction) {
        transactionDao.update(transaction);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Transaction transaction) {
        transactionDao.delete(transaction);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        transactionDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        LOGGER_INFO.info("find transaction by id: " + id);
        return transactionDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        LOGGER_INFO.info("find all transactions");
        return transactionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByAccountId(Long accountId) {
        LOGGER_INFO.info("find all transactions by account id: " + accountId);
        return transactionDao.findAllByAccountId(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByCategoryId(Long categoryId) {
        LOGGER_INFO.info("find all transactions by category id: " + categoryId);
        return transactionDao.findAllByCategoryId(categoryId);
    }
}
