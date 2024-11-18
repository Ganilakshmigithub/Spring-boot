package Spring.boot.crud.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTProvider {

    private static final SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateJwtToken(Authentication auth){
        return Jwts.builder()
                .setSubject(auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 432000000))
                .signWith(key)
                .compact();
    }

    public static String getEmailByJwtToken(String jwt){
        jwt = jwt.substring(7);

         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().getSubject();
    }
}