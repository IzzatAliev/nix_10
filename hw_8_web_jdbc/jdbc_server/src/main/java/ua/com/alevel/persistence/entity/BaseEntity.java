package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {

    private Long id;
    private Date created;
    private Date updated;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
    }
}
