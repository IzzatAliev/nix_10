package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.listener.AgeEntityListener;
import ua.com.alevel.persistence.listener.FullNameEntityListener;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@Getter
@Setter
@DiscriminatorValue("TEACHER")
@EntityListeners({
        FullNameEntityListener.class,
        AgeEntityListener.class})
public class Teacher extends BaseInfo {

    private String competence;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    public Teacher() {
        super();
        setRoleType(RoleType.ROLE_TEACHER);
    }
}
