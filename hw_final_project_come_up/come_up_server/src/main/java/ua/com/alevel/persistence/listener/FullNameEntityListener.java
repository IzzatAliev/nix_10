package ua.com.alevel.persistence.listener;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.persistence.entity.user.BaseInfo;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

public class FullNameEntityListener<E extends BaseInfo> {

    @PostLoad
    @PostPersist
    @PreUpdate
    public void generateFullName(E entity) {
        if (StringUtils.isNotBlank(entity.getFirstName()) && StringUtils.isNotBlank(entity.getLastName())) {
            entity.setFullName(entity.getFirstName() + " " + entity.getLastName());
        }
    }
}
