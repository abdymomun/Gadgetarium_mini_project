package peaksoft.service.Impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoFavorite.FavoriteResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Favorite;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.FavoriteService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    @Override
    public SimpleResponse create(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found !"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found !"));

        List<Favorite> favorites = user.getFavorites();
        Favorite existingFavorite = findFavoriteByProductId(favorites, productId);

        if (existingFavorite != null) {
            favorites.remove(existingFavorite);
            favoriteRepository.delete(existingFavorite);
            return new SimpleResponse(HttpStatus.OK, "Removed from favorites");
        } else {
            Favorite newFavorite = new Favorite();
            newFavorite.setUser(user);
            newFavorite.setProduct(product);
            favoriteRepository.save(newFavorite);
            return new SimpleResponse(HttpStatus.OK, "Added to favorites");
        }
    }
    private Favorite findFavoriteByProductId(List<Favorite> favorites, Long productId) {
        for (Favorite favorite : favorites) {
            if (favorite.getProduct().getId().equals(productId)) {
                return favorite;
            }
        }
        return null;
    }
    @Override
    public List<FavoriteResponse> getAll(Long userId) {
        return favoriteRepository.getAllFavorite(userId);
    }

    @Override
    public FavoriteResponse getById(Long id) {
        return favoriteRepository.getFavoriteId(id).orElseThrow(()-> new NotFoundException("Favorite with id: " + id + " not found !"));
    }

    @Override
    public SimpleResponse update(Long id, Long userId, Long productId) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException("Favorite with id: " + id + " not found !"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found !"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found !"));
        favorite.setUser(user);
        user.setFavorites(List.of(favorite));
        product.setFavorites(List.of(favorite));
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
        return new SimpleResponse(HttpStatus.CONTINUE,"aded to favorite");
    }

    @Override
    public SimpleResponse delete(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException("Favorite with id: " + id + " not found !"));
favoriteRepository.delete(favorite);
        return new SimpleResponse(HttpStatus.OK,"Favorite with: " + id + " successfully deleted ! ");
    }
}
