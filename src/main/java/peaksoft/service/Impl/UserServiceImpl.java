package peaksoft.service.Impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoAuthorization.AuthenticationResponse;
import peaksoft.dto.dtoAuthorization.SignInRequest;
import peaksoft.dto.dtoAuthorization.SignUpRequest;
import peaksoft.dto.dtoUser.UserRequest;
import peaksoft.dto.dtoUser.UserResponse;
import peaksoft.enums.Role;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.BadCredentialException;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Favorite;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.securiy.JwtService;
import peaksoft.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final FavoriteRepository favoriteRepository;


    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new AlreadyExistException("User with email: " + signUpRequest.getEmail() + " already exist !");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCreatedDate(LocalDate.now());
        user.setUpdateDate(null);
        user.setRole(Role.USER);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token)
                .email(user.getEmail()).role(user.getRole()).build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException("User with email: " + signInRequest.getEmail() + " not found !"));
        if (signInRequest.getEmail().isBlank()){
            throw  new BadCredentialException("Email is blank ! ");
        }
        if (!passwordEncoder.matches(signInRequest.getPassword(),user.getPassword())){
            throw new BadCredentialException(("Wrong password !"));
        }
        if (userRepository.existsByEmail(user.getEmail())){
            String token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .email(signInRequest.getEmail()).build();
        }
        return null;
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getById(Long id) {
        return userRepository.getUserById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found !"));
    }

    @Override
    public SimpleResponse update(Long id, UserRequest userRequest) {
      User user =  userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found !"));
      user.setFirstName(userRequest.getFirstName());
      user.setLastName(userRequest.getLastName());
      user.setEmail(userRequest.getEmail());
      user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      user.setUpdateDate(LocalDate.now());
        return new SimpleResponse(HttpStatus.OK,"User with id:" + id + " successfully updated !");
    }

    @Override
    public SimpleResponse delete(Long id) {
        userRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK,"User with id: " + id + " successfully deleted !");
    }

    @Override
    public SimpleResponse favorite(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found !"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found !"));
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        user.setFavorites(List.of(favorite));
        product.setFavorites(List.of(favorite));
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
        return new SimpleResponse(HttpStatus.CONTINUE,"aded to favorite");
    }

    @Override
    public SimpleResponse deleteIsFavorite(Long favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId).orElseThrow(() -> new NotFoundException("not found"));
        favoriteRepository.delete(favorite);
        return new SimpleResponse(HttpStatus.OK,"successfully deleted  !");

    }
}
