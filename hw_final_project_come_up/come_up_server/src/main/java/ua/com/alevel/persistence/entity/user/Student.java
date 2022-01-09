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
@DiscriminatorValue("STUDENT")
@EntityListeners({
        FullNameEntityListener.class,
        AgeEntityListener.class})
public class Student extends BaseInfo {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "students")
    private Set<Course> courses;

    public Student() {
        super();
        courses = new HashSet<>();
        setRoleType(RoleType.ROLE_STUDENT);
    }
}
