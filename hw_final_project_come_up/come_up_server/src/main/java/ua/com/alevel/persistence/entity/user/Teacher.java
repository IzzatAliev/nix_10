package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.course.Course;
import ua.com.alevel.persistence.listener.AgeEntityListener;
import ua.com.alevel.persistence.listener.FullNameEntityListener;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Course> courses;

    public Teacher() {
        super();
        courses = new HashSet<>();
        setRoleType(RoleType.ROLE_TEACHER);
    }
}
