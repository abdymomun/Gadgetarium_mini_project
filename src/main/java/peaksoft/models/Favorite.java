package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_seq")
    @SequenceGenerator(name = "favorite_seq",allocationSize = 1)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}