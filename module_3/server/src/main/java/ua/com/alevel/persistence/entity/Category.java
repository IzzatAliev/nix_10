package ua.com.alevel.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@ToString
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean income;

    @OneToMany(mappedBy="categories", fetch= FetchType.LAZY)
    private Set<Transaction> transactions;

    public Category() {
        super();
        this.transactions = new HashSet<>();
    }
}
