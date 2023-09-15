package peaksoft.dto.dtoUser;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;
import peaksoft.models.Basket;
import peaksoft.models.Comment;
import peaksoft.models.Favorite;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private Role role;
    private List<Comment> comments;
    private List<Favorite>favorites;
    private Basket basket;
    public UserResponse(Long id, String firstName, String lastName, String email, String password, LocalDate createdDate, LocalDate updateDate, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.role = role;
    }
}
