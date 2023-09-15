package peaksoft.dto.dtoFavorite;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import peaksoft.models.User;
@Getter @Setter
public class FavoriteRequest {
    private User user;
    private Product product;
}
