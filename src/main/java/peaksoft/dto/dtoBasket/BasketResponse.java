package peaksoft.dto.dtoBasket;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import peaksoft.models.User;
import java.util.List;
@Getter @Setter
public class BasketResponse {
    private Long id;
    private String userName;
    private String products;

    public BasketResponse() {
    }

    public BasketResponse(Long id, String userName, String products) {
        this.id = id;
        this.userName = userName;
        this.products = products;
    }
}
