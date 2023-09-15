package peaksoft.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoProduct.ProductRequest;
import peaksoft.dto.dtoProduct.ProductResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Brand;
import peaksoft.models.Comment;
import peaksoft.models.Product;
import peaksoft.repository.BrandRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.service.ProductService;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final EntityManager entityManager;


    @Override
    public SimpleResponse create(ProductRequest productRequest, Long brandId) {
        Product product = new Product();
        Brand brand = brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Brand with id: " + brandId + " not found !"));
        product.setName(productRequest.getName());
        product.setImages(productRequest.getImages());
        product.setFavorite(productRequest.isFavorite());
        product.setCategory(productRequest.getCategory());
        product.setCharacteristic(productRequest.getCharacteristic());
        product.setPrice(productRequest.getPrice());
        product.setMadeIn(productRequest.getMadeIn());
        product.setBrand(brand);
        brand.setProducts(List.of(product));
        productRepository.save(product);
        return new SimpleResponse(HttpStatus.OK, "Product with id: " + product.getId() + " successfully saved ! ");
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAllByImages();
    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id:" + id + " not found !"));
        ProductResponse productResponse = new ProductResponse();

        Long favoriteCount = (Long) entityManager.createQuery("SELECT COUNT(f.id) FROM Favorite f WHERE f.product.id = :productId")
                .setParameter("productId", id)
                .getSingleResult();


        List<String> commentList = new ArrayList<>();
        List<Comment> comments = product.getComments();
        for (Comment comment : comments) {
            commentList.add(comment.getComment());
        }
        productResponse.setComment(commentList);

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setImages(product.getImages());
        productResponse.setFavorite(product.isFavorite());
        productResponse.setCategory(product.getCategory());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setPrice(product.getPrice());
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setBrandName(product.getBrand().getBrandName());
        productResponse.setCountFavorite(favoriteCount.intValue());
        return productResponse;
    }

    @Override
    public SimpleResponse update(Long id, ProductRequest newProduct) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " + id + " not found !"));
        oldProduct.setName(newProduct.getName());
        oldProduct.setImages(newProduct.getImages());
        oldProduct.setFavorite(newProduct.isFavorite());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setCharacteristic(newProduct.getCharacteristic());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setMadeIn(newProduct.getMadeIn());
        productRepository.save(oldProduct);

        return new SimpleResponse(HttpStatus.OK, "Product with id: " + id + " successfully updated !");
    }

    @Override
    public SimpleResponse delete(Long id) {
        productRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "Product with id: " + id + " successfully deleted !");
    }


//    @Override
//    public PaganationResponse getAllPagination(int currentPage, int pageSize) {
//            Pageable pageable =  PageRequest.of(currentPage -1 , pageSize);
//            Page<ProductResponse> basketResponses = productRepository.getAllByImages(pageable);
//            return PaganationResponse.builder()
//                    .t(Collections.singletonList(basketResponses.getContent()))
//                    .currentPage(basketResponses.getNumber())
//                    .pageSize(basketResponses.getTotalPages())
//                    .build();
//        }

    }

