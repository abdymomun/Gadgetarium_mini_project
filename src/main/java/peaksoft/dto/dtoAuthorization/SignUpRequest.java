package peaksoft.dto.dtoAuthorization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
