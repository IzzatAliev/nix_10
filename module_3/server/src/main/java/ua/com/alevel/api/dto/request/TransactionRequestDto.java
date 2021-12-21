package ua.com.alevel.api.dto.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDto extends RequestDto {

    private Account accountId;
    private Category categoryId;
    private BigDecimal amount;
}
