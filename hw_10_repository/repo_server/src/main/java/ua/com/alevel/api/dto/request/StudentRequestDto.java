package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private Integer age;
}
