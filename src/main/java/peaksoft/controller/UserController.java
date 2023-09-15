package peaksoft.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoAuthorization.AuthenticationResponse;
import peaksoft.dto.dtoAuthorization.SignInRequest;
import peaksoft.dto.dtoAuthorization.SignUpRequest;
import peaksoft.dto.dtoUser.UserRequest;
import peaksoft.dto.dtoUser.UserResponse;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PermitAll
    @PostMapping("/signUp")
    public AuthenticationResponse signUpUser(@RequestBody SignUpRequest userRequest) {
        return userService.signUp(userRequest);
    }
    @PostMapping("/signIn")
    public AuthenticationResponse signInUser(@RequestBody SignInRequest signInRequest){
        return userService.signIn(signInRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PutMapping("/{id}")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/{userId}/{productId}")
    public SimpleResponse addFavorite(@PathVariable Long productId, @PathVariable Long userId){
        return userService.favorite(userId,productId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("/fav/{favId}")
    public SimpleResponse deleteFavorite(@PathVariable Long favId){
        return userService.deleteIsFavorite(favId);
    }

}
