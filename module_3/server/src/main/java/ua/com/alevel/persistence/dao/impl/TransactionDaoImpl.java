package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TransactionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Transaction transaction) {
        entityManager.merge(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        entityManager.merge(transaction);
    }

    @Override
    public void delete(Transaction transaction) {entityManager.remove(transaction);}

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Transaction t where t.id = :id")
                .setParameter("id", id);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(t.id) from Transaction t where t.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Transaction findById(Long id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public List<Transaction> findAll() {
        return entityManager.createQuery("select t from Transaction t", Transaction.class).getResultList();
    }

    @Override
    public List<Transaction> findAllByAccountId(Long accountId) {
        return entityManager.createQuery("select t from Transaction as t where t.accounts = :accountId", Transaction.class)
                .setParameter("accountId", accountId).getResultList();
    }

    @Override
    public List<Transaction> findAllByCategoryId(Long categoryId) {
        return entityManager.createQuery("select t from Transaction as t where t.categories = :categoryId", Transaction.class)
                .setParameter("categoryId", categoryId).getResultList();
    }
}
