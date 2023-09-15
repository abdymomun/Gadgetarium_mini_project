package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoAuthorization.AuthenticationResponse;
import peaksoft.dto.dtoAuthorization.SignInRequest;
import peaksoft.dto.dtoAuthorization.SignUpRequest;
import peaksoft.dto.dtoUser.UserRequest;
import peaksoft.dto.dtoUser.UserResponse;
import java.util.List;

public interface UserService {
AuthenticationResponse signUp(SignUpRequest signUpRequest);
AuthenticationResponse signIn(SignInRequest signInRequest);
List<UserResponse> getAll();
UserResponse getById(Long id);
SimpleResponse update(Long id, UserRequest userRequest);
SimpleResponse delete(Long id);
SimpleResponse favorite(Long userId,Long productId);
SimpleResponse deleteIsFavorite(Long favoriteId);
}
