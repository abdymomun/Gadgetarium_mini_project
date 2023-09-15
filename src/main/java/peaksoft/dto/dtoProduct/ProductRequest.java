package peaksoft.dto.dtoProduct;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;
@Getter @Setter
public class ProductRequest {
    private String name;
    private BigDecimal price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    private Category category;

}
