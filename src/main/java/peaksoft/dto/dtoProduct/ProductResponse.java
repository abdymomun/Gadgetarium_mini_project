package peaksoft.dto.dtoProduct;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<String> images;
    private String characteristic;
    private boolean isFavorite;
    private String madeIn;
    private Category category;
    private String brandName;
    private List<String> comment;
    private int countFavorite;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, BigDecimal price, List<String> images, String characteristic, boolean isFavorite, String madeIn, Category category, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brandName = brandName;
    }

    public ProductResponse(Long id, String name, BigDecimal price, List<String> images, String characteristic, boolean isFavorite, String madeIn, Category category, String brandName, List<String> comment, int countFavorite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brandName = brandName;
        this.comment = comment;
        this.countFavorite = countFavorite;
    }

    public ProductResponse(int countFavorite) {
        this.countFavorite = countFavorite;
    }
}
