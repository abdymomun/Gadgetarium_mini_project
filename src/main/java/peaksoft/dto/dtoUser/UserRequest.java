package peaksoft.dto.dtoUser;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private Role role;
}
