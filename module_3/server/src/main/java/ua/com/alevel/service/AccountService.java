package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.List;

public interface AccountService extends BaseService<Account> {

    List<Account> findAllByUser(User user);
}
