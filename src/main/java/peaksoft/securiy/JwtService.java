package peaksoft.securiy;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.enums.Role;
import peaksoft.models.User;
import peaksoft.repository.UserRepository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtService {
    @Value("${spring.jwt.secret_key}")
    private String SECRET_KEY;

    public String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withClaim("username", userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(60).toInstant()))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("username").asString();
    }

}









