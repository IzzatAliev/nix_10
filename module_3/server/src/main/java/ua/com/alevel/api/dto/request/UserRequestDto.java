package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto extends RequestDto{

    private String firstName;
    private String lastName;
}
