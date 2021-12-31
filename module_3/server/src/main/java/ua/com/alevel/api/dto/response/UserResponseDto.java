package ua.com.alevel.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ua.com.alevel.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;

    public UserResponseDto(User user){
        BeanUtils.copyProperties(user, this);
    }
}
