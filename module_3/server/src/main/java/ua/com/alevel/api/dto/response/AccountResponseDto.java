package ua.com.alevel.api.dto.response;

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
public class AccountResponseDto extends ResponseDto {

    private String name;
    private BigDecimal balance;
    private UserResponseDto user;

    public AccountResponseDto(Account account) {
        BeanUtils.copyProperties(account, this);
        setId(account.getId());
        setCreated(account.getCreated());
        setUpdated(account.getUpdated());
        setName(account.getName());
        setBalance(account.getBalance());
        setUser(new UserResponseDto(account.getUsers()));
    }
}
