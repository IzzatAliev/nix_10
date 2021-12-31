package ua.com.alevel.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponseDto extends ResponseDto {

    private Account account;
    private Category category;
    private BigDecimal amount;

    public TransactionResponseDto(Transaction transaction) {
        BeanUtils.copyProperties(transaction, this);
    }
}
