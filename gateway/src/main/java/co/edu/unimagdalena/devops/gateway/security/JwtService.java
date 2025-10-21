package co.edu.unimagdalena.devops.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;

@Component
public class JwtService {
    @Value("${jwt.secret}") private String secretB64;
    private Key key() { return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretB64)); }

    public Claims verify(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
    }

    public String subject(Claims c) { return c.getSubject(); }
    @SuppressWarnings("unchecked")
    public List<String> roles(Claims c) { return (List<String>) c.get("roles"); }
}
