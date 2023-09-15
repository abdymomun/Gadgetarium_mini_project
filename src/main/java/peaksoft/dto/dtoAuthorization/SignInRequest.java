package peaksoft.dto.dtoAuthorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
