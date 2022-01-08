package ua.com.alevel.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean income;

    @OneToMany(mappedBy="categories", fetch= FetchType.LAZY)
    @ToString.Exclude
    private Set<Transaction> transactions;

    public Category() {
        super();
        this.transactions = new HashSet<>();
    }
}
