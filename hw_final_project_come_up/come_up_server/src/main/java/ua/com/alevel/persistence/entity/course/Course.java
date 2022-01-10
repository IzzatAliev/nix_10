package ua.com.alevel.persistence.entity.course;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.Student;
import ua.com.alevel.persistence.entity.user.Teacher;
import ua.com.alevel.persistence.type.CourseCategory;
import ua.com.alevel.persistence.type.CourseLevel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column(name = "course_name")
    private String courseName;

    @Column(columnDefinition = "TEXT")
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_level")
    private CourseLevel courseLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_category")
    private CourseCategory courseCategory;

    public Course() {
        super();
        students = new HashSet<>();
    }
}
