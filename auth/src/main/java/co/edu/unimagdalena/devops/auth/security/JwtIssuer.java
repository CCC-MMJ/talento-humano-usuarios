package co.edu.unimagdalena.devops.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtIssuer {

    @Value("${jwt.secret}") private String secretB64;
    @Value("${jwt.expiration}") private long expirationMs;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretB64));
    }

    public String generate(String email, java.util.UUID userId, java.util.List<String> roles) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .claim("uid", userId.toString())
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

}
