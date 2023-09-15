package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoFavorite.FavoriteResponse;
import peaksoft.models.Favorite;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select new peaksoft.dto.dtoFavorite.FavoriteResponse(f.id,u.firstName,p.name)from Favorite f" +
            " join User u on u.id = f.user.id join  Product  p on p.id = f.product.id  where u.id =:userId")
    List<FavoriteResponse> getAllFavorite(@Param("userId") Long userId);
    @Query("select new peaksoft.dto.dtoFavorite.FavoriteResponse(f.id,u.firstName,p.name)from Favorite f" +
            " join User u on u.id = f.user.id join  Product  p on p.id = f.product.id  where f.id =:id")
    Optional<FavoriteResponse>getFavoriteId(@Param("id") Long id);
}