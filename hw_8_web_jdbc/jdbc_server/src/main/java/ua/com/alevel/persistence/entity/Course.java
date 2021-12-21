package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.type.CourseType;

@Getter
@Setter
public class Course extends BaseEntity {

    private String courseName;
    private Integer credit;
    private CourseType courseType ;
    private String description;

    public Course(){
        super();
    }
}
