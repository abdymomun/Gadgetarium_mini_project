package peaksoft.service;

import peaksoft.dto.PaganationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBasket.BasketResponse;
import peaksoft.dto.dtoBasket.BasketResponseGetCountAndSum;

import java.util.List;

public interface BasketService {
    SimpleResponse create(Long productId);
    List<BasketResponse> getAll();
    BasketResponse getById(Long id);
    SimpleResponse update(Long id,Long productId);
    SimpleResponse delete(Long id);
    List<BasketResponseGetCountAndSum> getInfo(Long userId);
}
