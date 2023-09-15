package peaksoft.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBasket.BasketResponse;
import peaksoft.dto.dtoBasket.BasketResponseGetCountAndSum;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Basket;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repository.BasketRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.BasketService;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public SimpleResponse create(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email: " + email + " not found !"));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("Product with id: " + productId + " not found !"));

        Basket basket = user.getBasket();

        if (basket == null) {
            basket = new Basket();
            basket.setUser(user);
            basket.setProducts(new ArrayList<>());
        }

        List<Product> products = basket.getProducts();
        products.add(product);

        basketRepository.save(basket);
        userRepository.save(user);
        productRepository.save(product);
        userRepository.save(user);


        return new SimpleResponse(HttpStatus.OK, "Successfully added to basket");
    }

    @Override
    public List<BasketResponse> getAll() {
        return basketRepository.getAllBasket();
    }


    @Override
    public BasketResponse getById(Long id) {
        return basketRepository.getBasketById(id).orElseThrow(() ->
                new NotFoundException("Basket with id: " + id + " not found !"));
    }

    @Override
    public SimpleResponse update(Long id, Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email: " + email + " not found !"));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException(" Product with id: " + productId + " not found !"));
        Basket basket = new Basket();
        basket.setUser(user);
        user.setBasket(basket);
        product.setBaskets(List.of(basket));
        basket.setProducts(List.of(product));
        basketRepository.save(basket);
        return new SimpleResponse(HttpStatus.OK,"Successfully updated ! ");

    }

    @Override
    public SimpleResponse delete(Long id) {
        Basket basket = basketRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Basket with id: " + id + " not found !"));
        basketRepository.delete(basket);
        return new SimpleResponse(HttpStatus.OK,"Basket with " + id + " successfully deleted !");
    }

    @Override
    public List<BasketResponseGetCountAndSum> getInfo(Long userId) {
        String sql = "SELECT b.id, u.first_name, p.name, COUNT(p.id), SUM(p.price) " +
                "FROM baskets b " +
                "JOIN users u ON u.id = b.user_id " +
                "JOIN baskets_products bp ON bp.baskets_id = b.id " +
                "JOIN products p ON p.id = bp.products_id " +
                "WHERE u.id = :userId " +
                "GROUP BY b.id, u.first_name, p.name " +
                "ORDER BY b.id";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);

        List<Object[]> results = query.getResultList();
        Map<Long, BasketResponseGetCountAndSum> resultMap = new HashMap<>();

        for (Object[] result : results) {
            Long basketId = ((Number) result[0]).longValue();
            String userName = (String) result[1];
            String productName = (String) result[2];
            Long countProducts = ((Number) result[3]).longValue();
            BigDecimal sumPrice = (BigDecimal) result[4];

            BasketResponseGetCountAndSum basketResponse = resultMap.get(basketId);
            if (basketResponse == null) {
                basketResponse = new BasketResponseGetCountAndSum(basketId, userName, new ArrayList<>(), 0L, BigDecimal.ZERO);
                resultMap.put(basketId, basketResponse);
            }

            basketResponse.getProducts().add(productName);
            basketResponse.setCountProducts((basketResponse.getCountProducts() + countProducts));
            basketResponse.setSumPrice(basketResponse.getSumPrice().add(sumPrice));
        }

        return new ArrayList<>(resultMap.values());
    }
}

