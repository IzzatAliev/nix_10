package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDto extends RequestDto {

    private String name;
    private long userId;
    private BigDecimal balance;
}
