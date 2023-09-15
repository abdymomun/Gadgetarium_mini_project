package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBrand.BrandRequest;
import peaksoft.dto.dtoBrand.BrandResponse;

import java.util.List;

public interface BrandService {
    SimpleResponse create(BrandRequest brandRequest);
    List<BrandResponse> getAll();
    BrandResponse getById(Long id);
    SimpleResponse update(Long id,BrandRequest brandRequest);
    SimpleResponse delete(Long id);

}
