package peaksoft.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBrand.BrandRequest;
import peaksoft.dto.dtoBrand.BrandResponse;
import peaksoft.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
        private final BrandService brandService;

        @Autowired
        public BrandController(BrandService brandService) {
            this.brandService = brandService;
        }
        @PreAuthorize("hasAuthority('ADMIN')")
        @PostMapping
        public SimpleResponse createBrand(@RequestBody BrandRequest brandRequest) {
            return brandService.create(brandRequest);
        }
        @PermitAll
        @GetMapping
        public List<BrandResponse> getAllBrands() {
            return brandService.getAll();
        }
        @PermitAll
        @GetMapping("/{id}")
        public BrandResponse getBrandById(@PathVariable Long id) {
            return brandService.getById(id);
        }
        @PreAuthorize("hasAuthority('ADMIN')")
        @PutMapping("/{id}")
        public SimpleResponse updateBrand(@PathVariable Long id, @RequestBody BrandRequest brandRequest) {
            return brandService.update(id, brandRequest);
        }
        @PreAuthorize("hasAuthority('ADMIN')")
        @DeleteMapping("/{id}")
        public SimpleResponse deleteBrand(@PathVariable Long id) {
            return brandService.delete(id);
        }
}
