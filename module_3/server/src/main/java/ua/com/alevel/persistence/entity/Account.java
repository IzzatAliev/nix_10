package ua.com.alevel.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@ToString
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "user_id", nullable = false)
    private User users;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToMany(mappedBy="accounts", fetch= FetchType.LAZY)
    private Set<Transaction> transactions;

    public Account() {
        super();
        this.transactions = new HashSet<>();
    }
}
