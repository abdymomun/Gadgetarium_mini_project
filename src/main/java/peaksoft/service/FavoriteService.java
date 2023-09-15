package peaksoft.service;

import peaksoft.dto.SimpleResponse;

import peaksoft.dto.dtoFavorite.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    SimpleResponse create(Long userId, Long productId);
    List<FavoriteResponse> getAll(Long userId);
    FavoriteResponse getById(Long id);
    SimpleResponse update(Long id,Long userId, Long productId);
    SimpleResponse delete(Long id);

}
