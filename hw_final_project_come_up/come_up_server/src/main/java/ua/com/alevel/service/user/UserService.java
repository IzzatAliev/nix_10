package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.BaseCrudService;

public interface UserService<U extends User> extends BaseCrudService<U> {

    void flush();
    boolean existsById(Long id);
    U findByEmail(String email);
    boolean existsByEmail(String email);
}
