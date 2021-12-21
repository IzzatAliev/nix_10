package ua.com.alevel.service.impl;

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

    private final TransactionDao transactionDao;

    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Transaction transaction) {
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
        return transactionDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByAccountId(Long accountId) {
        return transactionDao.findAllByAccountId(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllByCategoryId(Long categoryId) {
        return transactionDao.findAllByCategoryId(categoryId);
    }
}
