package ua.com.alevel.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.Student;

import java.util.Optional;

@Getter
@Setter
@ToString
public class StudentResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer courseCount;

    public StudentResponseDto() { }

    public StudentResponseDto(Student student) {
       setId(student.getId());
       setCreated(student.getCreated());
       setUpdated(student.getUpdated());
       this.firstName = student.getFirstName();
       this.lastName = student.getLastName();
       this.age = student.getAge();
    }
}
