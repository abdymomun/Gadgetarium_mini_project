package peaksoft.dto.dtoBasket;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import peaksoft.models.User;

import java.util.List;
@Getter @Setter
public class BasketRequest {
    private List<Product> products;
    private User user;
}
