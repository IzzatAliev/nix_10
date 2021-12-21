package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.type.CourseType;

@Getter
@Setter
@ToString
public class CourseRequestDto extends RequestDto {

    private String courseName;
    private CourseType courseType ;
    private Integer credit;
    private String description;
}
