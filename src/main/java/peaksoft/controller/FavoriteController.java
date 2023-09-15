package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoFavorite.FavoriteResponse;
import peaksoft.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/{userId}/{productId}")
    public SimpleResponse createFavorite(@PathVariable Long productId, @PathVariable Long userId) {
        return favoriteService.create(userId,productId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getAll/{userId}")
    public List<FavoriteResponse> getAllFavorites(@PathVariable Long userId) {
        return favoriteService.getAll(userId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public FavoriteResponse getFavoriteById(@PathVariable Long id) {
        return favoriteService.getById(id);
    }
    @PutMapping("/{id}/{userId}/{productId}")
    public SimpleResponse updateFavorite(@PathVariable Long id,@PathVariable Long productId, @PathVariable Long userId) {
        return favoriteService.update(id,userId,productId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteFavorite(@PathVariable Long id) {
        return favoriteService.delete(id);
    }
}