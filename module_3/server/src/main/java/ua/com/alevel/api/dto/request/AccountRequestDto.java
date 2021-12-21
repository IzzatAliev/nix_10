package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDto extends RequestDto {

    private String name;
    private Long userId;
    private BigDecimal balance;
}
