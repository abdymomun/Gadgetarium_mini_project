package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.dtoBasket.BasketResponse;
import peaksoft.models.Basket;
import peaksoft.models.Product;
import peaksoft.models.User;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select new peaksoft.dto.dtoBasket.BasketResponse(b.id,u.firstName,p.name)" +
            " from Basket b join User u on u.id = b.user.id join b.products p ")
    List<BasketResponse>getAllBasket();



    @Query("select new peaksoft.dto.dtoBasket.BasketResponse(b.id,u.firstName,p.name)" +
            " from Basket b join User u on u.id = b.user.id join b.products p where b.id =:id")
    Optional<BasketResponse> getBasketById(@Param("id") Long id);
  //  Optional<Basket> findByUserAndProduct(User user, Product product);
  @Query("SELECT b FROM Basket b WHERE b.user = :user AND b.products = :product")
  Optional<Basket> findBasketByUserAndProduct(@Param("user") User user, @Param("product") Product product);
//    @Query("select new peaksoft.dto.dtoBasket.BasketResponse(b.id,u.firstName,p.name)" +
//            " from Basket b join User u on u.id = b.user.id join b.products p ")
//    Page<BasketResponse> getAllBeskets(Pageable pageable);
}