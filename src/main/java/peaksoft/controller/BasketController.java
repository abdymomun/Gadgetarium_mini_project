package peaksoft.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoBasket.BasketResponse;
import peaksoft.dto.dtoBasket.BasketResponseGetCountAndSum;
import peaksoft.service.BasketService;

import java.util.List;
@RestController
@RequestMapping("/api/baskets")
public class BasketController {
        private final BasketService basketService;

        @Autowired
        public BasketController(BasketService basketService) {
            this.basketService = basketService;
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @PostMapping("/{productId}")
        public SimpleResponse createBasket(@PathVariable Long productId) {
            return basketService.create( productId);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping
        public List<BasketResponse> getAllBaskets() {
            return basketService.getAll();
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping("/{id}")
        public BasketResponse getBasketById(@PathVariable Long id) {
            return basketService.getById(id);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @PutMapping("/{id}/{productId}")
        public SimpleResponse updateBasket(@PathVariable Long id, @PathVariable Long productId) {
            return basketService.update(id,productId);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @DeleteMapping("/{id}")
        public SimpleResponse deleteBasket(@PathVariable Long id) {
            return basketService.delete(id);
        }
        @GetMapping("/info/{userId}")
        public List<BasketResponseGetCountAndSum>getCountAndSums(@PathVariable Long userId){
            return basketService.getInfo(userId);
        }
//        @GetMapping("/pagination")
//        @PermitAll
//    public ResponseEntity<PaganationResponse> responseResponseEntity(
//            @RequestParam @Min(1) int currentPage,
//            @RequestParam @Min(1) int pageSize
//        )
//        {
//            if (currentPage < 1 || pageSize < 1){
//                return ResponseEntity.badRequest().build();
//            }
//            PaganationResponse response = basketService.getAllPagination(currentPage,pageSize);
//            return ResponseEntity.ok(response);
//        }
}

