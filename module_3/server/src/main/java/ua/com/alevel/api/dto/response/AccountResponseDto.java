package ua.com.alevel.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto extends ResponseDto {

    private String name;
    private BigDecimal balance;
    private User user;

    public AccountResponseDto(Account account){
        BeanUtils.copyProperties(account, this);
    }
}
