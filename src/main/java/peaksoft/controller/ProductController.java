package peaksoft.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.PaganationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoProduct.ProductRequest;
import peaksoft.dto.dtoProduct.ProductResponse;
import peaksoft.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{brandId}")
    public SimpleResponse createProduct(@RequestBody ProductRequest productRequest,@PathVariable Long brandId) {
        return productService.create(productRequest,brandId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }
    @PermitAll
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }
//    @GetMapping("/pagination")
//    @PermitAll
//    public ResponseEntity<PaganationResponse> responseResponseEntity(
//            @RequestParam @Min(1) int currentPage,
//            @RequestParam @Min(1) int pageSize
//    )
//    {
//        if (currentPage < 1 || pageSize < 1){
//            return ResponseEntity.badRequest().build();
//        }
//        PaganationResponse response =productService.getAllPagination(currentPage,pageSize);
//        return ResponseEntity.ok(response);
//    }
}
