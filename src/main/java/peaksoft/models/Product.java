package peaksoft.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq",allocationSize = 1)
    private Long id;
    private String name;
    private BigDecimal price;
    @ElementCollection
    private List<String>images = new ArrayList<>();
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Comment>comments;
    @OneToMany(mappedBy = "product")
    private List<Favorite>favorites;
    @ManyToOne
    private Brand brand;
    @ManyToMany(mappedBy = "products")
    private List<Basket> baskets;
}