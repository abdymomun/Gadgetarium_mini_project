package peaksoft.dto.dtoBasket;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BasketResponseGetCountAndSum {
    private Long id;
    private String userName;
    private List<String> products;
    private Long countProducts;
    private BigDecimal sumPrice;

    public BasketResponseGetCountAndSum(Long id, String userName, List<String> products, Long countProducts, BigDecimal sumPrice) {
        this.id = id;
        this.userName = userName;
        this.products = products;
        this.countProducts = countProducts;
        this.sumPrice = sumPrice;
    }
}
