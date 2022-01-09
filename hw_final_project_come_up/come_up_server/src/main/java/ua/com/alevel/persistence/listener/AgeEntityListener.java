package ua.com.alevel.persistence.listener;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import ua.com.alevel.persistence.entity.user.BaseInfo;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

public class AgeEntityListener<E extends BaseInfo> {

    @PostLoad
    @PostPersist
    @PreUpdate
    public void generateAge(E entity) {
        if (entity.getBirthDay() != null) {
            Years years = Years.yearsBetween(new LocalDate(entity.getBirthDay()), new LocalDate());
            entity.setAge(years.getYears());
        }
    }
}
