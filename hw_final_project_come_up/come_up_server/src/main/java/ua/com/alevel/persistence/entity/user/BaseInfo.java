package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseInfo extends User {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "image_url")
    private String imageUrl;

    @Transient
    private Integer age;
}
