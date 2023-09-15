package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_seq")
    @SequenceGenerator(name = "basket_seq",allocationSize = 1)
    private Long id;
    @ManyToMany
    private List<Product>products;
    @OneToOne
    private User user;
}