package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_seq")
    @SequenceGenerator(name = "brand_seq",allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String brandName;
    private String image;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product>products;
}