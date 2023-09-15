package peaksoft.service;


import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoProduct.ProductRequest;
import peaksoft.dto.dtoProduct.ProductResponse;

import java.util.List;

public interface ProductService {
    SimpleResponse create(ProductRequest productRequest,Long brandId);
    List<ProductResponse> getAll();
    ProductResponse getById(Long id);
    SimpleResponse update(Long id,ProductRequest productRequest);
    SimpleResponse delete(Long id);
}
