package peaksoft.dto.dtoBrand;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import java.util.List;
@Getter@Setter
public class BrandResponse {
    private Long id;
    private String brandName;
    private String image;
    private List<Product> products;

    public BrandResponse(Long id, String brandName, String image) {
        this.id = id;
        this.brandName = brandName;
        this.image = image;
    }
}

