package ua.com.alevel.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.type.CourseType;

import java.util.Optional;

@Getter
@Setter
@ToString
public class CourseResponseDto extends ResponseDto {

    private String courseName;
    private Integer credit;
    private CourseType courseType ;
    private String description;
    private Integer studentCount;

    public CourseResponseDto() { }

    public CourseResponseDto(Course course) {
        this();
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        this.courseName = course.getCourseName();
        this.credit = course.getCredit();
        this.courseType = course.getCourseType();
        this.description = course.getDescription();
    }
}
