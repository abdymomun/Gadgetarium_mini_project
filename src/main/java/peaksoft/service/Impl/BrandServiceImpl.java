package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBrand.BrandRequest;
import peaksoft.dto.dtoBrand.BrandResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Brand;
import peaksoft.models.Product;
import peaksoft.repository.BrandRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.service.BrandService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Override
    public SimpleResponse create(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setBrandName(brandRequest.getBrandName());
        brand.setImage(brandRequest.getImage());
        brandRepository.save(brand);
        return new SimpleResponse(HttpStatus.CREATED,"Brand with id: " + brand.getId() + " successfully saved !");
    }
    @Override
    public List<BrandResponse> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public BrandResponse getById(Long id) {
        return brandRepository.getBrandById(id).orElseThrow(() ->
                new NotFoundException("Brand with id: " + id + " not found !"));
    }

    @Override
    public SimpleResponse update(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Brand with id: " + id + " not found !"));
        brand.setBrandName(brandRequest.getBrandName());
        brand.setImage(brandRequest.getImage());
        brandRepository.save(brand);
        return new SimpleResponse(HttpStatus.ACCEPTED,"Brand with id:"  + id + " successfully updated !");
    }

    @Override
    public SimpleResponse delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Brand with id: " + id + " not found !"));
        brand.getProducts().size();
        brandRepository.delete(brand);
        return new SimpleResponse(HttpStatus.CONTINUE,"Brand with id: " + id + " successfully deleted !");
    }
}
