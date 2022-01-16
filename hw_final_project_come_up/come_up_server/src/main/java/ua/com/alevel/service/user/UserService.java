package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.BaseCrudService;

public interface UserService<U extends User> extends BaseCrudService<U> {
}
