package ua.com.alevel.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "account_id", nullable = false)
    private Account accounts;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "category_name", nullable = false)
    private Category categories;

    @Column(nullable = false)
    private BigDecimal amount;

    public Transaction() {
        super();
    }
}
