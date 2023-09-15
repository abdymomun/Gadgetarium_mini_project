package peaksoft.dto.dtoFavorite;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import peaksoft.models.User;
@Getter @Setter
public class FavoriteResponse {
    private Long id;
    private String userName;
    private String productName;

    public FavoriteResponse() {
    }

    public FavoriteResponse(Long id, String userName, String productName) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
    }
}
