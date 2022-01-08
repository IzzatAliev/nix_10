package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {

    List<Account> findAllByUser(User user);
}
