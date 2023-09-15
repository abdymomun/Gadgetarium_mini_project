package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoUser.UserResponse;
import peaksoft.models.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    boolean existsByEmail(String email);
    @Query("select new peaksoft.dto.dtoUser.UserResponse (u.id,u.firstName,u.lastName,u.email,u.password,u.createdDate,u.updateDate,u.role) from User u")
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);
}