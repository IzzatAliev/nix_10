package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Account account) {
        entityManager.merge(account);
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }

    @Override
    public void delete(Account account) {
        entityManager.remove(account);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Account a where a.id = :id")
                .setParameter("id", id);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(a.id) from Account a where a.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> findAll() {
        return entityManager.createQuery("select a from Account a", Account.class).getResultList();
    }

    @Override
    public List<Account> findAllByUser(User user) {
        return entityManager.createQuery("select a from Account a where a.users = :user", Account.class)
                .setParameter("user", user).getResultList();
    }
}
